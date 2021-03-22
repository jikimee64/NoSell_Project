import Axios from "axios";

export const getMainList = (page) => Axios.get(`/api/v1/products/${page}/list`);

export const getTargetList = (page, categoryId) =>
  Axios.get(`/api/v1/products/${page}/list/categories/${categoryId}`);
