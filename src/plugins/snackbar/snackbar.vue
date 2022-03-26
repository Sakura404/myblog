<template>
  <div>
    <v-snackbar :color="type"
      timeout="1500"
      v-model="visible">
      <h3 > {{message}}</h3>
    </v-snackbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      message: "",
      type: "",
    };
  },
};
</script>

<style>
.snackbar_list {
  display: flex;
  align-items: flex-end;
  flex-direction: row-reverse;
  flex-wrap: wrap;
  position: absolute;
  bottom: 0;
  right: 0;
  max-width: 600px;
  min-width: 367px;
}
.snackbar_item {
  width: 100%;
  max-height: 87px;
  min-height: 80px;
  border-radius: 10px;
  line-height: 50px;
}
</style>