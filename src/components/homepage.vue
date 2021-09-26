<template>
  <div>
    <headinfo class="d-md-block"></headinfo>
    <v-row no-gutters>
      <v-spacer></v-spacer>
      <v-col
        v-intersect="onIntersect"
        style="
          background-color: rgba(255, 255, 255, 0.1);
          backdrop-filter: blur(20px);
        "
        class="px-6"
        lg="6"
        cols="12"
      >
        <v-card hover class="my-4">
          <v-img
            class="white--text align-end"
            height="300px"
            position="top top"
            src="../assets/mk.jpg"
          >
            <v-card-title> asdf </v-card-title></v-img
          >
          <v-card-text> dfsssssssss </v-card-text>
          <v-card-actions>s</v-card-actions>
        </v-card>
        <v-card hover class="my-4">
          <v-img
            class="white--text align-end"
            height="300px"
            src="../assets/73718346_p0.png"
          >
            <v-card-title> オレンジ </v-card-title>
            <v-card-subtitle class="white--text"
              ><v-icon small color="white">mdi-clock</v-icon>
              2020-7-31</v-card-subtitle
            ></v-img
          >
          <v-card-text>
            "Turns out semicolon-less style is easier and safer in TS because
            most gotcha edge cases are type invalid as well."
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-row no-gutters>
              <v-col lg="6" cols="6">
                <v-chip outlined class="mr-1 mb-1" color="primary">日常</v-chip>
                <v-chip outlined class="mr-1 mb-1" color="primary"
                  >念念碎</v-chip
                > </v-col 
              ><v-spacer     ></v-spacer>
              <v-btn text to="/post"
                >阅读全文 <v-icon>mdi-arrow-right</v-icon></v-btn
              >
            </v-row>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
  </div>
</template>

<script>
import headinfo from "../components/headinfo.vue";
export default {
  components: { headinfo },
  methods: {
    onIntersect(entries) {
      this.$emit("onIntersect", entries);
    },
  },
};
</script>