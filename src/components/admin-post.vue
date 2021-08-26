<template>
  <v-container>
    <v-dialog max-width="400px" v-model="dialogdelete">
      <v-card>
        <v-card-title>警告</v-card-title>
        <v-card-text>确定要删除该文章么</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="delectcancel()" depressed>取消</v-btn>
          <v-btn @click="delectsumbit()" depressed>确认</v-btn>
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
          <v-btn icon :to="'/editor?id=' + item.postid">
            <v-icon>mdi-pencil-outline</v-icon>
          </v-btn>
          <v-btn icon @click="delect(item)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
        <template v-slot:[`item.state`]="{ item }">
          <v-menu offset-y auto>
            <template v-slot:activator="{ on, attrs }">
              <v-btn depressed v-on="on" v-bind="attrs">{{ item.state }}</v-btn>
            </template>
            <v-list rounded dense>
              <v-list-item
                v-for="(statename, key) in otherstate(item.state)"
                @click="changestate(item, statename)"
                :key="key"
                >{{ statename }}</v-list-item
              >
            </v-list>
          </v-menu>
        </template>
      </v-data-table>
    </v-card>
    {{ allstate }}
  </v-container>
</template>

<script>
export default {
  name: "post",
  data() {
    return {
      allstate: ["审核", "草稿", "发布"],
      dialogdelete: false,
      iw: 1,
      changeitem: null,
      deleteitem: null,
      loading: false,
      loadingmeg: "please wait",
      headers: [
        {
          text: "名字",
          value: "postname",
        },
        {
          text: "编号",
          value: "postid",
        },
        {
          text: "作者",
          value: "postauthor",
        },
        {
          text: "评论数",
          value: "commentsum",
        },
        {
          text: "发表时间",
          value: "time",
          align: "center",
        },
        {
          text: "最后修改时间",
          value: "lasttime",
          align: "center",
        },
        {
          text: "状态",
          value: "state",
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
    this.updata();
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
    changestate(item, state) {
      this.changeitem = this.desserts.indexOf(item);
      this.desserts[this.changeitem].state = state;
      //发送改变状态请求到后端
      //this.updata()
      this.changeitem = null;
    },
    delect(item) {
      this.deleteitem = this.desserts.indexOf(item);
      this.dialogdelete = true;
    },
    delectsumbit() {
      this.desserts.splice(this.deleteitem, 1);
      this.dialogdelete = false;
      //发生删除请求到后端
      //this.updata()
      this.deleteitem = null;
    },
    delectcancel() {
      this.dialogdelete = false;
      this.deleteitem = null;
    },
    updata() {
      this.$http.get("./post.json").then((res) => {
        setTimeout(() => {
          this.desserts = res.data;
        }, 1000);
      });
    },
  },
};
</script>

<style>
</style>
