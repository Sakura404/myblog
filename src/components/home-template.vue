<template>
  <v-row
    style="margin-top: 56px; min-height: 100vh"
    justify="center"
    no-gutters
    
  >
    <v-col
      style="
        background-color: rgba(255, 255, 255, 0.8);
        backdrop-filter: blur(20px);
      "
      class="pa-6"
      lg="6"
      cols="12"
    >
      <slot></slot>
    </v-col>
  </v-row>
</template>
<script>
export default {
  name: "home-template",
};
</script>
