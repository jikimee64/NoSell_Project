import Axios from "axios";

// {
//     "email" : String,
//     "password" : String,
// }
export const authLogin = (loginObj) => Axios.post("/api/v1/login", loginObj);

// {
//     "email" : String,
//     "password" : String,
//     "nickName" : String,
//     "phoneNum" : "String1"
// }
export const authRegister = (registerObj) =>
  Axios.post("/api/v1/users", registerObj);

export const authCheck = (token) =>
  Axios.get("/api/v1/users/auth", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

export const loginGoogle = (token) =>
  Axios.post("/api/v1/oauth/GOOGLE/accessToken", { accessToken: token });

export const emailCheck = (email) =>
  Axios.post("/api/v1/users/emailCheck", { email: email });
