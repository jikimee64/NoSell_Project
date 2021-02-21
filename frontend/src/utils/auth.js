// Parameter => String, Array, (iterable)
export const isValue = (value) => {
  return !!value.length;
};

export const isPassword = (password) => {
  const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,15}$/i;

  return passwordRegex.test(password);
};

export const isEmail = (email) => {
  const emailRegex = /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;

  return emailRegex.test(email);
};

export const emailInfo = (email) => {
  if (!isValue(email)) return "아이디(이메일)";

  return isEmail(email) ? "OK" : "이메일을 확인해주세요.";
};

export const passwordInfo = (password) => {
  if (!isValue(password)) return "비밀번호";

  return isPassword(password) ? "OK" : "비밀번호를 확인해주세요.";
};
