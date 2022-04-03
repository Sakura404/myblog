<template>
  <home-template>
    <v-timeline>
      <v-timeline-item
        v-for="(item, index) in postList"
        :key="index"
        fill-dot
        color="blue"
      >
        <template slot="opposite">
          <span class="headline font-weight-bold">
            {{
              `${new Date(item.date).getMonth() + 1} 月 ${
                new Date(item.date).getUTCDate() + 1
              } 日`
            }}</span
          >
        </template>

        <div :class="index % 2 == 0 ? 'text-start' : 'text-end'" class="pa-4">
          <h2 class="headline font-weight-light mb-4">
            <a class="underlink" :href="`post/${item.id}`">
              {{ item.title }}
            </a>
          </h2>
          <div>{{ item.excerpt || "空空如也" }}</div>
        </div>
        <template v-slot:icon>
          <span class="text--white">{{
            new Date(item.date).getFullYear()
          }}</span>
        </template>
      </v-timeline-item>
    </v-timeline>
    <div class="text-center text-h6">本站已运行{{ runningTime }}小时</div>
    <slot name="foot"></slot>
  </home-template>
</template>
<script>
import homeTemplate from "../components/home-template.vue";
export default {
  components: { homeTemplate },
  data() {
    return {
      postList: [
        {
          id: 30,
          title: "22-1-16",
          author: "senkaryouran",
          date: "2022-01-16 16:41:15",
          modified: "2022-01-16 20:52:22",
          excerpt: "",
          termList: [],
          status: "public",
          commentCount: 0,
        },
      ],
      runningTime: "",
    };
  },
  mounted() {
    this.$http.get("api/posts/").then((res) => {
      this.postList = res.data.data;
    });
    this.getTime();
  },
  methods: {
    dateSort(postList) {
      let dateMap = new Map();
      for (let e of postList) {
        let date = new Date(e.date);
        if (!dateMap.has(date.getFullYear())) {
          dateMap.set(date.getFullYear(), []);
        }

        let moonthMap = dateMap.get(date.getFullYear());
        if (date.getMonth() in moonthMap) {
          moonthMap[date.getMonth()].push(e);
        } else {
          moonthMap[date.getMonth()] = [e];
        }
        // if (!moonthMap.has(date.getMonth())) {
        //   moonthMap.set(date.getMonth(), [e]);
        // } else {
        //   moonthMap.set(
        //     date.getMonth(),
        //     moonthMap.get(date.getMonth()).push(e)
        //   );
        // }
      }
      return Array.from(dateMap);
    },
    getTime() {
      let now = this.$Moment();
      this.runningTime = now.diff(new Date("2021-9-1"), "days");
    },
  },
};
</script>

<style lang="sass">
@import '~vuetify/src/components/VTimeline/_variables.scss'
$timeline-dot-small-size: 10px!default
</style>