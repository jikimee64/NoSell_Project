package com.soap.moon.domains.user.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Authority;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.domain.ProviderType;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserAuthority;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.dto.UserDto;
import com.soap.moon.domains.user.dto.UserDto.CheckUserAuthRes;
import com.soap.moon.domains.user.dto.UserDto.DownloadReqDto;
import com.soap.moon.domains.user.dto.UserDto.UploadReqDto;
import com.soap.moon.domains.user.exception.JwtExpiredException;
import com.soap.moon.domains.user.exception.JwtMalFormedException;
import com.soap.moon.domains.user.exception.JwtUnsupportedException;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.domains.user.repository.AuthorityRepository;
import com.soap.moon.domains.user.repository.UserOauthRepository;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.global.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final UserOauthRepository userOauthRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResourceLoader resourceLoader;
    //private final Storage storage;

    @Value("${sms.apiKey}")
    private String apiKey;

    @Value("${sms.apiSecret}")
    private String apiSecret;

    public UserDto.CheckUserAuthRes checkUserAuth(String token) {
        Boolean flag = null;
        Authentication authentication = null;
        CheckUserAuthRes build = null;
        try {
            flag = jwtTokenProvider.validateExceptionToken(token);
            authentication = jwtTokenProvider.getAuthentication(token);
        } catch (SecurityException | MalformedJwtException ex) {
            throw new JwtMalFormedException();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException();
        } catch (UnsupportedJwtException e) {
            throw new JwtUnsupportedException();
        }

        if (flag) {
            Account account = Account.builder().email(authentication.getName()).build();
            Optional<User> byAccount = userRepository.findByAccount(account);

            User user = null;
            if (!StringUtils.isEmpty(byAccount)) {
                user = byAccount.get();
                build = CheckUserAuthRes.builder()
                    .nickName(user.getNickName())
                    .profileImage(user.getProfileImage())
                    .build();
            } else {
                throw new MemberNotFoundException();
            }
        }
        return build;

    }

    /**
     * 회원가입
     */
    @Transactional
    public User save(UserDto.SignInReq dto) {
        validateDuplicateMember(dto.getEmail());

        Account account = Account.builder().email(dto.getEmail()).build();
        Password password = Password.builder()
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();

        //ROLE_USER GET
        Optional<Authority> authorityRoleUser = authorityRepository.findById(1L);

        User user = User.builder()
            .account(account)
            .password(password)
            .nickName(dto.getNickName())
            .phoneNum(dto.getPhoneNum())
            .profileImage(
                "https://user-images.githubusercontent.com/52563841/108304539-9a01e200-71eb-11eb-94a7-01ead35e186e.png")
            .status(UserStatus.ACTIVE)
            .build();

        UserAuthority userAuthorityEntity = UserAuthority.builder()
            .user(user)
            .authority(authorityRoleUser.get())
            .build();

        user.addAuthority(userAuthorityEntity);
        return userRepository.save(user);
    }

    /**
     * 중복회원 체크 account컬럼 유니크 제약조건 추가
     */
    public boolean validateDuplicateMember(String email) {
        Optional<User> findMember = userRepository.findByAccount(getAccountByUserId(email));
        findMember.ifPresent(fm -> {

            userOauthRepository.findByUser(fm).ifPresent(ofm -> {
                log.info("ofm.getProviderType()" + ofm.getProviderType());
                if ("GOOGLE".equals(ofm.getProviderType().getName())) {
                    throw new MemberDuplicationException(ProviderType.GOOGLE.getName());
                } else if ("NAVER".equals(ofm.getProviderType().getName())) {
                    throw new MemberDuplicationException(ProviderType.NAVER.getName());
                }
            });
            throw new MemberDuplicationException("자체");
        });
        return true;
    }


    public boolean certifiedPhoneNumber(String phoneNumber, String cerNum) {

        Message coolsms = new Message(apiKey, apiSecret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01023160200");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "NoSell 마켓 휴대폰인증 메시지 : 인증번호는" + "[" + cerNum + "]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            return false;
        }
        return true;
    }

    private Account getAccountByUserId(String userId) {
        return Account.builder().email(userId).build();
    }

    /**
     * gcp 파일 등록
     * 업로드 후 이미지경로 얻은다음 db에 저장 필요?
     */
    public BlobInfo uploadFileToGCS(UploadReqDto uploadReqDto){
        try{

//        System.out.println("3 ");
//        String a = getClass().getResource(filePath.toString()).toString();

//        String string = IOUtils
//            .toString(resourceLoader.getResource("classpath:" + fileName).getInputStream());
            String keyFileName = "nosell-3c87ec8cfb7f.json";
            InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

            Storage storage = StorageOptions.newBuilder().setProjectId("nosell").setCredentials(
                GoogleCredentials.fromStream(keyFile))
                .build().getService();

//        BlobInfo blobInfo =storage.create(
//            BlobInfo.newBuilder(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName())
//                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
//                .build(),
//            new FileInputStream(uploadReqDto.getLocalFileLocation()));

            BlobId blobId = BlobId.of(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build();
            Blob blob = storage
                .create(blobInfo, new FileInputStream(uploadReqDto.getLocalFileLocation()));

            return blob;
        }catch(IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

//        BlobInfo blobInfo =storage.create(
//            BlobInfo.newBuilder(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName())
//                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
//                .build(),
//            new FileInputStream(uploadReqDto.getLocalFileLocation()));
    }


    /**
     * gcp 파일 다운
     */
    public Blob downloadFileFromGCS(DownloadReqDto downloadReqDto){
        try{
            String keyFileName = "nosell-3c87ec8cfb7f.json";
            InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

            Storage storage = StorageOptions.newBuilder().setProjectId("nosell").setCredentials(
                GoogleCredentials.fromStream(keyFile))
                .build().getService();

            Blob blob = storage.get(downloadReqDto.getBucketName(), downloadReqDto.getDownloadFileName());
            blob.downloadTo(Paths.get(downloadReqDto.getLocalFileLocation()));
            return blob;
        }catch(IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

//        BlobInfo blobInfo =storage.create(
//            BlobInfo.newBuilder(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName())
//                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
//                .build(),
//            new FileInputStream(uploadReqDto.getLocalFileLocation()));
    }

    //닉네임 변경
    @Transactional
    public Map<String, String> updateMemberOfNickname(Long memberId, String nickname) {
        User user = userRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException());
        user.changeNickname(nickname);
        Map<String, String> map = new HashMap<>();
        map.put("nickname", user.getNickName());
        return map;
    }

    //비밀번호 변경
    @Transactional
    public Map<String, Boolean> updateMemberOfPassword(Long userId, String nowPassword, String newPassword) {
        User byId = userRepository.findById(userId)
            .orElseThrow(() -> new MemberNotFoundException());

        //현재 비밀번호가 맞지 않다면
        if(!byId.getPassword().equals(passwordEncoder.encode(nowPassword))){
            //throw new PasswordNotMatchException();
        }

        byId.changePassword(
            Password.builder().password((passwordEncoder.encode(newPassword))).build());
        Map<String, Boolean> map = new HashMap<>();
        map.put("confirm", true);
        return map;
    }

    //회원탈퇴
    @Transactional
    public Map<String, Boolean> deleteMember(Long userId) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("confirm", true);
        return map;
    }

    //비밀번호 찾기(이메일 계정으로)
    @Async
    public void findIdInEmail(String numStr, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom("nosell.market");
        message.setSubject("테스트 메일");
        message.setText("랜덤번호 : " + numStr);

        mailSender.send(message);
    }

    // user에 대한 판매 상품
    public List<mainProductRes> findUserSalesProducts(final Integer page, final Long memberId){
            Page<mainProductRes> products = userRepository.findSalesProductsByUser(PageRequest.of
            (page, 40, Sort.by("id").descending()), memberId);
        return products.getContent().stream()
            .map(s ->
                mainProductRes.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .price(s.getPrice())
                    .dealType(s.getDealType())
                    .salesStatus(s.getSalesStatus())
                    .image_url(s.getImage_url())
                    .createdAt(s.getCreatedAt())
                    .build())
            .collect(Collectors.toList());
    }

}
