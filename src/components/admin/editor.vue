<template>
  <v-form ref="post">
    <v-row>
      <v-col lg="9" cols="12">
        <v-expansion-panels v-model="mainPanels" multiple>
          <v-expansion-panel class="mt-5">
            <v-expansion-panel-header>标题</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-text-field
                label=""
                :rules="rules.title"
                v-model="form.post.title"
              ></v-text-field>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel class="my-5">
            <v-expansion-panel-header>摘要</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-textarea
                class="mt-2"
                dense
                row-height="14px"
                flat
                outlined
                counter
                auto-grow
                v-model="form.post.excerpt"
              ></v-textarea>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-card rounded="lg">
          <vue-tinymce
            v-model="form.post.content"
            :setting="setting"
            :setup="setup"
          />
        </v-card>
      </v-col>
      <v-col lg="3" cols="12">
        <v-expansion-panels v-model="supPanels" multiple class="my-5">
          <v-expansion-panel>
            <v-expansion-panel-header>作者</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-overflow-btn
                editable
                :rules="rules.author"
                label="Author"
                v-model="form.post.author"
                :items="users"
                class="mt-3"
              ></v-overflow-btn>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel>
            <v-expansion-panel-header>标签分类</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-chip-group multiple column v-model="form.terms">
                <v-chip
                  filter
                  close
                  @click:close="deleteTerm(term)"
                  v-for="(term, index) in terms"
                  :key="index"
                  :value="term.id"
                >
                  {{ term.name }}</v-chip
                >
                <v-chip @click="addTermFlag = true" v-if="!addTermFlag">
                  +
                </v-chip>
                <v-chip v-else>
                  <span class="term-form">
                    <v-text-field v-model="newTerm"></v-text-field>
                    <v-btn small icon @click="addTerm(newTerm)">添加</v-btn>
                  </span>
                </v-chip>
                <v-dialog max-width="50vw" :value="deleteTermItem" absolute>
                  <v-card>
                    <v-card-title>警告</v-card-title>
                    <v-card-text>确定要删除该标签么</v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn @click="deleteTermCancel()" depressed>取消</v-btn>
                      <v-btn @click="deleteTermSumbit()" depressed>确认</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-chip-group>
            </v-expansion-panel-content>
          </v-expansion-panel>
          <v-expansion-panel>
            <v-expansion-panel-header>信息</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-row>
                <v-col align-self="center"> 状态:</v-col>
                <v-col cols="8">
                  <v-select
                    :items="status"
                    v-model="form.post.status"
                    menu-props="{ fixed:true,bottom: true, overflowY: true }"
                  ></v-select>
                </v-col>
              </v-row>
              <v-row no-gutters align="center">
                <v-col>发布日期:</v-col>
                <v-col>
                  <v-text-field v-model="date" type="date"></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center">
                <v-col>发布时间:</v-col>
                <v-col>
                  <v-text-field v-model="time" type="time"></v-text-field>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
          <v-expansion-panel>
            <v-expansion-panel-header>特色图片</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-img
                min-height="300"
                max-height="700"
                contain
                :src="
                  form.post.attachment.url
                    ? form.post.attachment.url
                    : defaultImgUrl
                "
              >
              </v-img>
              <v-btn
                block
                @click="
                  imageManager = true;
                  editorImgCheck = false;
                "
              >
                添加
              </v-btn>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn v-if="newPost" @click="addPost()" block outlined>
          提交文章
        </v-btn>
        <v-btn v-else @click="updataPost()" block outlined>更新文章</v-btn>
      </v-col>
    </v-row>

    <v-dialog
      absolute
      class="dialog-overflow"
      content-class="dialog-overflow"
      max-width="1100px"
      v-model="imageManager"
    >
      <admin-image
        @close="imageManager = false"
        @imgSelect="imageSelect"
      ></admin-image>
    </v-dialog>
  </v-form>
