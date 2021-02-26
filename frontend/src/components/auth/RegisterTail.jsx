import React from "react";
import { pwConfirmInfo, phoneInfo, nickNameInfo } from "../../utils/auth";

const RegisterTail = ({ form, onChangeForm }) => {
  const { password, passwordConfirm, phone, nickName } = form;
  return (
    <>
      <span>비밀번호 확인</span>
      <div className="field_wrap">
        <label for="sign_pw_com" className={!!passwordConfirm ? "active" : ""}>
          {pwConfirmInfo(password, passwordConfirm)}
          <span className="req">*</span>
        </label>
        <input
          type="password"
          id="sign_pw_com"
          required
          autocomplete="off"
          minlength="6"
          maxlength="15"
          name="passwordConfirm"
          value={passwordConfirm}
          onChange={onChangeForm}
        />
      </div>
      <span>휴대폰 번호</span>
      <div className="field_wrap">
        <label for="sign_phon" className={!!phone ? "active" : ""}>
          {phoneInfo(phone)}
          <span className="req">*</span>
        </label>
        <input
          type="tel"
          pattern={"[0-9]{3}-[0-9]{4}-[0-9]{4}"}
          id="sign_phon"
          required
          autocomplete="off"
          name="phone"
          value={form.phone}
          onChange={onChangeForm}
        />
        <input type="button" value="인증요청" className="com_btn" />
      </div>
      <span>닉네임</span>
      <div className="field_wrap">
        <label for="sign_name" className={nickName ? "active" : ""}>
          {nickNameInfo(nickName)}
          <span className="req">*</span>
        </label>
        <input
          type="text"
          id="sign_name"
          required
          autocomplete="off"
          minlength="2"
          maxlength="12"
          name="nickName"
          value={form.nickName}
          onChange={onChangeForm}
        />
      </div>
      <p>가입시 이용약관및 개인정보 취급방침,위치정보제공에 동의합니다.</p>
    </>
  );
};

export default RegisterTail;
