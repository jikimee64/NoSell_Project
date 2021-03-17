import React from "react";
import { pwConfirmInfo, phoneInfo, nickNameInfo } from "../../utils/auth";

const RegisterTail = ({ form, onChangeForm }) => {
  const { password, passwordConfirm, phone, nickName } = form;
  return (
    <>
      <span>비밀번호 확인</span>
      <div className="field_wrap">
        <label
          htmlFor="sign_pw_com"
          className={!!passwordConfirm ? "active" : ""}
        >
          {pwConfirmInfo(password, passwordConfirm)}
          <span className="req">*</span>
        </label>
        <input
          type="password"
          id="sign_pw_com"
          required
          autoComplete="off"
          minLength="6"
          maxLength="15"
          name="passwordConfirm"
          value={passwordConfirm}
          onChange={onChangeForm}
        />
      </div>
      <span>휴대폰 번호</span>
      <div className="field_wrap">
        <label htmlFor="sign_phon" className={!!phone ? "active" : ""}>
          {phoneInfo(phone)}
          <span className="req">*</span>
        </label>
        <input
          type="tel"
          pattern={"[0-9]{11}"}
          id="sign_phon"
          required
          autoComplete="off"
          name="phone"
          value={form.phone}
          onChange={onChangeForm}
        />
        <button className="com_btn">인증요청</button>
      </div>
      <span>인증번호</span>
      <div className="field_wrap">
        <input
          type="number"
          id="phone_auth"
          autoComplete="off"
          name="phoneAuth"
          pattern={"[0-9]{6}"}
          value={form.phoneAuth}
          onChange={onChangeForm}
          placeholder="인증번호를 확인해주세요."
        />
        <button className="com_btn">인증확인</button>
      </div>
      <span>닉네임</span>
      <div className="field_wrap">
        <label htmlFor="sign_name" className={nickName ? "active" : ""}>
          {nickNameInfo(nickName)}
          <span className="req">*</span>
        </label>
        <input
          type="text"
          id="sign_name"
          required
          autoComplete="off"
          minLength="2"
          maxLength="12"
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
