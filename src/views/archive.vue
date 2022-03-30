<template>

  <home-template>
    <v-treeview :item=archive
      item-text></v-treeview>
    <slot name="foot"></slot>
  </home-template>

</template>
<script>
import homeTemplate from "./home-template.vue";
export default {
  components: { homeTemplate },
  data: () => ({
    archive: [],
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
          let item = { id: term.id, text: term.name, children: [] };
          this.$http.get(`api/posts/term/${term.id}`).then((r) => {
            if (r.data.code == 10000)
              for (let post of r.data.data)
                item.children.push({
                  id: post.id,
                  text: post.name,
                  date: post.date,
                  comment: post.commentCount,
                });
          });
        }
      });
  },
};
</script>
