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
          <v-list-item v-for="n in 5" :key="n" @click="() => {}">
            <v-list-item-title>Option {{ n }}</v-list-item-title>
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
};
</script>
