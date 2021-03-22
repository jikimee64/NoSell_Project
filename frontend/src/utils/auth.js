export const isEmail = (email) => {
  const emailRegex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  return emailRegex.test(email);
};

export const emailInfo = (email) => {
  if (!email) {
    return "아이디(이메일)";
  }
  return isEmail(email) ? "Email OK!" : "이메일 양식이 맞지 않습니다.";
};

export const isPassword = (password) => {
  const pwRegx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,15}/;
  return pwRegx.test(password);
};

export const pwInfo = (password) => {
  if (!password) {
    return "비밀번호";
  }
  return isPassword(password) ? "PW OK!" : "패스워드 양식이 맞지 않습니다.";
};

export const pwConfirmInfo = (password, pwConfirm) => {
  if (!pwConfirm) {
    return "영문,숫자,특수문자 포함 6-15자";
  }
  return password === pwConfirm
      ? "PW Confirm OK!"
      : "패스워드가 일치하지 않습니다.";
};

export const phoneInfo = (phone) => {
  if (!phone) {
    return "숫자만 입력";
  }
  return /^[0-9]{11}$/.test(phone) ? "Phone OK!" : "번호 양식에 맞지않습니다.";
};

export const nickNameInfo = (nickName) => {
  if (!nickName) {
    return "최소 2자이상 입력";
  }
  return nickName.length > 1 ? "Nick OK!" : "닉네임 양식에 맞지 않습니다.";
};
