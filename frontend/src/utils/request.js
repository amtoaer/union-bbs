import axios from "./axios";

// 登录接口
export const login = ({ account, password }) =>
  axios.request({
    url: "/api/login",
    method: "POST",
    data: { account, password },
  });

// 注册接口
export const register = ({ name, account, password }) =>
  axios.request({
    url: "/api/register",
    method: "POST",
    data: { name, account, password },
  });
