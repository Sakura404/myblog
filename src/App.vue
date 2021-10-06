<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  data: () => ({
    //
  }),
};
</script>
<style>
.serif {
  font-family: "Noto Serif SC", "Source Han Serif SC", "Source Han Serif",
    source-han-serif-sc, "PT Serif", "SongTi SC", "MicroSoft Yahei", Georgia,
    serif !important;
}
.chinese-font {
  font-family: "Merriweather Sans", Helvetica, Tahoma, Arial, "PingFang SC",
    "Hiragino Sans GB", "Microsoft Yahei", "WenQuanYi Micro Hei", sans-serif !important;
}
</style>