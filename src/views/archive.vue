<template>

  <home-template>
    <slot name="foot"></slot>
  </home-template>

</template>
<script>
import homeTemplate from "./home-template.vue";
export default {
  components: { homeTemplate },
};
</script>
