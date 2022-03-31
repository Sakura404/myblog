<template>
  <div>
    <v-dialog max-width="400px" v-model="dialogdelete">
      <v-card>
        <v-card-title>警告</v-card-title>
        <v-card-text>确定要删除该文章么</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="deletecancel()" depressed>取消</v-btn>
          <v-btn @click="deletesumbit()" depressed>确认</v-btn>
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
            <v-toolbar-title>文章管理</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>
            <v-icon @click="updata()">mdi-refresh</v-icon>
          </v-toolbar>
        </template>
        <template v-slot:[`item.opreation`]="{ item }">
          <v-btn icon :to="`editor/${item.id}`">
            <v-icon>mdi-pencil-outline</v-icon>
          </v-btn>
          <v-btn icon @click="deletePost(item)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
        <template v-slot:[`item.status`]="{ item }">
          <v-menu offset-y bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn depressed v-on="on" v-bind="attrs">{{
                item.status
              }}</v-btn>
            </template>
            <v-list dense>
              <v-list-item
                v-for="(status, key) in otherstate(item.status)"
                @click="changestate(item, status)"
                :key="key"
                class="text-center"
                >{{ status }}</v-list-item
              >
            </v-list>
          </v-menu>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import Moment from "moment";
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
        {
          text: "标题",
          value: "title",
        },
        {
          text: "编号",
          value: "id",
        },
        {
          text: "作者",
          value: "author",
        },
        {
          text: "评论数",
          value: "commentCount",
        },
        {
          text: "发表时间",
          value: "date",
          align: "center",
        },
        {
          text: "最后修改时间",
          value: "modified",
          align: "center",
        },
        {
          text: "状态",
          value: "status",
          align: "center",
        },
        {
          text: "操作",
          value: "opreation",
          align: "center",
          sortable: false,
        },
      ],
      desserts: [],
    };
  },
  mounted: function () {
    let that = this;
    this.$http.interceptors.request.use(
      function (config) {
        that.loading = true;
        return config;
      },
      function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
      }
    );
    this.$http.interceptors.response.use(
      function (response) {
        that.loading = false;

        return response;
      },
      function (error) {
        // 对响应错误做点什么
        return Promise.reject(error);
      }
    );
    // this.updata();
    this.getPosts();
  },
  methods: {
    getPosts() {
      this.$http.get("/api/posts/").then((res) => {
        this.desserts = res.data.data;
        this.desserts.forEach((e, n, a) => {
          a[n].date = Moment(e.date).format("yyyy-MM-DD HH:mm:ss");
          a[n].modified = Moment(e.modified).format("yyyy-MM-DD HH:mm:ss");
        });
      });
    },
    otherstate(name) {
      var statearr = this.allstate.slice(0);
      if (statearr.indexOf(name) != -1) {
        statearr.splice(statearr.indexOf(name), 1);
        return statearr;
      } else {
        return this.allstate;
      }
    },
    changestate(item, status) {
      this.changeitem = this.desserts.indexOf(item);
      this.desserts[this.changeitem].status = status;
      //发送改变状态请求到后端
      //this.updata()
      this.changeitem = null;
    },
    deletePost(item) {
      this.deleteitem = this.desserts.indexOf(item);
      this.dialogdelete = true;
    },
    deletesumbit() {
      this.$http
        .delete(`/api/posts/${this.desserts[this.deleteitem].id}`)
        .then((res) => {
          if (res.data.code == 10000) {
            this.desserts.splice(this.deleteitem, 1);
            this.dialogdelete = false;
            this.deleteitem = null;
            this.$forceUpdate();
            this.$snackbar.success("删除成功");
          }
        });

      //发生删除请求到后端
      //this.updata()
    },
    deletecancel() {
      this.dialogdelete = false;
      this.deleteitem = null;
    },

    updata() {
      this.getPosts();
    },
  },
};
</script>

<style>
</style>
