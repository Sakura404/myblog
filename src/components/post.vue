<template>
  <v-row style="margin-top: 56px" justify="center" class="serif no-gutters">
    <v-col
      v-intersect="onIntersect"
      style="background-color: white"
      lg="5"
      cols="12"
      class=""
    >
      <v-card >
        <v-img
          class="white--text align-end"
          height="300px"
          src="../assets/73718346_p0.png"
        >
          <v-card-title
            style="text-shadow: 2px 2px 10px #000"
            class="py-8 font-weight-thin"
          >
            <h2>{{ post.title }}</h2>
          </v-card-title>
          <v-card-subtitle class="white--text">
            <h2 class="py-2">
              <v-icon color="white">mdi-pen</v-icon>
              {{ post.author }}

              <v-icon color="white">mdi-clock</v-icon>
              {{ moment(post.date) }}
            </h2>
          </v-card-subtitle></v-img
        >
        <v-card outlined flat class="px-6 py-6">
          <!--  -->
          <div v-html="post.content"></div>
          <!--  -->
          <v-divider></v-divider>
          <span class="text-colorfull">comment | 32</span>
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
      {
        dateTime: "2021-10-5 12:49",
        author: "apex",
        toUser: null,
        content: "我们根1",
      },
    ],
    post: {author:null,content:null,date:null,except:null,modified:null,status:null,title:null,},
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
      this.$http
        .get(`/api/post/get?id=${this.$route.params.id}`)
        .then((res) => {
          if (res.data.code == 10000) this.post = res.data.data;
          console.log(res.data.data);
        });
    },
    moment(time) {
      return moment(time).format("YYYY-MM-DD");
    },
  },
  mounted() {
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