<template>
  <v-card class="py-3 px-3">
    <h2 path="contents" class="text--primary">目录</h2>
    <v-divider class="mb-4"></v-divider>
    <div style="overflow: auto; max-height: 60vh" class="nav-content">
      <ul class="postnav pl-1 ml-3 mb-6">
        <li
          :style="navActive"
          style="overflow: visible"
          class="nav-item-active"
        ></li>
        <li
          style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap"
          v-for="(item, index) in navlist"
          :key="index"
          class="d-block nav-li pa-2 mr-2"
          :class="`pl-${(item.deep - 3) * 2}`"
          @click="scrollTo(item.offsetTop)"
        >
          <a
            :id="item.href.substring(8)"
            :style="getFont(item.deep)"
            class="nav-link"
          >
            {{ item.text }}</a
          >
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
      // if (!dom) return;
      let offsetTop = dom.offsetTop;

      document.querySelector(".nav-content").scrollTop = offsetTop - 50;
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
          return "font-size:1.1rem";
        case 6:
          return "font-size:1rem";
        case 8:
          return "font-size:1rem";
      }
    },
    scrollTo(offsetTop) {
      document.documentElement.scrollTop = offsetTop;
    },
    scrollThrottle() {
      let lastTime = 0;
      let fn = this.scrollFollow();
      return function () {
        let nowTime = new Date().getTime();
        fn.call(this);
        if (nowTime - lastTime > 0) {
          lastTime = nowTime;
        }
      };
    },
    scrolldebounce(fn, delay) {
      let timer = null;
      return function () {
        clearTimeout(timer);
        timer = setTimeout(() => fn.call(this), delay);
      };
    },
    scrollFollow() {
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
            if (dom) this.observer(dom);
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
    window.addEventListener("scroll", this.scrollThrottle);
  },
  beforeDestroy() {
    // 离开该页面需要移除这个监听的事件，不然会报错
    console.log("destroy");
    window.removeEventListener("scroll", this.scrollThrottle);
  },
};
</script>
<style >
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
.nav-content {
  scrollbar-color: #6969dd #e0e0e0;
  scrollbar-width: 12px;
}
.nav-content::-webkit-scrollbar {
  width: 6px;
}

.nav-content::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 20px;
}

.nav-content::-webkit-scrollbar-thumb {
  background-color: #e4e4e4;
  border-radius: 20px;
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
.nav-li:hover {
  background-color: rgba(0, 0, 0, 0.1);
  border-radius: 3px;
}
mce-toc {
  display: none;
}
</style>