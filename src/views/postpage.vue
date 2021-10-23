<template>
  <div>
    <headinfo class="d-md-block"></headinfo>
    <v-row no-gutters>
      <v-spacer></v-spacer>
      <v-col
        v-intersect="onIntersect"
        style="
          background-color: rgba(255, 255, 255, 0.1);
          backdrop-filter: blur(20px);
        "
        class="px-6"
        lg="6"
        cols="12"
      >
        <v-card v-for="post in postList" hover class="my-4" :key="post.id">
          <v-img
            class="white--text align-end"
            height="300px"
            src="../assets/73718346_p0.png"
          >
            <v-card-title> {{ post.title }} </v-card-title>
            <v-card-subtitle class="white--text"
              ><v-icon small color="white">mdi-clock</v-icon>
              {{ lagtime(post.date) }}
              &nbsp;
               <v-icon small color="white"> mdi-pen</v-icon
              > {{ post.author }}</v-card-subtitle
            ></v-img
          >
          <v-card-text> {{ post.excerpt }}...... </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-row no-gutters>
              <v-col lg="6" cols="6">
                <v-chip
                  v-for="term in post.termList"
                  outlined
                  link
                  class="mr-1 mb-1"
                  color="primary"
                  :key="term.id"
                  >{{ term.name }}</v-chip
                > </v-col
              ><v-spacer></v-spacer>
              <v-btn text :to="'/post/' + post.id"
                >阅读全文<v-icon>mdi-arrow-right</v-icon></v-btn
              >
            </v-row>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
  </div>
</template>

<script>
import headinfo from "../components/headinfo.vue";
import Moment from "moment";
export default {
  components: { headinfo },
  data: () => ({
    postList: null,
  }),
  methods: {
    onIntersect(entries) {
      this.$emit("onIntersect", entries);
    },
    getPosts() {
      this.$http
        .get("/api/posts/")
        .then((res) => {
          if (res.data.code == 10000) {
            console.log(res.data.data);
            this.postList = res.data.data;
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    lagtime(dateTime) {
      let pasttime = new Date(dateTime);
      let nowtime = new Date();
      let lag = Math.abs(nowtime.getTime() - pasttime.getTime());
      lag =
        lag / 1000 < 60
          ? `${parseInt(lag / 1000)} 秒前`
          : lag / 1000 / 60 < 60
          ? `${parseInt(lag / 1000 / 60)} 分前`
          : lag / 1000 / 60 / 60 < 24
          ? `${parseInt(lag / 1000 / 60 / 60)} 小时前`
          : lag / 1000 / 60 / 60 / 24 < 7
          ? `${parseInt(lag / 1000 / 60 / 60 / 24)} 天前`
          : `${Moment(pasttime).format("YYYY-MM-DD HH:mm")}
            `;
      return lag;
    },
  },
  mounted() {
    this.getPosts();
  },
};
</script>