<template>
  <v-container fluid>
    <v-row justify="center">
      <div class="transition-swing text-h5 mb-2" align="center">
        用户登录
      </div>
    </v-row>
    <v-row>
      <v-col cols="6" offset="3">
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="account"
            :counter="10"
            :rules="accountRules"
            label="帐号"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :counter="16"
            :rules="passwordRules"
            type="password"
            label="密码"
            required
          ></v-text-field>

          <v-btn :disabled="!valid" color="primary" block @click="login">
            提交
          </v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data: () => ({
    account: "",
    password: "",
    accountRules: [
      (v) => !!v || "帐号不能为空",
      (v) => (v && v.length <= 10) || "帐号长度必须小于10字符",
    ],
    passwordRules: [
      (v) => !!v || "密码不能为空",
      (v) => (v && v.length <= 16) || "密码长度必须小于16字符",
    ],
    valid: false,
  }),
  methods: {
    login() {
      this.$store
        .dispatch("login", { account: this.account, password: this.password })
        .then((token) => {
          // 把token保存到localStorage
          localStorage.setItem("token", token);
          // 设置登陆状态为true
          this.$store.commit("setLoginStatus", true);
          this.$store.dispatch("successMessage", "登录成功");
          // 跳转到主页
          this.$router.push({ path: "/" });
        });
    },
  },
  created: function() {
    this.$store.commit("setTitle", "登录");
  },
};
</script>
