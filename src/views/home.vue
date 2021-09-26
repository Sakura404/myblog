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
      :color="toolcolor ? 'rgba(0, 0, 0, 0)' : 'rgba(255,255,255,0.95)'"
      :flat="!toolflat"
      @mouseenter="toolcolor = toolcolorlock ? toolcolor : false"
      @mouseleave="toolcolor = toolcolorlock ? toolcolor : true"
      short
      fixed
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"> </v-app-bar-nav-icon>
      <v-app-bar-title>散の華</v-app-bar-title>
      <v-spacer></v-spacer>

      <v-btn icon >
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn  icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn></v-app-bar
    >

    <v-main>
      <v-container fluid class="pa-0">
        <div :style="mainimg" class="biyin">
          <router-view @onIntersect="onIntersect"></router-view>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>
<script>
export default {
  data: () => ({
    drawer: false,
    toolcolor: true,
    toolcolorlock: false,
    toolflat: false,
    nai: true,
    one: "我们难过时，总是悲观地以为，我们这辈子都不会再变好了，后来才发现，总有一天，难过会消失的，人生会变好的。真的是有那么一天的。",
    links: [
      ["mdi-home", "首页", "home"],
      ["mdi-sort-bool-descending", "时间轴", "post"],
      ["mdi-heart", "爱好", "work"],
      ["mdi-gamepad-variant", "游戏", "game"],
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
    handleScroll() {
      this.toolflat =
        (window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop) > 0
          ? true
          : false;
    },
    onToolfixed() {
      console.log(1);
      window.pageYOffset;
    },
    onIntersect(entries) {
      this.toolcolor = !entries[0].isIntersecting;
      this.toolcolorlock = entries[0].isIntersecting;
    },
  },
  mounted: function () {
    this.$http.get("https://api.xygeng.cn/Bing/url/").then((res) => {
      this.biyinapi = res.data;
    });
    window.addEventListener("scroll", this.handleScroll, true);
  },

  destroyed() {
    // 离开该页面需要移除这个监听的事件，不然会报错
    window.removeEventListener("scroll", this.handleScroll);
  },
};
</script>
<style>
.white-hover:hover {
  background: white;
}
.headinfo {
  background-attachment: fixed;
  background-image: url("../assets/dcbg.jpg");
}
.biyin {
  background-attachment: fixed;
}
code {
  background-color: rgba(1, 1, 1, 0) !important;
}
</style>
