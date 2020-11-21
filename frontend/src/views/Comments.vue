<template>
  <v-container fluid>
    <v-row v-for="item in items" :key="item.id">
      <v-col>
        <v-card outlined>
          <v-card-title>
            <v-list-item class="grow">
              <v-list-item-avatar>
                <v-img class="elevation-6" :src="item.uavatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{ item.uname }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-card-title>
          <v-card-text>
            <v-row>
              <v-col>
                {{ item.ccontent }}
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="totalPage"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div>
    <v-dialog width="500" v-model="dialog">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="pink" dark absolute right fab v-bind="attrs" v-on="on">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <div class="text-center">
          添加评论
        </div>
        <v-form v-model="valid">
          <v-textarea
            auto-grow
            counter
            background-color="light-blue"
            :rules="rules"
            v-model="content"
          ></v-textarea>
        </v-form>
        <v-btn
          color="primary"
          text
          block
          :disabled="!valid"
          @click="newComment()"
        >
          提交
        </v-btn>
      </v-card>
    </v-dialog>
  </v-container>
</template>
<script>
export default {
  data: () => ({
    items: [],
    page: 1,
    totalPage: 1,
    content: "",
    rules: [
      (v) => v.length <= 300 || "评论长度最长为300个字符",
      (v) => v.length > 0 || "评论不为空",
    ],
    valid: false,
    dialog: false,
  }),
  computed: {
    pid: function() {
      return this.$route.params.pid;
    },
  },
  methods: {
    async newComment() {
      // 关闭对话框
      this.dialog = false;
      // 请求加入新的评论
      await this.axios.request({
        url: `/api/comments/new`,
        method: "POST",
        data: {
          pid: Number(this.pid),
          content: this.content,
        },
      });
      // 更新页面
      this.updatePage();
    },
    updatePage() {
      this.axios
        .request({
          url: `/api/posts/${this.pid}/page/${this.page}`,
          method: "GET",
        })
        .then((resp) => {
          this.items = resp.data;
        });
    },
    getTotalPage() {
      this.axios
        .request({
          url: `/api/page/posts/${this.pid}`,
          method: "GET",
        })
        .then((resp) => {
          this.totalPage = resp.data.count;
        });
    },
  },
  created: function() {
    this.$store.commit("setTitle", "评论");
    this.getTotalPage();
    this.updatePage();
  },
  watch: {
    page: function() {
      this.updatePage();
    },
  },
};
</script>
