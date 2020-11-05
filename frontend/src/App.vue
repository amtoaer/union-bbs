<template>
  <v-app>
    <input type="text" v-model="account" />
    <input type="text" v-model="password" />
    <button @click="login">登录</button>
    <button @click="ping">获取用户信息</button>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    account: "",
    password: "",
  }),
  methods: {
    login() {
      this.$store
        .dispatch("login", { account: this.account, password: this.password })
        .then((token) => {
          alert(token);
          localStorage.setItem("token", token);
        });
    },
    ping() {
      this.axios
        .request({
          url: "/api/users/1",
          method: "GET",
        })
        .then((data) => {
          alert(data);
        });
    },
  },
};
</script>
