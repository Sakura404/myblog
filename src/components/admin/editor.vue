<template>
  <v-form>
    <v-row>
      <v-col lg="9"
        cols="12">
        <v-expansion-panels v-model="mainPanels"
          multiple>
          <v-expansion-panel class="mt-5">
            <v-expansion-panel-header>标题</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-text-field label=""
                v-model="form.post.title"></v-text-field>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel class="my-5">
            <v-expansion-panel-header>摘要</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-textarea class="mt-2"
                dense
                row-height="14px"
                flat
                outlined
                counter
                auto-grow
                v-model="form.post.excerpt"></v-textarea>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-card rounded="lg">
          <vue-tinymce v-model="form.post.content"
            :setting="setting"
            :setup="setup" />
        </v-card>
      </v-col>
      <v-col lg="3"
        cols="12">
        <v-expansion-panels v-model="supPanels"
          multiple
          class="my-5">
          <v-expansion-panel>
            <v-expansion-panel-header>作者</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-overflow-btn editable
                label="Author"
                v-model="form.post.author"
                :items="users"
                class="mt-3"></v-overflow-btn>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel>
            <v-expansion-panel-header>标签分类</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-chip-group multiple
                column
                v-model="form.terms">
                <v-chip filter
                  v-for="(term, index) in terms"
                  :key="index"
                  :value="term.id">
                  {{ term.name }}</v-chip>
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
                  <v-select :items="status"
                    v-model="form.post.status"
                    menu-props="{ fixed:true,bottom: true, overflowY: true }"></v-select>
                </v-col>
              </v-row>
              <v-row no-gutters
                align="center">
                <v-col>发布日期:</v-col>
                <v-col>
                  <v-text-field v-model="date"
                    type="date"></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center">
                <v-col>发布时间:</v-col>
                <v-col>
                  <v-text-field v-model="time"
                    type="time"></v-text-field>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn v-if="newPost"
          @click="addPost()"
          block
          outlined>
          提交文章
        </v-btn>
        <v-btn v-else
          @click="updataPost()"
          block
          outlined>更新文章</v-btn>
      </v-col>
    </v-row>

    <v-dialog 
      absolute	
      class
      max-width="1000px"
      v-model="imageManager" >
      <admin-image></admin-image>
    </v-dialog>
  </v-form>
</template>
<script>
import Moment from "moment";
import adminImage from "./admin-image.vue";
export default {
  components: { adminImage },
  data: () => ({
    content: undefined,
    newPost: true,
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
        "imgList undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect lineheight forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | codesample | restoredraft toc |code|hr",
      toolbar_drawer: "sliding",
      font_formats:
        "serif= Noto Serif SC,Noto Serif SC, Source Han Serif SC, Source Han Serif, source-han-serif-sc, PT Serif, SongTi SC, MicroSoft Yahei, Georgia, serif ",
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
    supPanels: [0, 1, 2],
    date: Moment(new Date()).format("yyyy-MM-DD"),
    time: Moment(new Date()).format("hh:mm:ss"),
    form: {
      post: {
        title: "",
        excerpt: "",
        content: "",
        date: "",
        status: "公开",
      },
      terms: [],
    },
    imageManager: false,
    terms: [],
    users: ["orag", "senkaryouran", "Sakura"],
    status: ["public", "draft", "hidden"],
  }),
  computed: {},
  methods: {
    getterms() {
      this.$http.get("/api/terms/").then((res) => {
        console.log(res);
        if (res.data.code == 10000) this.terms = res.data.data;
      });
    },
    addPost() {
      this.form.terms.forEach((e, i, a) => {
        a[i] = { id: e };
      });
      this.$set(
        this.form.post,
        "date",
        Moment(`${this.date} ${this.time}`).format("yyyy-MM-DD hh:mm:ss")
      );
      this.$http
        .post("/api/posts/", this.form)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    updataPost() {
      this.form.terms.forEach((e, i, a) => {
        a[i] = { id: e };
      });
      this.$set(
        this.form.post,
        "date",
        Moment(`${this.date} ${this.time}`).format("yyyy-MM-DD hh:mm:ss")
      );
      let Data = this.form;
      this.$http
        .put(`/api/posts/${this.$route.params.id}`, Data)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    setup(editor) {
      let _this = this;
      editor.ui.registry.addIcon(
        "shopping-cart",
        `<svg viewBox="0 0 1024 1024" data-icon="shopping-cart" width="1.5em" height="1.5em" fill="currentColor" aria-hidden="true" focusable="false" class=""><path d="M922.9 701.9H327.4l29.9-60.9 496.8-.9c16.8 0 31.2-12 34.2-28.6l68.8-385.1c1.8-10.1-.9-20.5-7.5-28.4a34.99 34.99 0 0 0-26.6-12.5l-632-2.1-5.4-25.4c-3.4-16.2-18-28-34.6-28H96.5a35.3 35.3 0 1 0 0 70.6h125.9L246 312.8l58.1 281.3-74.8 122.1a34.96 34.96 0 0 0-3 36.8c6 11.9 18.1 19.4 31.5 19.4h62.8a102.43 102.43 0 0 0-20.6 61.7c0 56.6 46 102.6 102.6 102.6s102.6-46 102.6-102.6c0-22.3-7.4-44-20.6-61.7h161.1a102.43 102.43 0 0 0-20.6 61.7c0 56.6 46 102.6 102.6 102.6s102.6-46 102.6-102.6c0-22.3-7.4-44-20.6-61.7H923c19.4 0 35.3-15.8 35.3-35.3a35.42 35.42 0 0 0-35.4-35.2zM305.7 253l575.8 1.9-56.4 315.8-452.3.8L305.7 253zm96.9 612.7c-17.4 0-31.6-14.2-31.6-31.6 0-17.4 14.2-31.6 31.6-31.6s31.6 14.2 31.6 31.6a31.6 31.6 0 0 1-31.6 31.6zm325.1 0c-17.4 0-31.6-14.2-31.6-31.6 0-17.4 14.2-31.6 31.6-31.6s31.6 14.2 31.6 31.6a31.6 31.6 0 0 1-31.6 31.6z"></path></svg>`
      );
      editor.ui.registry.addButton("imgList", {
        icon: "Image",
        onAction: function () {
          _this.imageManager = true;
          //   editor.execCommand(
          //     "mceInsertContent",
          //     false,
          //     '<img alt="Smiley face" height="42" width="42" src=""/>'
          //   );
        },
      });
    },
  },
  created() {
    this.getterms();
    if (this.$route.params.id) {
      this.$http.get(`/api/posts/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) {
          this.form.post = res.data.data;
          this.date = Moment(res.data.data.date).format("yyyy-MM-DD");
          this.time = Moment(res.data.data.time).format("hh:mm");
          this.newPost = false;
        } else {
          this.$router.push("/admin/editor");
        }
      });

      this.$http.get(`/api/terms/post/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) {
          console.log(res);
          res.data.data.forEach((e) => {
            this.form.terms.push(e.id);
          });
        }
      });
    }
  },
};
</script>
