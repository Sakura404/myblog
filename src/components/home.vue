<template>
  <v-app>
    <v-navigation-drawer temporary v-model="drawer" app>
      <v-sheet outlined>
        <v-img src="../assets\dcbg.jpg">
          <v-card-title></v-card-title>
          <v-avatar class="mb-4" color="grey darken-1" size="64"
            ><img src="../assets/mk.jpg"
          /></v-avatar>
        </v-img>
      </v-sheet>

      <v-divider></v-divider>

      <v-list>
        <v-list-item
          color="blue"
          v-for="[icon, text, link] in links"
          :to="link"
          :key="icon"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list></v-navigation-drawer
    >

    <v-app-bar
      style="transition: 0.5s"
      clipped-left
      :color="toolcolor ? 'rgba(0, 0, 0, 0)' : 'white'"
      :flat="toolcolor"
      short
      fixed
      @mouseenter="toolcolor = false"
      @mouseleave="toolcolor = true"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"> </v-app-bar-nav-icon>
      <v-app-bar-title>散の華</v-app-bar-title>
      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn></v-app-bar
    >
    <v-main>
      <v-container fluid class="pa-0">
        <headinfo class="d-md-block"></headinfo>

        <div :style="mainimg" class="mt-3 biyin">
          <v-row>
            <v-spacer></v-spacer>
            <v-col
              v-intersect="onIntersect"
              style="
                background-color: rgba(255, 255, 255, 0.1);
                backdrop-filter: blur(20px);
              "
              class="px-6"
              xl="6"
              cols="10"
            >
              <v-card class="my-4">
                <v-img
                  class="white--text align-end"
                  height="400px"
                  position="top top"
                  src="../assets/mk.jpg"
                >
                  <v-card-title> asdf </v-card-title></v-img
                >
                <v-card-text> dfsssssssss </v-card-text>
                <v-card-actions>s</v-card-actions>
              </v-card>
              <v-card class="my-4">
                <v-img
                  class="white--text align-end"
                  height="400px"
                  src="../assets/73718346_p0.png"
                >
                  <v-card-title> オレンジ </v-card-title>
                  <v-card-subtitle class="white--text"
                    ><v-icon small color="white">mdi-clock</v-icon>
                    2020-7-31</v-card-subtitle
                  ></v-img
                >

                <v-card-text>
                  "Turns out semicolon-less style is easier and safer in TS
                  because most gotcha edge cases are type invalid as well."
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                  <v-row no-gutters>
                    <v-col cols="4">
                      <v-chip outlined class="mr-1" color="primary"
                        >日常</v-chip
                      >
                      <v-chip outlined class="mr-1" color="primary"
                        >念念碎</v-chip
                      > </v-col
                    ><v-spacer></v-spacer
                    ><v-btn text
                      >阅读全文 <v-icon>mdi-arrow-right</v-icon></v-btn
                    >
                  </v-row>
                </v-card-actions>
              </v-card>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>
<script>
import headinfo from "../components/headinfo.vue";
export default {
  components: { headinfo },
  data: () => ({
    drawer: false,
    toolcolor: true,
    nai: true,
    one: "我们难过时，总是悲观地以为，我们这辈子都不会再变好了，后来才发现，总有一天，难过会消失的，人生会变好的。真的是有那么一天的。",
    links: [
      ["mdi-home", "首页", "home"],
      ["mdi-sort-bool-descending", "时间轴", "post"],
      ["mdi-heart", "爱好", "work"],
      ["mdi-link", "友情链接"],
      ["mdi-alert-octagon", "关于"],
    ],
    biyinapi: [],
  }),
  computed: {
    mainimg() {
      let url = this.biyinapi.data;
      const style = `background-image:url(${url})`;
      return style;
    },
  },
  methods: {
    onIntersect(entries) {
      console.log(this.toolcolor);
      console.log(entries);
      this.toolcolor = !entries[0].isIntersecting;
    },
  },
  mounted: function () {
    this.$http.get("https://api.xygeng.cn/Bing/url/").then((res) => {
      this.biyinapi = res.data;
    });
  },
};
</script>
<style>
.headinfo {
  background-attachment: fixed;
  background-image: url("../assets/dcbg.jpg");
}
.biyin {
  background-attachment: fixed;
}
</style>
