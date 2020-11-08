<template>
  <v-container fluid>
    <v-list>
      <v-list-item v-for="post in posts" :key="post.id">
        <v-list-item-avatar>
          <v-img :src="post.avatar"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title v-text="post.username"></v-list-item-title>
          <v-list-item-subtitle v-text="post.title"></v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-icon>
          <v-icon>mdi-star</v-icon>
          {{ post.comment }}
        </v-list-item-icon>
      </v-list-item>
    </v-list>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    posts: [
      {
        id: 1,
        username: "amtoaer",
        avatar:
          "http://cdn.jsdelivr.net/gh/amtoaer/blogcdn@master/img/custom/amtoaer.png",
        title: "这是第一篇文章！",
        date: "2020.1.27",
        comment: 10,
      },
    ],
  }),
};
</script>
