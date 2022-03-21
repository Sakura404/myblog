<template>
  <home-template>
    <v-timeline align-top>
      <v-timeline-item fill-dot
        right
        small
        v-for="(item,index) in postList"
        :key="index">
        {{item.title}}
        <template slot="opposite">
          {{item.date}}
        </template>
      </v-timeline-item>
    </v-timeline>
    {{postList}}
  </home-template>
</template>
<script>
import homeTemplate from "./home-template.vue";
export default {
  components: { homeTemplate },
  data() {
    return {
      postList: [],
    };
  },
  mounted() {
    this.$http.get("api/posts/").then((res) => {
      this.postList = res.data.data;
    });
  },
};
</script>

<style lang="sass">
@import '~vuetify/src/components/VTimeline/_variables.scss'
$timeline-dot-small-size: 10px!default
</style>