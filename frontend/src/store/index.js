import Vue from "vue";
import Vuex from "vuex";
import { login, register } from "../utils/request";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {
      id: 0,
      uname: "未登录",
      uaccount: "未登录",
      uavatar:
        "https://gravatar.loli.net/avatar/d41d8cd98f00b204e9800998ecf8427e?s=40&d=mp",
      upermission: 2,
      usign: "",
    },
    title: "",
    isLogin: false,
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setTitle(state, title) {
      state.title = title;
    },
    setLoginStatus(state, isLogin) {
      state.isLogin = isLogin;
    },
    setDefaultUser(state) {
      state.user = {
        id: 0,
        uname: "未登录",
        uaccount: "未登录",
        uavatar:
          "https://gravatar.loli.net/avatar/d41d8cd98f00b204e9800998ecf8427e?s=40&d=mp",
        upermission: 2,
        usign: "",
      };
    },
  },
  actions: {
    async login({ commit }, user) {
      const { data } = await login(user);
      if (data.error) {
        return Promise.reject(data);
      } else {
        commit("setUser", data.user);
        return Promise.resolve(data.token);
      }
    },

    async register({ commit }, user) {
      const { data } = await register(user);
      if (data.error) {
        return Promise.reject(data);
      } else {
        commit("setUser", data.user);
        return Promise.resolve(data.token);
      }
    },
  },
  modules: {},
});
