<template>
  <div>
    <v-dialog max-width="400px" v-model="dialogdelete">
      <v-card>
        <v-card-title>警告</v-card-title>
        <v-card-text>确定要删除该评论么</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="deleteCancel()" depressed>取消</v-btn>
          <v-btn @click="deleteSumbit()" depressed>确认</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-card>
      <v-data-table
        :headers="headers"
        :loading="loading"
        :loading-text="loadingmeg"
        :items="desserts"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>评论管理</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>
            <!-- <v-icon @click="updata()">mdi-refresh</v-icon> -->
          </v-toolbar>
        </template>
        <template v-slot:[`item.postId`]="{ item }">
          <a :href="`./editor/${item.postId}`">{{ item.postId }}</a>
        </template>
        <template v-slot:[`item.opreation`]="{ item }">
          <v-btn icon @click="deleteComment(item)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allstate: ["public", "draft", "hidden"],
      dialogdelete: false,
      iw: 1,
      changeitem: null,
      deleteitem: null,
      loading: false,
      loadingmeg: "please wait",
      headers: [
        { text: "评论编号", value: "id" },
        { text: "文章编号", value: "postId" },
        { text: "评论内容", value: "content" },
        { text: "评论者", value: "user.name" },
        { text: "邮箱", value: "user.email" },
        { text: "发表时间", value: "date", align: "center" },
        { text: "操作", value: "opreation", align: "center", sortable: false },
      ],
      desserts: [],
    };
  },
  methods: {
    otherstate(name) {
      var statearr = this.allstate.slice(0);
      if (statearr.indexOf(name) != -1) {
        statearr.splice(statearr.indexOf(name), 1);
        return statearr;
      } else {
        return this.allstate;
      }
    },
    changeState(item, status) {
      this.changeitem = this.desserts.indexOf(item);
      this.desserts[this.changeitem].status = status;
      //发送改变状态请求到后端
      //this.updata()
      this.changeitem = null;
    },
    deleteCancel() {
      this.dialogdelete = false;
      this.deleteitem = null;
    },
    deleteSumbit() {
      this.dialogdelete = false;
      this.$http
        .delete(`/api/comments/${this.desserts[this.deleteitem].id}`)
        .then((res, resolve, reject) => {
          if (res.data.code == 10000) {
            this.$snackbar.success("删除成功");
            this.desserts.splice(this.deleteitem, 1);
          } else reject();
        })
        .catch(() => {
          this.$snackbar.error("删除失败");
        });
    },
    deleteComment(item) {
      this.deleteitem = this.desserts.indexOf(item);
      this.dialogdelete = true;
    },
  },
  mounted() {
    this.$http.get("/api/comments/").then((res) => {
      if (res.data.code == 10000) this.desserts = res.data.data;
    });
  },
};
</script>

<style>
</style>