<template>
  <div class="comment">
    <v-row class="py-3" no-gutters>
      <v-col align-self="center" cols="2" sm="1">
        <v-avatar size="54" color="orange">
          <v-img
            min-width="170%"
            position="top"
            src="../assets/奥尔加·伊兹卡.png"
          ></v-img
        ></v-avatar>
      </v-col>
      <v-col cols="10" align-self="center"
        ><v-row
          no-gutters
          style="color: #3daee9"
          class="font-weight-bold comment-author"
          >{{ author }}</v-row
        >
        <v-row no-gutters>
          <v-col class="grey--text comment-datetime"
            >发布于 {{ lagtime }}前</v-col
          >
          <v-spacer></v-spacer
        ></v-row>
      </v-col>
      <v-col align-self="center" class="text-right"
        ><v-chip
          transition="fade-transition"
          color="primary"
          class="comment-reply"
          small
          >回复</v-chip
        ></v-col
      >
    </v-row>
    <v-row no-gutters>
      <v-col cols="2" sm="1"></v-col>
      <v-col cols="10">
        <p>
          {{ content }}
        </p>
      </v-col>
    </v-row>
    <v-divider></v-divider>
  </div>
</template>
<script>
export default {
  props: {
    author: {
      type: String,
      default: "作者",
    },
    datetime: {
      type: String,
      default: "2021-10-4 15:00",
    },
    content: {
      type: String,
      default: "空白评论",
    },
  },
  computed: {
    lagtime() {
      let pasttime = new Date(this.datetime);
      let nowtime = new Date();
      let lag = nowtime.getTime() - pasttime.getTime();
      lag =
        lag / 1000 < 60
          ? `${parseInt(lag / 1000)} 秒`
          : lag / 1000 / 60 < 60
          ? `${parseInt(lag / 1000 / 60)} 分`
          : lag / 1000 / 60 / 60 < 24
          ? `${parseInt(lag / 1000 / 60 / 60)} 小时`
          : lag / 1000 / 60 / 60 / 24 < 30
          ? `${parseInt(lag / 1000 / 60 / 60 / 24)} 天`
          : lag / 1000 / 60 / 60 / 24 / 30 < 12
          ? `${parseInt(lag / 1000 / 60 / 60 / 24 / 30)} 月`
          : `${parseInt(lag / 1000 / 60 / 60 / 24 / 365)} 年`;
      return lag;
    },
  },
};
</script>
<style >
.comment-author {
  font-size: 15px;
  font-weight: 600;
}
.comment-datetime {
  margin-top: 2px;
  font-size: 12px;
  letter-spacing: 0;
  text-transform: none;
  color: rgba(0, 0, 0, 0.35);
}
.comment-reply {
  visibility: hidden;
  transition: all .5s;
  opacity:0
}
.comment:hover .comment-reply {
  visibility: visible;
  opacity:1
}
</style>