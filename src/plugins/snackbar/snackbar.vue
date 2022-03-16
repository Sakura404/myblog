<template>
  <v-snackbar v-model="visible"
    timeout=1500
    bottom>{{message}}</v-snackbar>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      message: "",
    };
  },
};
</script>

<style>
</style>