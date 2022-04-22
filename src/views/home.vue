<template>
  <div>
    <v-navigation-drawer clipped temporary v-model="drawer" app>
      <v-sheet class="sheet-linear white--text pa-2">
        <v-card-title></v-card-title>
        <v-avatar class="mb-4" color="grey darken-1" size="64"
          ><img src="../assets/mk.jpg"
        /></v-avatar>
        <p>个人邮箱:</p>
        <p>380487608@qq.com</p>
      </v-sheet>

      <v-divider></v-divider>

      <v-list>
        <v-list-item
          color="blue"
          v-for="[icon, text, link] in links"
          :to="link"
          :key="icon"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      clipped-left
      style="transition: background-color 0s"
      :color="toolcolor ? 'white' : 'rgba(0, 0, 0, 0)'"
      :dark="!toolcolor"
      :flat="toolflat"
      short
      fixed
      class="toolwhite"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"> </v-app-bar-nav-icon>
      <v-app-bar-title>散の華</v-app-bar-title>
      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-menu offset-y fixed bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="mr-1" v-bind="attrs" v-on="on" icon>
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list dense flat>
          <v-list-item to="/admin"> 登录 </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>

    <v-main>
      <v-container id="main-container" fluid class="pa-0">
        <headinfo v-if="$route.path == '/'" @observe="headobserve"></headinfo>
        <div :style="biyinapi ? mainimg : null" class="biyin">
          <router-view>
            <v-footer slot="foot" color="rgba(0,0,0,0.0)">
              <v-col class="text-center" cols="12">
                <strong
                  ><a
                    class="black--text"
                    href="https://beian.miit.gov.cn/"
                    target="_blank"
                    >粤ICP备2021170811号</a
                  ></strong
                >
              </v-col>
            </v-footer>
          </router-view>
        </div>
      </v-container>
    </v-main>
  </div>
</template>
<script>
import headinfo from "../components/headinfo.vue";
export default {
  components: { headinfo },

  data: () => ({
    drawer: false,
    toolcolor: true,
    toolcolorlock: false,
    toolflat: true,
    nai: true,
    one: "我们难过时，总是悲观地以为，我们这辈子都不会再变好了，后来才发现，总有一天，难过会消失的，人生会变好的。真的是有那么一天的。",
    links: [
      ["mdi-home", "首页", "/home"],
      ["mdi-sort-bool-descending", "时间轴", "/timelines"],
      ["mdi-file", "归档", "/archive"],
      ["mdi-gamepad-variant", "游戏", "/game"],
      ["mdi-link", "友情链接"],
      ["mdi-alert-octagon", "关于", "/about"],
    ],
    biyinapi: null,
  }),
  computed: {
    mainimg() {
      let url = this.biyinapi;
      const style = `background-image:url(${url})`;
      return style;
    },
  },

  methods: {
    headobserve(o) {
      var headheight = document.querySelector("#headinfo").clientHeight;
      if (Math.floor(headheight * o) <= 56) {
        this.toolcolor = true;
        this.toolflat = false;
      } else {
        this.toolcolor = false;
        this.toolflat = true;
      }
    },
    // handleScroll() {
    //   this.toolflat =
    //     (window.pageYOffset ||
    //       document.documentElement.scrollTop ||
    //       document.body.scrollTop) > 0
    //       ? true
    //       : false;
    // },
    onIntersect(entries) {
      // this.toolcolor = !entries[0].isIntersecting;
      this.toolflat = entries[0].isIntersecting;
    },
  },
  mounted: function () {
    this.$http.get("http://112.74.125.3:8888/bing").then((res) => {
      let imgSrc = `https://bing.com${res.data.images[0].url}`;
      const primise = new Promise((resolve, reject) => {
        let img = new Image();
        img.src = imgSrc;
        img.onload = () => {
          resolve(img);
        };
        img.onerror = () => {
          reject(new Error("图片加载失败"));
        };
      });
      primise.then((res) => {
        this.biyinapi = res.src;
      });
    });

    //  window.addEventListener("scroll", this.handleScroll, true);
  },
  created() {
    this.$watch(
      () => this.$route.name,
      (name) => {
        if (name != "home") {
          this.toolcolor = true;
          this.toolflat = false;
        }
      }
    );
  },

  destroyed() {
    // 离开该页面需要移除这个监听的事件，不然会报错
    //  window.removeEventListener("scroll", this.handleScroll);
  },
};
</script>
<style>
.white-hover:hover {
  background: white;
}
.headinfo {
  background-attachment: fixed;
  background-image: url("../assets/dcbg.jpg");
}
.biyin {
  background-image: url("../assets/food.png");
  background-attachment: fixed;
  background-repeat: repeat;
  background-position: center center;
  background-size: 1920px 100%;
  transition: background-image 1s;
}

code {
  background-color: rgba(1, 1, 1, 0) !important;
}
.bounce-enter-active {
  animation: bounce-in 1s;
}
.bounce-leave-active {
  animation: bounce-in 1s reverse;
}
@keyframes bounce-in {
  0% {
    transform: translateX(0);
  }
  50% {
    transform: translateX(-50vw);
  }
  100% {
    transform: translateX(-100vw);
  }
}
.sheet-linear {
  background: #ef32d9; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    rgb(239, 50, 217),
    rgb(137, 255, 253)
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    rgb(239, 50, 217),
    rgb(137, 255, 253)
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>
