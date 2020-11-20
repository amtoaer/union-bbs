<template>
  <v-app>
    <v-navigation-drawer
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
    >
      <v-list shaped>
        <v-list-item-group color="primary" mandatory>
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            @click="$router.push({ path: item.path })"
          >
            <v-list-item-icon>
              <v-icon v-text="item.icon" :color="item.color"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="blue darken-3"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
        <span class="hidden-sm-and-down">{{ title }}</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon large>
        <v-avatar size="32px" item>
          <v-img :src="user.uavatar" :alt="user.uname"></v-img
        ></v-avatar>
      </v-btn>
      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon large v-bind="attrs" v-on="on" :disabled="!isLogin">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="logOut()">
            <v-list-item-title>注销</v-list-item-title>
          </v-list-item>
          <v-list-item
            @click="
              $router.push({
                path: '/profile',
              })
            "
          >
            <v-list-item-title>查看个人信息</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
  computed: {
    title: function() {
      return this.$store.state.title;
    },
    user: function() {
      return this.$store.state.user;
    },
    isLogin: function() {
      return this.$store.state.isLogin;
    },
  },
  data: () => ({
    drawer: null,
    // 显示在抽屉内的内容
    items: [
      {
        icon: "mdi-account-box",
        text: "登录",
        color: "pink lighten-1",
        path: "/login",
      },
      {
        icon: "mdi-account-plus",
        text: "注册",
        color: "pink lighten-1",
        path: "/register",
      },
    ],
  }),
  watch: {
    // 监听登录变化，如果登录则将抽屉内内容替换
    isLogin: function(val) {
      if (val) {
        this.items = [
          {
            icon: "mdi-account-box",
            text: "登录成功",
            color: "pink lighten-1",
            path: "/login",
          },
          {
            icon: "mdi-account-plus",
            text: "测试",
            color: "pink lighten-1",
            path: "/register",
          },
        ];
      } else {
        this.items = [
          {
            icon: "mdi-account-box",
            text: "登录",
            color: "pink lighten-1",
            path: "/login",
          },
          {
            icon: "mdi-account-plus",
            text: "注册",
            color: "pink lighten-1",
            path: "/register",
          },
        ];
      }
    },
  },
  created: function() {
    // 已登录
    if (localStorage.getItem("token")) {
      // 将登录状态设置为true
      this.$store.commit("setLoginStatus", true);
      // 请求当前用户信息
      this.axios
        .request({
          url: "/api/users/myInfo",
          method: "GET",
        })
        .then((resp) => {
          this.$store.commit("setUser", resp.data);
        });
      // 未登录则跳转到login页面
    } else {
      this.$router.push({ path: "/login" });
    }
  },
  methods: {
    logOut() {
      // 移除token
      localStorage.removeItem("token");
      // 设置登录状态为false
      this.$store.commit("setLoginStatus", false);
      // 跳转到登录界面
      this.$router.push({
        path: "/login",
      });
    },
  },
};
</script>
