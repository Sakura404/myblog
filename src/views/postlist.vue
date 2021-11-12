<template>
  <div>
    <v-row no-gutters>
      <v-spacer></v-spacer>
      <v-col
        style="
          background-color: rgba(255, 255, 255, 0.1);
          backdrop-filter: blur(20px);
        "
        class="px-6"
        lg="6"
        cols="12"
      >
        <v-card
          v-for="post in postList"
          hover
          class="scaleImg my-6"
          :key="post.id"
          :to="'/post/' + post.id"
        >
          <v-img
            class="white--text csfsdgf align-end"
            height="300px"
            gradient="rgba(0,0,0,0.2),rgba(0,0,0,0.2)"
            lazy-src
            :src="$randomImg.cdnRandomImg()"
          >
            <v-card-title> {{ post.title }} </v-card-title>
            <v-card-subtitle class="white--text"
              ><v-icon small color="white">mdi-clock</v-icon>
              {{ lagtime(post.date) }}
              &nbsp;
              <v-icon small color="white"> mdi-pen</v-icon>
              {{ post.author }}</v-card-subtitle
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
import Moment from "moment";
export default {
  data: () => ({
    postList: null,
  }),
  computed: {},
  methods: {
    randomImg() {
      return require(`../assets/bg/${Math.floor(Math.random() * 16) + 1}.jpg`);
    },
    onIntersect(entries) {
      this.$emit("onIntersect", entries);
    },
    getPosts() {
      this.$http
        .get("/api/posts/")
        .then((res) => {
          if (res.data.code == 10000) {
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
<style>
.scaleImg:hover .v-image__image {
  transform: scale(1.1);
  transition: transform 0.3s ease-in-out;
}
.scaleImg .v-image__image {
  transition: transform 0.3s ease-in-out;
  transform: scale(1);
}
</style>