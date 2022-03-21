<template>
  <v-main>
    <v-row style="margin-top: 56px;min-height: 100vh"
      justify="center"
      no-gutters>
      <v-col style="
            background-color: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(20px);"
        lg="6"
        cols="12">
        <slot></slot>
      </v-col>
    </v-row>
  </v-main>
</template>
<script>
export default {
  name: "home-template",
};
</script>
