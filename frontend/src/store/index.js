import Vue from "vue";
import Vuex from "vuex";
import { login, register } from "../utils/request";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
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
