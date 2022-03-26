<template>
  <v-app 
    class="serif chinese-font">
    <div id="blogSncakbar">
      <!-- <v-snackbar app
        color="success"
        timeout="150000"
        value="true">123</v-snackbar> -->
    </div>

    <router-view></router-view>
  </v-app>
</template>

<script>
import snackbar from "./plugins/snackbar/snackbar.js";
import Vue from "vue";
export default {
  name: "App",
  data: () => ({
    //
  }),
  mounted() {
    Vue.use(snackbar);
  },
};
</script>
<style>
.chinese-font {
  font-family: "Merriweather Sans", Helvetica, Tahoma, Arial, "PingFang SC",
    "Hiragino Sans GB", "Microsoft Yahei", "WenQuanYi Micro Hei", sans-serif !important;
}
.serif {
  font-family: "Noto Serif SC", "Source Han Serif SC", "Source Han Serif",
    source-han-serif-sc, "PT Serif", "SongTi SC", "MicroSoft Yahei", Georgia,
    serif !important;
}
</style>