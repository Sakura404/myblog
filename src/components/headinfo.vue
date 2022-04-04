<template>
  <div style="background-color: white" :style="headStyle">
    <div
      id="headinfo"
      :style="loginBackgroundSrc"
      style="height: 100vh"
    >
      <div
        :style="rotate_one"
        @mouseleave="rotateOff()"
        @mouseenter="rotateOn()"
        @mousemove="getclient($event)"
        :class="{ rotateoff: isrotate }"
        id="ones"
      >
        {{ sentence }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Headinfo",

  data() {
    return {
      headOpactiy: 1,
      isrotate: false,
      m_client: {
        X: 0,
        Y: 0,
      },
      windowSize: {
        x: 0,
        y: 0,
      },
      rotate_a: {
        x: 0,
        y: 0,
      },
      tops: 0,
      bg: this.$randomImg.cdnRandomImg(),
      sentence:
        "既然已经做出了选择，最好还是先假定自己是对的。焦虑未来和后悔过去，只经历一个就够了。",
    };
  },
  computed: {
    loginBackgroundSrc() {
      return `background-image:url('${this.bg}') `;
    },
    headStyle() {
      return `opacity: ${
        this.headOpactiy.toFixed(2) > 0.8
          ? 1
          : this.headOpactiy.toFixed(2) > 0.1
          ? this.headOpactiy.toFixed(2) * 2
          : 0
      } `;
    },
    headinfoheight() {
      return "height: " + (this.windowSize.y + 12) + "px";
    },
    rotate_one() {
      return `transform: perspective(1000px) rotateY(${this.rotate_a.y.toFixed(
        2
      )}deg)  rotateX(${this.rotate_a.x.toFixed(2)}deg)  scale3d(1, 1, 1);`;
    },
  },
  mounted: function () {
    this.onResize();
    this.$http
      .get(
        "http://api.tianapi.com/txapi/one/index?key=0b5ca2dd448bb6b4b8eb0da46a27d0d9"
      )
      .then((response) => {
        this.sentence = response.data.newslist[0].word;
      });
    let options = {
      threshold: [],
    };
    for (let i = 0; i <= 1.0; i += 0.02) {
      options.threshold.push(i);
    }
    let headObserver = new IntersectionObserver(this.headeEntries, options);
    headObserver.observe(document.querySelector("#headinfo"));
  },

  methods: {
    randomImg() {
      return `background-image: url(../assets/bg/${
        Math.floor(Math.random() * 16) + 1
      }.jpg)`;
    },
    headeEntries(entries) {
      this.headOpactiy = entries[0].intersectionRatio;
      this.$emit("observe", this.headOpactiy);
    },
    rotateOn() {
      this.isrotate = true;
      setTimeout(() => {
        this.isrotate = false;
      }, 2000);
    },
    rotateOff() {
      this.rotate_a.x = 0;
      this.rotate_a.y = 0;
      this.rotateOn();
    },
    getclient(ev) {
      this.m_client.X = ev.offsetX;
      this.m_client.Y = ev.offsetY;
      var dom = ev.currentTarget;
      let reg = 13;
      this.rotate_a.y =
        (ev.offsetX > dom.offsetWidth / 2 ? reg : -reg) *
        Math.abs(ev.offsetX / (dom.offsetWidth / 2) - 1);
      this.rotate_a.x =
        (ev.offsetY > dom.offsetHeight / 2 ? -reg : reg) *
        Math.abs(ev.offsetY / (dom.offsetHeight / 2) - 1);
      //   this.rotate_a.y = 100*(ev.offsetY - dom.offsetHeight / 2) / dom.offsetHeight;
      //   this.rotate_a.x = 100*(ev.offsetX - dom.offsetWidth / 2) / dom.offsetWidth;
    },
    onResize() {
      this.windowSize = { x: window.innerWidth, y: window.innerHeight };
    },
  },
};
</script>

<style>
.rotateoff {
  transition: all 400ms cubic-bezier(0.03, 0.98, 0.52, 0.99) 0s;
}
#ones {
  border-radius: 10px;
  margin: 0 auto;
  max-width: 700px;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  text-align: center;
  font-size: 16px;
  line-height: 30px;
  padding: 15px;
  position: relative;
  top: 50%;
  font-weight: 700;
}
.under:hover::before {
  transform: scaleX(0.9);
}
.under::before {
  content: "";
  left: 0;
  height: 2px;
  background: black;
  bottom: 10%;
  position: absolute;
  transform: scaleX(0);
  width: 100%;
  transition: all 0.5s ease-in-out;
}
#headinfo {
  background-image: url(../assets/dcbg.jpg);
  background-position: top center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  height: 1000px;
  transition: 0.2s;
}

#downbtn {
  width: 100%;
  height: 40px;
  font-size: 10px;
  position: absolute;
  text-align: center;
  bottom: 0px;
  transition: 0.5s;
  user-select: none;
  cursor: pointer;
  padding-top: 20px;
}

.arrow {
  width: 50px;
  height: 50px;
  border: 10px solid blue;
  border-left-color: transparent;
  border-bottom-color: transparent;
  transform: rotate(45deg);
}

#downbtn .mdui-icon {
  font-size: 40px;
}

#downbtn:hover {
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
