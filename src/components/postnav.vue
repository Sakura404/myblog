<template>
  <v-card class="py-3 px-3">
    <h2 path="contents"
      class="text--primary">目录
    </h2>
    <v-divider class="mb-2"></v-divider>
    <div style="overflow: auto; max-height: 60vh">
      <ul class="postnav pl-1 ml-3 mb-6">
        <li :style="navActive"
          style="overflow:visible ;"
          class="nav-item-active"></li>
        <li style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap"
          v-for="(item, index) in navlist"
          :key="index"
          class="d-block nav-li"
          :class="`pl-${(item.deep - 3) * 2}`">
          <a :id="item.href.substring(8)"
            :style="getFont(item.deep)"
            class="nav-link"
            @click="scrollTo(item.offsetTop)">
            {{ item.text }}</a>
        </li>
      </ul>
    </div>
  </v-card>
</template>
<script>
export default {
  props: { navlist: Array },
  data: () => ({
    list: [],
    navItemHoverTop: 0,
    navItemActiveTop: 0,
    navItemHoverHeight: 0,
    navItemActiveHeight: 0,
    navLastItem: null,
  }),
  methods: {
    mouseIn(e) {
      this.navItemHoverTop = e.currentTarget.offsetTop;
      this.navItemHoverHeight = e.currentTarget.offsetHeight;
    },
    mouseClick(e) {
      this.navItemActiveTop = e.currentTarget.offsetTop;
      this.navItemActiveHeight = e.currentTarget.offsetHeight;
      if (this.navLastItem)
        this.navLastItem.classList.remove("nav-link-active");
      e.currentTarget.classList.add("nav-link-active");
      this.navLastItem = e.currentTarget;
    },
    observer(dom) {
      this.navItemActiveTop = dom.offsetTop;
      this.navItemActiveHeight = dom.offsetHeight;
      if (this.navLastItem)
        this.navLastItem.classList.remove("nav-link-active");
      dom.classList.add("nav-link-active");
      this.navLastItem = dom;
      this.mouseOut();
    },
    mouseOut() {
      this.navItemHoverTop = this.navItemActiveTop;
      this.navItemHoverHeight = this.navItemActiveHeight;
    },
    getTocDepth(dom) {
      let top = document.querySelector("ul.mce-toc");
      if (dom.parentNode == top) return 1;
      if (dom.parentNode == document.querySelector("html")) return 0;
      return this.getTocDepth(dom.parentNode) + 1;
    },
    getTocHeight(id) {
      document.getElementById(id).offsetHeight;
    },
    getFont(deep) {
      switch (deep) {
        case 4:
          return "font-size:1.3rem";
        case 6:
          return "font-size:1.2rem";
        case 8:
          return "font-size:1rem";
      }
    },
    scrollTo(offsetTop) {
      document.documentElement.scrollTop = offsetTop ;
    },
    handleScroll() {
      const scrollTop =
        document.documentElement.scrollTop ||
        document.body.scrollTop ||
        window.pageYOffset;

      if (this.navlist)
        for (let i = 1; i < this.navlist.length; i++) {
          if (
            scrollTop >= this.navlist[i - 1].offsetTop &&
            scrollTop < this.navlist[i].offsetTop
          ) {
            let dom = document.getElementById(
              this.navlist[i - 1].href.substring(8)
            );
            this.observer(dom);
            break;
          }
          if (
            i == this.navlist.length - 1 &&
            scrollTop >= this.navlist[i].offsetTop
          ) {
            let dom = document.getElementById(
              this.navlist[i].href.substring(8)
            );
            this.observer(dom);
          }
        }
    },
  },
  computed: {
    navActive() {
      return `transform: translateY(${this.navItemHoverTop}px); height: ${this.navItemHoverHeight}px`;
    },
  },
  mounted() {
    let s = document.querySelectorAll(".postnav li")[1];
    if (s) {
      this.navItemActiveHeight = s.offsetHeight;
      this.navItemHoverHeight = s.offsetHeight;
    }
    window.addEventListener("scroll", this.handleScroll, true);
  },
  destroyed() {
    // 离开该页面需要移除这个监听的事件，不然会报错
    window.removeEventListener("scroll", this.handleScroll);
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
  color: black !important;
}

.nav-link:hover {
  color: #1976d2 !important;
}
.nav-link-active {
  color: #1976d2 !important;
}

mce-toc {
  display: none;
}
</style>