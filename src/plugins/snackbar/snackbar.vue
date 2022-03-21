<template>
  <div class="snackbar_list">
    <v-scroll-x-transition :key=i
      v-for="e,i in messageList">
      <div v-if="e.visible"
        :class="e.type"
        class="snackbar_item ma-2 pa-4 dark white--text">{{e.message}}

      </div>
    </v-scroll-x-transition>
  </div>
</template>

<script>
export default {
  data() {
    return {
      visible: true,
      message: "123",
      messageList: [{ visible: true, message: "1", type: "green" }],
    };
  },
  methods: {
    deleteMessage(e) {
      let _this = this;
      setTimeout(() => {
        if (!e.visible) {
          const index = _this.messageList.indexOf(e);
          _this.messageList.splice(index, 1);
        }
      }, 100);
    },
    toast(i) {
      setTimeout(() => {
        this.messageList[i].visible = true;
        setTimeout(() => {
          this.messageList[i].visible = false;
        }, 1000);
      }, 1000);
    },
  },
  mounted() {},
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