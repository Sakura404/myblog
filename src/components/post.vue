<template>
  <v-row style="" justify="center" class="serif no-gutters">
    <v-img
      v-intersect="onIntersect"
      class="white--text align-end"
      height="300"
      gradient="rgba(0,0,0,0.2),rgba(0,0,0,0.2)"
      :src="require(`../assets/bg/${Math.floor(Math.random() * 16) + 1}.jpg`)"
    >
      <v-card-title
        style="text-shadow: 2px 2px 10px #000"
        class="py-8 font-weight-thin justify-center"
      >
        <h1>{{ post.title }}</h1>
      </v-card-title>
      <v-card-subtitle class="white--text text-center">
        <h2 class="py-2">
          <v-icon color="white">mdi-clock</v-icon>
          {{ moment(post.date) }}
          <v-icon color="white">mdi-pen</v-icon>
          {{ post.author }}
        </h2>
      </v-card-subtitle>
    </v-img>
    <v-col v-intersect="onIntersect" lg="7" xl="6" cols="12">
      <v-card
        elevation="10"
        outlined
        flat
        style="background-color: rgba(255, 255, 255, 0.8)"
        class="px-6 py-6"
      >
        <!--  -->
        <div v-html="post.content"></div>
        <!--  -->
        <v-divider></v-divider>
        <span class="text-colorfull">comment | {{ comments.length }}</span>
        <comment
          v-for="(commentItem, index) of eachPageComments"
          :dateTime="commentItem.dateTime"
          :author="commentItem.author"
          :toUser="commentItem.toUser"
          :content="commentItem.content"
          :key="index"
        >
        </comment>
        <v-pagination
          class="my-4"
          v-model="commentPage"
          v-if="commentLength > 1"
          :length="commentLength"
          :total-visible="7"
          circle
        ></v-pagination>
      </v-card>
    </v-col>
  </v-row>
</template>
<script>
import Prism from "prismjs";
import comment from "./comment.vue";
import moment from "moment";
export default {
  components: { comment },
  data: () => ({
    commentPage: 1,
    eachPage: 10,
    comments: [
      //   {
      //     // dateTime: "2021-10-5 12:49",
      //     // author: "",
      //     // toUser: "",
      //     // content: "",
      //   },
    ],
    post: {
      author: null,
      content: null,
      date: null,
      except: null,
      modified: null,
      status: null,
      title: null,
    },
  }),
  computed: {
    eachPageComments() {
      return this.comments.slice(
        (this.commentPage - 1) * this.eachPage,
        this.commentPage * this.eachPage < this.comments.length
          ? this.commentPage * this.eachPage
          : this.comments.length
      );
    },
    commentLength() {
      return Math.ceil(this.comments.length / this.eachPage);
    },
  },
  methods: {
    onIntersect(entries) {
      this.$emit("onIntersect", entries);
    },
    getPost() {
      this.$http.get(`/api/posts/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) this.post = res.data.data;
        console.log(res.data.data);
      });
    },
    moment(time) {
      return moment(time).format("YYYY-MM-DD");
    },
  },
  created() {
    Prism.highlightAll();
    this.getPost();
  },
};
</script>
<style>
.underlink {
  position: relative;
  text-decoration: none;
  transition: color 0.2s ease-out, border 0.2s ease-out, opacity 0.2s ease-out;
}
.underlink:hover {
  color: orange;
}
.underlink:after {
  content: "";
  position: absolute;
  width: 100%;
  transform: scaleX(0);
  height: 2px;
  bottom: 0;
  left: 0;
  background-color: orange;
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
}
.underlink:hover:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}
</style>