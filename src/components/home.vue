<template>
  <v-app>
    <v-navigation-drawer temporary v-model="drawer" app>
      <v-card img="../assets/dcbg.jpg" outlined>
        <v-card-title></v-card-title>
        <v-avatar class="mb-4" color="grey darken-1" size="64"
          ><img src="../assets/mk.jpg"
        /></v-avatar>
      </v-card>

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

    <v-app-bar absolute app :value="nai">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"> </v-app-bar-nav-icon>
      <v-app-bar-title>散の華</v-app-bar-title>
      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn></v-app-bar
    ><v-main>
      <headinfo></headinfo>
      <router-view></router-view>
    </v-main>
    <v-footer app> </v-footer>
  </v-app>
</template>
<script>
import headinfo from "../components/headinfo.vue";
export default {
  components: { headinfo },
  data: () => ({
    drawer: false,
    nai: true,
    one:
      "我们难过时，总是悲观地以为，我们这辈子都不会再变好了，后来才发现，总有一天，难过会消失的，人生会变好的。真的是有那么一天的。",
    links: [
      ["mdi-home", "首页", "home"],
      ["mdi-sort-bool-descending", "时间轴", "post"],
      ["mdi-heart", "爱好", "work"],
      ["mdi-link", "友情链接"],
      ["mdi-alert-octagon", "关于"],
    ],
  }),
};
</script>
<style>
.headinfo {
  background-attachment: fixed;
  background-image: url("../assets/dcbg.jpg");
}
</style>
