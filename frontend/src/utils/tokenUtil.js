import jwt from "jsonwebtoken";

export const validateToken = (token) => {
  const decodeToken = jwt.decode(token);
  const nowTime = new Date().getTime() / 1000;
  return decodeToken.exp >= nowTime;
};
