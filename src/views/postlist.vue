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
        <!-- <v-card
          v-for="post in postList"
          hover
          class="scaleImg my-6"
          :key="post.id"
        >
          <v-img
            class="white--text align-end"
            height="300px"
            gradient="rgba(0,0,0,0.2),rgba(0,0,0,0.2)"
            lazy-src
            :src="
              post.attachment ? post.attachment.url : $randomImg.cdnRandomImg()
            "
          >
            <v-card-title> {{ post.title }} </v-card-title>
            <v-card-subtitle class="white--text">
              <v-icon small color="white">mdi-clock</v-icon>
              {{ lagtime(post.date) }}
              &nbsp;
              <v-icon small color="white"> mdi-pen</v-icon>
              {{ post.author }}
            </v-card-subtitle>
          </v-img>
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
                  @click="getPostByTermId(term.id)"
                  :key="term.id"
                  >{{ term.name }}</v-chip
                >
              </v-col>
              <v-spacer></v-spacer>
              <v-btn text :to="'/post/' + post.id"
                >阅读全文<v-icon>mdi-arrow-right</v-icon>
              </v-btn>
            </v-row>
          </v-card-actions>
        </v-card> -->
        <v-card color="#bb6688" hover class="my-6">
          <v-card-title>短句</v-card-title>
          <v-card-subtitle>{{ this.$Moment().fromNow() }}</v-card-subtitle>
          <v-card-text>
            <p>
              你有信仰就年轻，疑惑就年老；有自信就年轻，畏惧就年老；有希望就年轻，绝望就年老；岁月使你皮肤起皱，但是失去了热忱，就损伤了灵魂。
            </p>
            <p class="text-end font-weight-bold">—— orga</p>
          </v-card-text>
        </v-card>
        <post-card
          v-for="(item, index) in showAllList"
          :key="index"
          :type="item.type"
          :id="item.id"
          :attachment="item.attachment"
          :author="item.author"
          :date="item.date"
          :color="item.color"
          :content="item.content"
          :dark="item.dark"
          :excerpt="item.excerpt"
          :termList="item.termList"
          :title="item.title"
          @getPostByTermId="getPostByTermId"
        ></post-card>
        <slot name="foot"></slot>
      </v-col>
      <v-fab-transition>
        <v-btn
          style="bottom: 10vh; right: 5vw"
          fixed
          fab
          v-if="termBtn"
          @click="getPosts()"
        >
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </v-fab-transition>
      <v-spacer></v-spacer>
    </v-row>
  </div>
</template>

<script>
import Moment from "moment";
import PostCard from "../components/PostCard.vue";
export default {
  components: { PostCard },
  data: () => ({
    postList: [],
    phraseList: [],
    termBtn: false,
  }),
  computed: {
    showAllList() {
      let postList = this.postList.map((v) => {
        v.type = "post";
        return v;
      });
      let phraseList = this.termBtn
        ? []
        : this.phraseList.map((v) => {
            v.type = "phrase";
            return v;
          });
      return [...postList, ...phraseList].sort((a, b) => {
        return new Date(a.date) - new Date(b.date);
      });
    },
  },
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
            this.termBtn = false;
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    getPostByTermId(id) {
      this.$http
        .get(`/api/posts/term/${id}`)
        .then((res) => {
          if (res.data.code == 10000) {
            this.postList = res.data.data;
            this.termBtn = true;
          }
        })
        .catch((err) => {
          this.$snackbar.error(err);
        });
    },
    getPhrase() {
      this.$http
        .get("/api/phrases/")
        .then((res) => {
          if (res.data.code == 10000) {
            this.phraseList = res.data.data;
          }
        })
        .catch((err) => {
          this.$snackbar.error(err);
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
    this.getPhrase();
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