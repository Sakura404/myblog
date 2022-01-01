<template>
  <v-card style="overflow: auto; max-height: 70vh" tile class="py-3 px-3">
    <h2 path="contents" class="mb-2 text--primary">目录</h2>
    <ul class="postnav pl-0 ml-3 mb-6">
      <li :style="navActive" class="nav-item-active"></li>
      <li
        style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap"
        v-for="(item, index) in navlist"
        :key="index"
        class="d-block nav-li"
        :class="`pl-${(item.deep - 3) * 2}`"
      >
        <a
          @mouseenter="mouseIn"
          @mouseleave="mouseOut"
          @mousedown="mouseClick"
          :style="getFont(item.deep)"
          class="nav-link"
          :href="item.href"
        >
          {{ item.text }}</a
        >
      </li>
    </ul>
  </v-card>
</template>
<script>
export default {
  props: { navlist: Array },
  data: () => ({
    list: [],
    navItemHoverLength: 0,
    navItemActiveLength: 0,
    navItemHoverHeight: 0,
    navItemActiveHeight: 0,
  }),
  methods: {
    mouseIn(e) {
      this.navItemHoverLength = e.currentTarget.offsetTop;
      this.navItemHoverHeight = e.currentTarget.offsetHeight;
    },
    mouseClick(e) {
      this.navItemActiveLength = e.currentTarget.offsetTop;
      this.navItemActiveHeight = e.currentTarget.offsetHeight;
    },
    mouseOut() {
      this.navItemHoverLength = this.navItemActiveLength;
      this.navItemHoverHeight = this.navItemActiveHeight;
    },
    getTocDepth(dom) {
      let top = document.querySelector("ul.mce-toc");
      if (dom.parentNode == top) return 1;
      if (dom.parentNode == document.querySelector("html")) return 0;
      return this.getTocDepth(dom.parentNode) + 1;
    },
    getFont(deep) {
      switch (deep) {
        case 4:
          return "font-size:1.5rem";
        case 6:
          return "font-size:1.25rem";
        case 8:
          return "font-size:1rem";
      }
    },
  },
  computed: {
    navActive() {
      return `transform: translateY(${this.navItemHoverLength}px); height: ${this.navItemHoverHeight}px`;
    },
  },
  mounted() {
    let s = document.querySelectorAll(".postnav li")[1];
    if (s) {
      console.log(123);
      this.navItemActiveHeight = s.offsetHeight;
      this.navItemHoverHeight = s.offsetHeight;
    }
  },
};
</script>
<style>
.postnav {
  border-left: 2px rgba(0, 0, 0, 0.3) solid;
  position: relative;
}
.nav-item-active {
  display: block;
  background: blue;
  position: absolute;
  top: 0;
  left: -2px;
  height: 24px;
  width: 2px;
  transition: 0.5s;
  transform: translateY(0);
}
html {
  scroll-behavior: smooth;
  scroll-padding-top: 60px;
}
.nav-link {
  text-decoration: none;
  font-weight: bold;
  color: orange !important;
}

.nav-link:hover {
  color: #1976d2 !important;
}
.nav-link:active {
  color: #1976d2 !important;
}

mce-toc {
  display: none;
}
</style>