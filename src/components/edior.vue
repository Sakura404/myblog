<template>
  <v-card outlined>
    <vue-tinymce v-model="content" :setting="setting" />
  </v-card>
</template>
<script>
export default {
  data: () => ({
    content: undefined,
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
      content_css: "https://fonts.googleapis.com/css?family=Noto+SerifMerriweather|Merriweather+Sans|Source+Code+Pro|Noto+Serif+SC ",
      height: 700,
    },
  }),
};
</script>
