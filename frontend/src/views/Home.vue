<template>
  <v-container fluid>
    <v-list>
      <v-list-item
        v-for="post in posts"
        :key="post.id"
        @click="$router.push({ path: `/post/${post.id}` })"
      >
        <v-list-item-avatar>
          <v-img :src="post.uavatar"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title v-text="post.uname"></v-list-item-title>
          <v-list-item-subtitle v-text="post.ptitle"></v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-icon>
          <v-icon>mdi-comment-processing-outline</v-icon>
          {{ post.pcomment }}
        </v-list-item-icon>
      </v-list-item>
    </v-list>
    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="totalPage"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    posts: [],
    page: 1,
    totalPage: 1,
  }),
  methods: {
    // 用于更新当前所处的页
    updatePage: function() {
      this.axios
        .request({
          url: `/api/posts/page/${this.page}`,
          method: "GET",
        })
        .then((resp) => {
          this.posts = resp.data;
        });
    },

    // 用于得到总页数
    getTotalPage: function() {
      this.axios
        .request({
          url: "/api/page/posts",
          method: "GET",
        })
        .then((resp) => {
          this.totalPage = resp.data.count;
        });
    },
  },
  watch: {
    page: function() {
      this.updatePage();
    },
  },
  created: function() {
    this.$store.commit("setTitle", "主页");
    this.getTotalPage();
    this.updatePage();
  },
};
</script>
