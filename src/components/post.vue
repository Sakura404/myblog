<template>
  <v-row
    v-if="post"
    style="position: relative"
    justify="center"
    class="serif no-gutters"
  >
    <v-img
      class="white--text align-end"
      height="300"
      :src="imgSrc"
      min-width="100%"
      gradient="rgba(0,0,0,0.2),rgba(0,0,0,0.2)"
    >
      <v-card-title
        style="text-shadow: 2px 2px 10px #000"
        class="py-8 font-weight-thin justify-center"
      >
        <h1
          v-intersect="{
            handler: onIntersect,
            options: {
              threshold: [0, 0.8],
            },
          }"
        >
          {{ post.title }}
        </h1>
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
    <v-spacer></v-spacer>
    <v-col :class="navStatu" cols="2"></v-col>
    <v-col lg="7" xl="6" cols="12">
      <v-card
        elevation="10"
        outlined
        flat
        style="background-color: rgba(255, 255, 255, 0.8)"
        class="px-6 py-6"
      >
        <!--  -->
        <div
          id="postContent"
          v-resize="onNavResize"
          v-html="post.content"
        ></div>
        <!--  -->

        <v-divider></v-divider>
        <span
          id="comment_top"
          v-if="comments.length != 0"
          class="text-colorfull"
          >comment | {{ comments.length }}</span
        >
        <comment
          v-for="(commentItem, index) of eachPageComments"
          :dateTime="commentItem.date"
          :author="commentItem.user ? commentItem.user.name : null"
          :toUser="commentItem.reply ? commentItem.reply.user.name : null"
          :replyId="commentItem.reply ? commentItem.reply.id : null"
          :content="commentItem.content"
          :id="commentItem.id"
          :key="index"
          @reply="reply"
        >
        </comment>
        <v-pagination
          class="my-4"
          v-model="commentPage"
          v-if="commentLength > 1"
          :length="commentLength"
          :total-visible="7"
          circle
          @input="tocommentTop()"
        ></v-pagination>

        <v-divider> </v-divider>
        <v-form ref="comment">
          <v-row class="my-4" no-gutters>
            <v-col class="mr-5" cols="4">
              <v-text-field
                outlined
                v-model="commentForm.name"
                :rules="commentRules.name"
                label="名称"
              >
              </v-text-field>
            </v-col>
            <v-col class="mr-5" cols="4">
              <v-text-field
                type="email"
                outlined
                label="邮箱"
                :rules="commentRules.email"
                v-model="commentForm.email"
              >
              </v-text-field>
            </v-col>
            <v-col class="mr-5" cols="3">
              <v-btn
                outlined
                block
                v-if="commentForm.replyName"
                @click="replyCancel()"
                >取消回复</v-btn
              >
            </v-col>
            <v-col cols="12">
              <v-textarea
                :rules="commentRules.content"
                outlined
                :label="
                  commentForm.replyName
                    ? `回复 @${commentForm.replyName}:`
                    : '评论'
                "
                v-model="commentForm.content"
              ></v-textarea>
            </v-col>
            <v-col cols="12">
              <v-btn @click="replysubmit()" outlined block>
                {{ commentForm.replyName ? "回复" : "评论" }}
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
        <v-divider></v-divider>
        <v-row class="my-4" justify="center" no-gutters>
          <v-col
            v-for="(item, index) in postguide"
            :key="index"
            cols="12"
            :md="postguide.length > 1 ? 6 : 12"
            ><a style="text-decoration: none" :href="`/post/${item.post_id}`">
              <v-img
                height="150"
                :src="item.img"
                class="white--text postguide align-center"
              >
                <p
                  style="padding: 40px; height: 50%"
                  class="subtitle-1"
                  :class="item.type == 'PREVIOUS' ? 'text-light' : 'text-right'"
                >
                  {{ item.type }} POST<br />
                  {{ item.post_title }}
                </p>
              </v-img>
            </a></v-col
          >
          <slot name="foot"></slot>
          <!-- <v-col cols="12" md="6"
            ><a style="text-decoration: none" href="">
              <v-img
                height="150"
                gradient="rgba(0,0,0,0.3),rgba(0,0,0,0.3)"
                :src="imgSrc"
                class="white--text align-center"
                ><p
                  style="padding: 40px; height: 50%"
                  class="text-right subtitle-1"
                >
                  后一篇 文章<br />
                  {{ s }}
                </p></v-img
              >
            </a></v-col
          > -->
        </v-row>
        <!-- <v-pagination
          class="my-4"
          v-model="commentPage"
          v-if="commentLength > 1"
          :length="commentLength"
          :total-visible="7"
          circle
        ></v-pagination> -->
      </v-card>
    </v-col>
    <v-col
      :style="navstyle"
      :class="navStatu"
      style=""
      class="d-none d-lg-block pl-6"
      cols="2"
      xl="2"
    >
      <div>
        <postnav v-if="nav.length > 0" :navlist="nav"></postnav>
      </div>
    </v-col>
  </v-row>
</template>

