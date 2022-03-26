<template>
  <v-row no-gutters>
    <v-col v-if="toUser"
      cols="2"
      sm="1"></v-col>
    <v-col class="comment">
      <v-row class="py-3"
        no-gutters>
        <v-col align-self="center"
          cols="2"
          sm="1">
          <v-avatar size="54"
            color="blue">
            <v-img v-if="this.avatar"
              min-width="170%"
              position="top"
              src="../assets/奥尔加·伊兹卡.png"></v-img>

            <h2 style="user-select: none">{{ this.author[0] }}</h2>

          </v-avatar>
        </v-col>
        <v-col cols="10"
          align-self="center">
          <v-row no-gutters
            style="color: #3daee9"
            class="font-weight-bold comment-author"> <a :name="`comment${id}`">{{ author }} </a></v-row>
          <v-row no-gutters>
            <v-col class="grey--text comment-datetime">发布于 {{ lagtime }}</v-col>
            <v-spacer></v-spacer>
          </v-row>
        </v-col>
        <v-col align-self="center"
          class="text-left">
          <v-chip transition="fade-transition"
            color="primary"
            class="comment-reply"
            small
            style="user-select: none"
            @click="$emit('reply', id, author)">回复</v-chip>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="2"
          sm="1"></v-col>
        <v-col cols="11">
          <p>
            <span v-if="toUser">
              回复
              <a class="comment-toUser underlink"
                :href="`#comment${replyId}`">@{{ toUser }} </a>:</span>
            {{ content }}
          </p>
        </v-col>
      </v-row>
      <v-divider></v-divider>
    </v-col>
  </v-row>
</template>
<script>
import Moment from "moment";
export default {
  props: {
    id: {
      type: Number,
    },
    author: {
      type: String,
      default: "作者",
    },
    dateTime: {
      type: String,
      default: "2021-10-4 15:00",
    },
    content: {
      type: String,
      default: "空白评论",
    },
    replyId: {
      type: Number,
      default: null,
    },
    toUser: {
      type: String,
      default: null,
    },
    avatar: {
      type: String,
      default: null,
    },
  },
  computed: {
    lagtime() {
      let pasttime = new Date(this.dateTime);
      let nowtime = new Date();
      let lag = nowtime.getTime() - pasttime.getTime();
      lag =
        lag / 1000 < 60
          ? `${parseInt(lag / 1000)} 秒 前`
          : lag / 1000 / 60 < 60
          ? `${parseInt(lag / 1000 / 60)} 分 前`
          : lag / 1000 / 60 / 60 < 24
          ? `${parseInt(lag / 1000 / 60 / 60)} 小时 前`
          : lag / 1000 / 60 / 60 / 24 < 7
          ? `${parseInt(lag / 1000 / 60 / 60 / 24)} 天 前`
          : `${Moment(pasttime).format("YYYY-MM-DD HH:mm")}
            `;
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
  transition: all 0.5s;
  opacity: 0;
}
.comment:hover .comment-reply {
  visibility: visible;
  opacity: 1;
}
.comment-toUser {
  text-decoration: none;
}
</style>