<template>
  <v-container fluid>
    <v-form ref="form" v-model="valid" class="pa-4 pt-6">
      <v-text-field
        v-model="uname"
        filled
        :rules="nameRules"
        color="primary"
        label="用户名（用于显示）"
      ></v-text-field>
      <v-text-field
        v-model="uaccount"
        filled
        :rules="accountRules"
        color="primary"
        label="账户名（用于登录）"
      ></v-text-field>
      <v-text-field
        v-model="upassword"
        filled
        :rules="passwordRules"
        color="primary"
        label="密码（留空表示不修改）"
      ></v-text-field>
      <v-text-field
        v-model="uavatar"
        filled
        :rules="avatarRules"
        color="primary"
        label="头像链接"
      ></v-text-field>
    </v-form>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        :disabled="!valid"
        class="white--text"
        color="primary accent-4"
        depressed
        @click="updateProfile()"
      >
        提交
      </v-btn>
    </v-card-actions>
  </v-container>
</template>
<script>
export default {
  data: () => ({
    vaild: false,
    accountRules: [
      (v) => !!v || "帐号不能为空",
      (v) => (v && v.length <= 10) || "帐号长度必须小于10字符",
    ],
    nameRules: [
      (v) => !!v || "用户名不能为空",
      (v) => (v && v.length <= 10) || "用户名长度必须小于10字符",
    ],
    passwordRules: [(v) => (v && v.length <= 16) || "密码长度必须小于16字符"],
    avatarRules: [
      (v) => !!v || "头像链接不能为空",
      (v) => (v && v.length <= 150) || "头像链接长度必须小于150字符",
    ],
    uname: "",
    uaccount: "",
    upassword: "",
    uavatar: "",
  }),
  created: function() {
    this.uname = this.$store.state.user.uname;
    this.uaccount = this.$store.state.user.uaccount;
    this.uavatar = this.$store.state.user.uavatar;
  },
  methods: {
    updateProfile() {
      this.axios
        .request({
          url: `/api/users/update`,
          method: "POST",
          data: {
            uname: this.uname,
            uaccount: this.uaccount,
            upassword: this.upassword,
            uavatar: this.uavatar,
          },
        })
        .then((resp) => {
          if (resp.data.error) {
            this.$store.dispatch("errorMessage", resp.data.message);
          } else {
            // 成功修改
            this.$store.dispatch("successMessage", resp.data.message);
            // 因为token使用用户密码作为secret，因此如果修改了密码需要刷新token
            if (this.upassword.length != 0) {
              this.$store
                .dispatch("login", {
                  account: this.uaccount,
                  password: this.upassword,
                })
                .then((token) => {
                  // 把token保存到localStorage
                  localStorage.setItem("token", token);
                });
            }
          }
        });
    },
  },
};
</script>
