<template>
  <v-container fluid>
    <v-form ref="form" v-model="valid" class="pa-4 pt-6">
      <v-text-field
        v-model="title"
        filled
        color="primary"
        label="标题"
      ></v-text-field>
      <v-textarea
        v-model="content"
        auto-grow
        filled
        color="primary"
        label="内容"
      ></v-textarea>
    </v-form>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        :disabled="!valid"
        class="white--text"
        color="primary accent-4"
        depressed
        @click="newPost()"
      >
        提交
      </v-btn>
    </v-card-actions>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    valid: false,
    title: "",
    content: "",
  }),
  methods: {
    async newPost() {
      //请求发帖
      await this.axios.request({
        url: `/api/posts/new`,
        method: "POST",
        data: {
          title: this.title,
          content: this.content,
        },
      });
      // 跳转到帖子界面
      this.$router.push({ path: "/" });
    },
  },
  created: function() {
    this.$store.commit("setTitle", "发帖");
  },
};
</script>
