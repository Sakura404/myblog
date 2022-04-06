<template>
  <home-template>
    <v-expansion-panels>
      <v-expansion-panel v-for="(term, index) in archive" :key="index">
        <v-expansion-panel-header>
          {{ term.name }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-timeline dense>
            <v-timeline-item v-for="(post, i) in term.children" :key="i" small>
              <v-row>
                <v-col>
                  <a class="underlink" :href="`/post/${post.id}`">
                    {{ post.title }}
                  </a>
                </v-col>
                <v-col class="text-end">
                  {{ $Moment(post.date).format(`yyyy-MM-DD`) }}
                </v-col>
              </v-row>
            </v-timeline-item>
          </v-timeline>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

    <slot
      name="
        foot"
    ></slot>
  </home-template>
</template>
<script>
import homeTemplate from "../components/home-template.vue";
export default {
  components: { homeTemplate },
  data: () => ({
    archive: [],
    items: [
      {
        id: 1,
        name: "Applications :",
        children: [
          { id: 2, name: "Calendar : app" },
          { id: 3, name: "Chrome : app" },
          { id: 4, name: "Webstorm : app" },
        ],
      },
      {
        id: 5,
        name: "Documents :",
        children: [
          {
            id: 6,
            name: "vuetify :",
            children: [
              {
                id: 7,
                name: "src :",
                children: [
                  { id: 8, name: "index : ts" },
                  { id: 9, name: "bootstrap : ts" },
                ],
              },
            ],
          },
          {
            id: 10,
            name: "material2 :",
            children: [
              {
                id: 11,
                name: "src :",
                children: [
                  { id: 12, name: "v-btn : ts" },
                  { id: 13, name: "v-card : ts" },
                  { id: 14, name: "v-window : ts" },
                ],
              },
            ],
          },
        ],
      },
      {
        id: 15,
        name: "Downloads :",
        children: [
          { id: 16, name: "October : pdf" },
          { id: 17, name: "November : pdf" },
          { id: 18, name: "Tutorial : html" },
        ],
      },
      {
        id: 19,
        name: "Videos :",
        children: [
          {
            id: 20,
            name: "Tutorials :",
            children: [
              { id: 21, name: "Basic layouts : mp4" },
              { id: 22, name: "Advanced techniques : mp4" },
              { id: 23, name: "All about app : dir" },
            ],
          },
          { id: 24, name: "Intro : mov" },
          { id: 25, name: "Conference introduction : avi" },
        ],
      },
    ],
  }),
  created() {
    this.$http
      .get("api/terms/")
      .then((res) => {
        console.log(res);
        if (res.data.code == 10000) return res.data.data;
      })
      .then((res) => {
        for (let term of res) {
          this.$http.get(`api/posts/term/${term.id}`).then((r) => {
            if (r.data.code == 10000) {
              let item = { id: term.id, name: term.name, children: [] };
              for (let post of r.data.data) item.children.push(post);
              this.archive.push(item);
            }
          });
        }
      })
      .then(() => {
        this.$http.get(`api/posts/term/`).then((r) => {
          if (r.data.code == 10000) {
            let item = { id: -1, name: "未分类", children: [] };
            for (let post of r.data.data) item.children.push(post);
            this.archive.push(item);
          }
        });
      });
  },
};
</script>