</template>
<script>
import tinymce from "tinymce";
import Vue from "vue";
import VueTinymce from "@packy-tang/vue-tinymce";
Vue.prototype.$tinymce = tinymce; // 将全局tinymce对象指向给Vue作用域下
Vue.use(VueTinymce); // 安装vue的tinymce组件
import Moment from "moment";
import adminImage from "./admin-image.vue";
export default {
  components: { adminImage },
  data: () => ({
    content: undefined,
    newPost: true,
    _editor: 0,
    defaultImgUrl: null,
    setting: {
      //   images_upload_handler: function imgUpLoad(blobInfo, success, failure) {
      //     let file = blobInfo.blob();
      //     let data = new FormData();
      //     data.append("file", file);
      //     data.append("Content-Type", "multipart/form-data");
      //     this.axios
      //       .post("./api/medias/", data)
      //       .then((response) => {
      //         if (response.data.code == 10000) success(response.data.data.url);
      //         else failure(response);
      //       })
      //       .catch((e) => {
      //         failure(e);
      //       });
      //},
      menubar: "file edit insert view format table  tools help",
      toolbar:
        " undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | imgList media table | fontselect fontsizeselect lineheight forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | codesample | restoredraft toc |code|hr",
      toolbar_drawer: "sliding",
      font_formats: "serif=Noto Serif SC, Source Han Serif SC;",
      quickbars_selection_toolbar:
        "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor ",
      plugins:
        " toc importcss hr code link code codesample autosave image media table lists fullscreen quickbars",
      language: "zh_CN", //本地化设置
      content_css:
        "https://fonts.googleapis.com/css?family=Noto+SerifMerriweather|Merriweather+Sans|Source+Code+Pro|Noto+Serif+SC ",
      height: 700,
    },

    mainPanels: [0, 1],
    supPanels: [0, 1, 2, 3],
    date: Moment(new Date()).format("yyyy-MM-DD"),
    time: Moment(new Date()).format("HH:mm:ss"),
    form: {
      post: {
        title: "",
        excerpt: "",
        content: "",
        date: "",
        status: "public",
        attachment: { id: -1 },
      },
      terms: [],
    },
    editorImgCheck: true,
    imageManager: false,
    terms: [],
    newTerm: null,
    addTermFlag: false,
    deleteTermItem: null,
    rules: {
      title: [(v) => !!v || "标题不能为空"],
      content: [(v) => !!v || "内容不能为空"],
      author: [(v) => !!v || "作者不能为空"],
    },
    users: ["orag", "senkaryouran", "Sakura"],
    status: ["public", "draft", "hidden"],
  }),
  computed: {},
  methods: {
    getterms() {
      this.$http.get("/api/terms/").then((res) => {
        if (res.data.code == 10000) this.terms = res.data.data;
      });
    },
    addPost() {
      if (this.$refs.post.validate()) {
        let termList = [];
        this.form.terms.forEach((e) => {
          termList.push({ id: e });
        });
        this.$set(
          this.form.post,
          "date",
          Moment(`${this.date} ${this.time}`).format("yyyy-MM-DD hh:mm:ss")
        );
        let Data = new FormData();
        Data = { post: this.form.post, terms: termList };
        this.$http
          .post("/api/posts/", Data)
          .then((res) => {
            this.$snackbar.success("文章添加成功:" + res.data.mes);
            this.$route.push(`/admin/editor/${res.data.data.id}`);
          })
          .catch((err) => {
            this.$snackbar.error(err);
          });
      }
    },
    updataPost() {
      if (this.$refs.post.validate()) {
        let termList = [];
        this.form.terms.forEach((e) => {
          termList.push({ id: e });
        });
        this.$set(
          this.form.post,
          "date",
          Moment(`${this.date} ${this.time}`).format("yyyy-MM-DD hh:mm:ss")
        );
        let Data = new FormData();
        Data = { post: this.form.post, terms: termList };
        // console.log(Data);

        this.$http
          .put(`/api/posts/${this.$route.params.id}`, Data)
          .then((res) => {
            this.$snackbar.success("文章更新成功:" + res.data.message);
          })
          .catch((err) => {
            this.$snackbar.error(err);
          });
      }
    },
    setup(editor) {
      this._editor = editor;
      let _this = this;
      editor.on("init", function () {
        this.getBody().style.fontFamily = "Noto Serif SC";
      });
      editor.ui.registry.addButton("imgList", {
        icon: "Image",
        onAction: function () {
          _this.editorImgCheck = true;
          _this.imageManager = true;
          //   editor.execCommand(
          //     "mceInsertContent",
          //     false,
          //     '<img alt="Smiley face" height="42" width="42" src=""/>'
          //   );
        },
      });
    },
    addEditorImg(onCheck) {
      this._editor.execCommand(
        "mceInsertContent",
        false,
        `<img  alt="${onCheck.excerpt || ""}"  src="${
          onCheck.url
        }" width="90%"/>`
      );
      this.imageManager = false;
    },
    addAttachment(onCheck) {
      this.form.post.attachment = onCheck;
      this.imageManager = false;
    },
    imageSelect(onCheck) {
      if (this.editorImgCheck) {
        this.addEditorImg(onCheck);
      } else this.addAttachment(onCheck);
    },
    addTerm(name) {
      this.addTermFlag = false;
      if (name && name.length <= 4) {
        this.terms.push({ name: name, id: 65 });
        this.addTermFlag = false;
        this.newTerm = null;
      } else {
        if (!name) this.$snackbar.warning("标签名不能为空");
        else this.$snackbar.warning("标签名不能大于4位");
      }
    },
    deleteTerm(item) {
      console.log(item.id);
      this.deleteTermItem = item;
    },
    deleteTermSumbit() {
      this.terms.splice(this.terms.indexOf(this.deleteTermItem), 1);
        this.deleteTermItem = null;
    },
    deleteTermCancel() {
      this.deleteTermItem = null;
    },
  },
  created() {
    this.defaultImgUrl = this.$randomImg.cdnRandomImg();
    this.getterms();
    this.$watch(
      () => this.addTermFlag,
      (name) => {
        console.log(name);
      }
    );
    if (this.$route.params.id) {
      this.$http.get(`/api/posts/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) {
          this.form.post = res.data.data;
          this.date = Moment(res.data.data.date).format("yyyy-MM-DD");
          this.time = Moment(res.data.data.time).format("HH:mm:ss");
          this.newPost = false;
        } else {
          this.$route.push("/admin/editor");
        }
      });
      this.$http.get(`/api/terms/post/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) {
          //  console.log(res);
          res.data.data.forEach((e) => {
            this.form.terms.push(e.id);
          });
        }
      });
    }
  },
};
</script>
<style>
/* .dialog-overflow {
  overflow: hidden !important;
} */
.term-form {
  display: flex;
  align-items: center;
}
</style>