<script>
import Prism from "prismjs";
import comment from "./comment.vue";
import moment from "moment";
import Postnav from "./postnav.vue";
export default {
  components: { comment, Postnav },
  data: () => ({
    commentPage: 1,
    eachPage: 10,
    nav: [],

    comments: [],
    post: {
      author: null,
      content: null,
      date: null,
      except: null,
      modified: null,
      status: null,
      title: null,
    },
    commentForm: {
      name: "",
      postId: null,
      replyId: null,
      replyName: "",
      email: "",
      content: "",
    },
    navleft: 0,
    navStatu: "nav-absolute ",
    postguide: "",
    commentRules: {
      name: [(v) => !!v || "名称不能为空"],
      email: [
        (v) => !!v || "邮箱不能为空",
        (v) => /.+@.+/.test(v) || "邮箱格式不正确",
      ],
      content: [(v) => !!v || "内容不能为空"],
    },
  }),
  computed: {
    imgSrc() {
      return this.$randomImg.cdnRandomImg();
    },
    navstyle() {
      return `left : ${this.navleft}px`;
    },

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
    reply(replyId, replyName) {
      //console.log(replyName, replyId);
      this.commentForm.replyName = replyName;
      this.commentForm.replyId = replyId;
    },
    replyCancel() {
      this.commentForm.replyName = "";
      this.commentForm.replyId = null;
    },
    replysubmit() {
      if (this.$refs.comment.validate()) {
        this.$http.post("/api/comments/", this.commentForm).then((res) => {
          if (res.data.code == 10000) {
            this.getComments();
            this.$snacbar.success("评论成功");
          }
        });
      }
    },
    onIntersect(entries) {
      this.$emit("onIntersect", entries);
    },
    tocommentTop() {
      document.getElementById("comment_top").scrollIntoView();
    },
    getPost() {
      this.$http.get(`/api/posts/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) this.post = res.data.data;
        this.$nextTick(this.tonavlist);
      });
    },
    imgHoverIn() {},
    getPostguide() {
      this.$http
        .get(`/api/common/findbeforafter/${this.$route.params.id}`)
        .then((res) => {
          if (res.data.code == 10000) {
            this.postguide = res.data.data;
            this.postguide.forEach((e) => {
              e.img = this.$randomImg.cdnRandomImg();
            });
          }
        });
    },
    moment(time) {
      return moment(time).format("YYYY-MM-DD");
    },
    getTocDepth(dom) {
      let top = document.querySelector("#postContent");
      if (dom.parentNode == top) return 1;
      if (dom.parentNode == document.querySelector("html")) return 0;
      return this.getTocDepth(dom.parentNode) + 1;
    },
    tonavlist() {
      let list = new Array();
      console.log("1");
      document.querySelectorAll(".mce-toc a").forEach((e) => {
        let text = e.innerText;
        let href = e.getAttribute("href");
        list.push({
          text: text,
          href: href,
          offsetTop: document.getElementById(href.substring(1)).offsetTop + 240,
          deep: this.getTocDepth(e),
        });
      });
      this.nav = list;
    },
    randomImg() {
      return require(`../assets/bg/${Math.floor(Math.random() * 16) + 1}.jpg`);
    },
    handleScroll() {
      const scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      if (scrollTop >= 300 - 56) {
        this.navStatu = "nav-fixed";
      } else {
        this.navStatu = "nav-absolute";
      }
    },
    onNavResize() {
      let content = document.querySelector("#postContent");
      const windowX = document.documentElement.offsetWidth;
      const navMr = (windowX + content.offsetWidth) / 2 + 56; //(X-w/2 + w)化简
      this.navleft = navMr;
    },
    getComments() {
      this.$http.get(`/api/comments/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) {
          this.comments = res.data.data;
          console.log(res.data.data);
        }
      });
    },
  },
  created() {
    this.getPost();
    this.getPostguide();
    this.getComments();
    this.commentForm.postId = this.$route.params.id;
  },
  mounted() {
    let that = this;
    window.onload = function () {
      that.tonavlist();
    };
    window.addEventListener("scroll", this.handleScroll, true);
  },
  updated() {
    Prism.highlightAll();
  },
  destroyed() {
    // 离开该页面需要移除这个监听的事件，不然会报错
    window.removeEventListener("scroll", this.handleScroll);
  },
};
</script>
<style>
.underlink {
  position: relative;
  padding-bottom: 2px;
  text-decoration: none;
  transition: color 0.2s ease-out, border 0.2s ease-out, opacity 0.2s ease-out;
}
.underlink:hover {
  color: #104b86;
}
.underlink:after {
  content: "";
  position: absolute;
  width: 100%;
  transform: scaleX(0);
  height: 2px;
  bottom: 0;
  left: 0;
  background-color: #104b86;
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
}
.underlink:hover:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}
.postguide .v-image__image {
  transition: all 0.4s ease-in-out;
  filter: brightness(20%);
}
.postguide:hover .v-image__image {
  transition: all 0.4s ease-in-out;
  filter: brightness(40%);
}
.mce-toc {
  display: none;
}
.nav-absolute {
  position: absolute;
  top: 300px;
  left: 80vw;
}
.nav-fixed {
  position: fixed;
  top: 57px;
  left: 80vw;
}
</style>