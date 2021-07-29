<template>
  <div id="headinfo" v-resize="onResize" :style="headinfoheight">
    <div id="ones">
      {{ sentence }}
    </div>
  </div>
</template>

<script>
export default {
  name: "Headinfo",
  data() {
    return {
      windowSize: {
        x: 0,
        y: 0,
      },
      tops: 0,
      sentence:
        "既然已经做出了选择，最好还是先假定自己是对的。焦虑未来和后悔过去，只经历一个就够了。",
    };
  },
  computed: {
    headinfoheight() {
      return "height: " + (this.windowSize.y + 12) + "px";
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
  },
  methods: {
    onResize() {
      this.windowSize = { x: window.innerWidth, y: window.innerHeight };
    },
  },
};
</script>

<style>
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

#headinfo {
  background-image: url(../assets/dcbg.jpg);
  background-position: top center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size:cover;
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

#downbtn .mdui-icon {
  font-size: 40px;
}

#downbtn:hover {
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
