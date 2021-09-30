<template>
  <v-row
    style="margin-top: 56px; height: 1000px"
    justify="center"
    class="no-gutters"
  >
    <v-col
      style="
        background-color: rgba(255, 255, 255, 0.1);
        backdrop-filter: blur(20px);
      "
      lg="6"
      cols="12"
    >
      <minesweeper></minesweeper
    ></v-col>
  </v-row>
</template>
<script>
import minesweeper from "../components/minesweeper.vue";
export default {
  components: { minesweeper },
};
</script>