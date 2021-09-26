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
        "undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | codesample | restoredraft |code",
      toolbar_drawer: "sliding",
      quickbars_selection_toolbar:
        "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor ",
      plugins:
        "link code codesample autosave image media table lists fullscreen quickbars",
      language: "zh_CN", //本地化设置
      height: 700,
    },
  }),
};
</script>
