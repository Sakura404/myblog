<template>
  <div>
    <v-dialog max-width="400px" v-model="delectSwitch">
      <v-card>
        <v-card-title>警告</v-card-title>
        <v-card-text>确定要删除该短句么</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="delectCancel()" depressed>取消</v-btn>
          <v-btn @click="delectSumbit()" depressed>确认</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog max-width="400px" v-model="phraseFormSwitch">
      <v-card>
        <v-card-title>短句编辑</v-card-title>
        <v-card-text>
          <v-form ref="phraseForm">
            <v-text-field
              label="作者"
              v-model="phraseForm.author"
            ></v-text-field>
            <v-text-field
              label="标题"
              v-model="phraseForm.title"
            ></v-text-field>
            <v-textarea label="内容" v-model="phraseForm.content"> </v-textarea>
            <v-text-field
              label="颜色"
              @click="$refs.color.click()"
              v-model="phraseForm.color"
            >
            </v-text-field>
            <input
              style="transform: scale(0)"
              ref="color"
              type="color"
              v-model="phraseForm.color"
            />
            <v-switch
              v-model="phraseForm.dark"
              color="black"
              inset
              :label="phraseForm.dark ? 'dark' : 'light'"
            >
            </v-switch>
            <v-btn
              block
              outlined
              @click="
                if (addForm) addPhrase();
                else phraseUpdate();
              "
            >
              {{ addForm ? "添加" : "修改" }}</v-btn
            >
          </v-form>
        </v-card-text>
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
            <v-icon
              @click="
                phraseFormSwitch = true;
                phraseForm = phraseFormTemplate;
                addForm = true;
              "
              >mdi-plus</v-icon
            >
          </v-toolbar>
        </template>
        <template v-slot:[`item.opreation`]="{ item }">
          <v-btn
            icon
            @click="
              phraseForm = item;
              phraseFormSwitch = true;
              addForm = false;
            "
          >
            <v-icon>mdi-pencil-outline</v-icon>
          </v-btn>
          <v-btn icon @click="delect(item)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
        <template v-slot:[`item.color`]="{ item }">
          <v-sheet
            rounded="lg"
            :dark="item.dark"
            :color="item.color"
            class="pa-2"
            >{{ item.color }}
          </v-sheet>
        </template>
        <template v-slot:[`item.status`]="{ item }">
          <v-menu offset-y bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn depressed v-on="on" v-bind="attrs">{{
                item.status
              }}</v-btn>
            </template>
            <!-- <v-list dense>
              <v-list-item v-for="(status, key) in otherstate(item.status)"
                @click="changestate(item, status)"
                :key="key"
                class="text-center">{{ status }}</v-list-item>
            </v-list> -->
          </v-menu>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>
<script>
export default {
  data: () => ({
    allstate: ["public", "draft", "hidden"],
    delectSwitch: false,
    iw: 1,
    phraseFormSwitch: false,
    changeitem: null,
    deleteitem: null,
    loading: false,
    darkSwitch: false,
    loadingmeg: "please wait",
    headers: [
      { text: "短句编号", value: "id" },
      { text: "内容", value: "content", width: "40%" },
      { text: "作者", value: "author" },
      { text: "发表时间", value: "date", align: "center" },
      { text: "更新时间", value: "modified", align: "center" },
      { text: "颜色", value: "color", align: "center" },
      { text: "暗色主题", value: "dark", align: "center" },
      { text: "操作", value: "opreation", align: "center", sortable: false },
    ],
    addForm: true,
    phraseForm: {
      id: null,
      title: "短句",
      content: "",
      author: "",
      date: "",
      modified: "",
      status: "",
      color: "#ffffff",
      dark: false,
    },
    phraseFormTemplate: {
      id: null,
      title: "短句",
      content: "",
      author: "",
      date: "",
      modified: "",
      status: "",
      color: "#ffffff",
      dark: false,
    },
    desserts: [
      // {
      //   id: 2,
      //   title: "短句",
      //   content:
      //     "你有信仰就年轻，疑惑就年老；有自信就年轻，畏惧就年老；有希望就年轻，绝望就年老；岁月使你皮肤起皱，但是失去了热忱，就损伤了灵魂。",
      //   author: "1",
      //   date: "2022-02-25 04:17:20",
      //   modified: "2022-04-28 04:17:58",
      //   status: "public",
      //   color: "#bb6328",
      //   dark: false,
      // },
    ],
  }),
  methods: {
    phraseUpdate() {
      this.$http
        .put("/api/phrases/", this.phraseForm)
        .then((res) => {
          if (res.data.code == "10000") this.$snackbar.success("修改成功");
        })
        .catch((err) => {
          this.$snackbar.error(err.message);
        });
      this.phraseFormSwitch = false;
    },
    getPhrase() {
      this.$http
        .get("/api/phrases/")
        .then((res) => {
          this.loading = false;
          if (res.data.code == "10000") this.desserts = res.data.data;
        })
        .catch((err) => {
          this.$snackbar.error(err.message);
        });
      this.loading = true;
    },
    addPhrase() {
      this.$http
        .post("/api/phrases/", this.phraseForm)
        .then((res) => {
          if (res.data.code == "10000") {
            this.desserts.push(res.data.data);
            this.$snackbar.success("添加成功");
          }
        })
        .catch((err) => {
          this.$snackbar.error(err.message);
        });
      this.phraseFormSwitch = false;
    },
    delect(item) {
      this.deleteitem = this.desserts.indexOf(item);
      this.delectSwitch = true;
    },
    delectCancel() {
      this.deleteitem = null;
      this.delectSwitch = false;
    },
    delectSumbit() {
      if (!this.deleteitem) return;
      const deleteId = this.desserts[this.deleteitem].id;
      this.$http
        .delete("/api/phrases/" + deleteId)
        .then((res) => {
          if (res.data.code == "10000") this.$snackbar.success("删除成功");
        })
        .catch((err) => {
          this.$snackbar.error("删除失败" + err);
        });
      this.deleteitem = null;
      this.delectSwitch = false;
    },
  },
  mounted() {
    this.getPhrase();
  },
};
</script>