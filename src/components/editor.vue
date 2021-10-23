<template>
  <v-form class="mx-2">
    <v-row>
      <v-col lg="10" cols="12">
        <v-expansion-panels v-model="mainPanels" multiple>
          <v-expansion-panel class="mt-5">
            <v-expansion-panel-header>标题</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-text-field label="" v-model="form.post.title"></v-text-field>
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
          /> </v-card
      ></v-col>
      <v-col lg="2" cols="12"
        ><v-expansion-panels v-model="supPanels" multiple class="my-5">
          <v-expansion-panel>
            <v-expansion-panel-header>作者</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-overflow-btn
                editable
                label="Author"
                v-model="form.post.author"
                :items="users"
                class="mt-3"
              ></v-overflow-btn>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel
            ><v-expansion-panel-header>标签分类</v-expansion-panel-header>
            <v-divider></v-divider>
            <v-expansion-panel-content>
              <v-chip-group multiple column v-model="form.terms">
                <v-chip
                  filter
                  v-for="(term, index) in terms"
                  :key="index"
                  :value="term.id"
                >
                  {{ term.name }}{{ term.id }}</v-chip
                >
              </v-chip-group>
            </v-expansion-panel-content></v-expansion-panel
          >
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
              <v-row
                ><v-col>发布时间:</v-col
                ><v-col
                  ><v-text-field
                    type="datetime-local"
                    v-model="date"
                  ></v-text-field></v-col
              ></v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels> </v-col
    ></v-row>
    <v-row>
      <v-col>
        <v-btn v-if="newPost" @click="addPost()" block outlined>
          提交文章
        </v-btn>
        <v-btn v-else @click="addPost()" block outlined>更新文章</v-btn>
      </v-col>
    </v-row>
  </v-form>
</template>
<script>
import Moment from "moment";

export default {
  data: () => ({
    content: undefined,
    newPost: true,
    setting: {
      menubar: "file edit insert view format table tools help",
      toolbar:
        "undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect lineheight forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | codesample | restoredraft |code",
      toolbar_drawer: "sliding",
      font_formats:
        "serif= Noto Serif SC,Noto Serif SC, Source Han Serif SC, Source Han Serif, source-han-serif-sc, PT Serif, SongTi SC, MicroSoft Yahei, Georgia, serif ",
      quickbars_selection_toolbar:
        "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor ",
      plugins:
        "importcss code link code codesample autosave image media table lists fullscreen quickbars",
      language: "zh_CN", //本地化设置
      content_css:
        "https://fonts.googleapis.com/css?family=Noto+SerifMerriweather|Merriweather+Sans|Source+Code+Pro|Noto+Serif+SC ",
      height: 700,
    },
    mainPanels: [0, 1],
    supPanels: [0, 1, 2],
    date: Moment(new Date()).format("yyyy-MM-DDThh:mm"),
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
    terms: [],
    users: ["orag", "senkaryouran", "Sakura"],
    status: ["public", "draft", "hidden"],
  }),
  computed: {

  },
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
      this.form.post.date = Moment(this.date).format("yyyy-MM-DD HH:mm:ss");
      console.log(this.form);
      this.$http
        .post("/api/posts/", this.form)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    updataPost() {},
  },
  created() {
    this.getterms();
    if (this.$route.params.id) {
      this.$http.get(`api/posts/${this.$route.params.id}`).then((res) => {
        if (res.data.code == 10000) {
          this.form.post = res.data.data;
          this.newPost = false;
        } else {
          this.$router.push("/admin/editor");
        }
      });
      this.$http.get(`api/terms/post/${this.$route.params.id}`).then((res) => {
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
