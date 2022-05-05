<template>
  <v-card
    hover
    class="scaleImg my-6"
    :dark="dark"
    :light="!dark"
    :color="color"
  >
    <div v-if="type == 'post'">
      <v-img
        class="white--text align-end"
        height="300px"
        gradient="rgba(0,0,0,0.2),rgba(0,0,0,0.2)"
        lazy-src
        :src="attachment ? attachment.url : $randomImg.cdnRandomImg()"
      >
        <v-card-title> {{ title }} </v-card-title>
        <v-card-subtitle class="white--text">
          <v-icon small color="white">mdi-clock</v-icon>
          {{ this.$Moment(date).fromNow() }}
          &nbsp;
          <v-icon small color="white"> mdi-pen</v-icon>
          {{ author }}
        </v-card-subtitle>
      </v-img>
      <v-card-text> {{ excerpt }}...... </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-row no-gutters>
          <v-col lg="6" cols="6">
            <v-chip
              v-for="term in termList"
              outlined
              link
              class="mr-1 mb-1"
              color="primary"
              @click="getPostByTermId(term.id)"
              :key="term.id"
              >{{ term.name }}</v-chip
            >
          </v-col>
          <v-spacer></v-spacer>
          <v-btn text :to="'/post/' + id"
            >阅读全文<v-icon>mdi-arrow-right</v-icon>
          </v-btn>
        </v-row>
      </v-card-actions>
    </div>
    <div v-else>
      <v-card-title>短句</v-card-title>
      <v-card-subtitle>{{ this.$Moment(date).fromNow() }}</v-card-subtitle>
      <v-card-text>
        <p>
          {{ content }}
        </p>
        <p class="text-end font-weight-bold">{{ author }}</p>
      </v-card-text>
    </div>
  </v-card>
  <!-- <v-card color="#bb6688" hover class="my-6">
    <v-card-title>短句</v-card-title>
    <v-card-subtitle>{{ this.$Moment().fromNow() }}</v-card-subtitle>
    <v-card-text>
      <p>
        你有信仰就年轻，疑惑就年老；有自信就年轻，畏惧就年老；有希望就年轻，绝望就年老；岁月使你皮肤起皱，但是失去了热忱，就损伤了灵魂。
      </p>
      <p class="text-end font-weight-bold">—— orga</p>
    </v-card-text>
  </v-card> -->
</template>
<script>
export default {
  name: "post-card",
  props: {
    id: Number,
    title: String,
    author: String,
    excerpt: String,
    date: String,
    type: String,
    attachment: Object,
    termList: Array,
    content: String,
    color: String,
    dark: Boolean,
  },
  data: () => ({}),
  methods: {
    getPostByTermId(termId) {
      this.$emit("getPostByTermId", termId);
    },
  },
};
</script>