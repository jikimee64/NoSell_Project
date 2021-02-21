import Axios from "axios";

// const BASE_URL = "http://34.64.124.246/api/v1/";

export const getMainList = (page) => Axios.get(`/api/v1/products/${page}/list`);
export const localLogin = (loginObj) => Axios.post("/api/v1/login", loginObj);
