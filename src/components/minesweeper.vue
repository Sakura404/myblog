<template>
  <div v-resize="onResize" class="minesweeper text-center">
    <v-dialog persistent :value="gameState != false" max-width="600">
      <v-card>
        <v-row align-content="center" no-gutters justify="center">
          <v-col class="text-center text-h"> {{ gameState }}</v-col>
        </v-row>
        <v-card-actions class="d-flex justify-space-around">
          <v-btn
            dark
            class="flex-grow-1"
            v-for="(grade, index) in gameGrade"
            :color="grade.color"
            :key="index"
            @click="gradeSelect(grade)"
            >{{ grade.name }}</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>

    <div class="sweeperline" v-for="(y, i) of gamearr" :key="i">
      <span
        class="sweeperblock sweeperblock-hidden"
        :style="blocksize"
        v-for="(x, n) of y"
        :key="n"
        @click="
          if (initflag) {
            initGame(i, n);
          }
          dig(i, n, 'center');
          setAnimation(i, n);
          victory();
          defeat(i, n);
        "
        ><v-icon :style="blocksize" style="height: 40px; width: 40px">{{
          x ? x : "&nbsp;"
        }}</v-icon
        ></span
      >
    </div>
  </div>
</template>
<script>
export default {
  data: () => ({
    Vertical: 20,
    Horizontal: 20,
    mineTotal: 40,
    gamearr: null,
    gameState: "游戏开始",
    gameStar: true,
    gameWindow: window.innerWidth,
    gameVictory: false,
    gameDefeat: false,
    gameGrade: [
      { name: "初级", width: 8, height: 8, mine: 10, color: "green" },
      { name: "中级", width: 16, height: 16, mine: 40, color: "blue" },
      { name: "高级", width: 16, height: 30, mine: 99, color: "red" },
    ],
    initflag: true,
  }),
  computed: {
    blocksize() {
      let width;
      let height;
      let viewWidth = this.gameWindow;
      if (viewWidth > 1264) width = 35 / this.Horizontal;
      else width = 80 / this.Horizontal;
      height = width;
      if (width >= 0) {
        this.onResize();
      }
      return `width:${width >= 0 ? width : 25}vw;height: ${
        height >= 0 ? height : 25
      }vw;`;
    },
  },
  methods: {
    gradeSelect(grade) {
      this.Vertical = grade.height;
      this.Horizontal = grade.width;
      this.mineTotal = grade.mine;
      this.onResize();
      this.initWindow();
      this.gameState = false;
      this.initflag = true;
    },
    initWindow() {
      this.gamearr = new Array(this.Vertical);
      for (let i = 0; i < this.Vertical; i++) {
        this.gamearr[i] = new Array(this.Horizontal);
      }
      //初值
      for (let i = 0; i < this.Vertical; i++) {
        for (let e = 0; e < this.Horizontal; e++) {
          this.gamearr[i][e] = 0;
        }
      }
      let block = document.getElementsByClassName("sweeperblock");
      for (let a of block)
        if (!a.classList.contains("sweeperblock-hidden")) {
          a.classList.remove("sweeperblock-active");
          a.classList.add("sweeperblock-hidden");
          a.style.setProperty("transition-delay", `0s`);
        }
    },
    initGame(row, col) {
      if (this.initflag) {
        this.initflag = false;
        for (let i = 0; i < this.Vertical; i++) {
          for (let e = 0; e < this.Horizontal; e++) {
            this.gamearr[i][e] = 0;
          }
        }
        //炸弹生成

        let mineXY = new Set();
        const minelength = this.Vertical.toString().length;
        while (mineXY.size < this.mineTotal) {
          let locationX = Math.floor(Math.random() * this.Horizontal);
          let locationY = Math.floor(Math.random() * this.Vertical);
          if (locationX == col && locationY == row) {
            continue;
          }
          mineXY.add(
            (
              locationX +
              locationY * (0.1 ** minelength).toFixed(minelength)
            ).toFixed(minelength)
          );
        }
        console.log(mineXY);
        let minelocations = new Array();
        for (let XY of Array.from(mineXY)) {
          var t = new Array();
          XY.toString()
            .split(".")
            .forEach((element) => {
              t.push(parseInt(element));
            });
          minelocations.push(t);
        }

        //console.log(minelocations);
        let around = [-1, 0, 1];
        for (let i = 0; i < this.mineTotal; i++) {
          this.gamearr[minelocations[i][1]][minelocations[i][0]] = "mdi-mine";
          for (let r of around)
            for (let l of around) {
              if (
                minelocations[i][0] + r >= 0 &&
                minelocations[i][0] + r < this.Horizontal &&
                minelocations[i][1] + l >= 0 &&
                minelocations[i][1] + l < this.Vertical &&
                this.gamearr[minelocations[i][1] + l][
                  minelocations[i][0] + r
                ] != "mdi-mine"
              )
                this.gamearr[minelocations[i][1] + l][
                  minelocations[i][0] + r
                ]++;
            }
        }
        //
        this.$forceUpdate();
      }
    },
    dig(y, x) {
      let blockItem = this.gamearr[y][x];
      let blockDom =
        document.getElementsByClassName("sweeperline")[y].children[x];
      //检测方块是否被打开防止反复递归
      if (
        blockItem != "mdi-mine" &&
        blockDom.classList.contains("sweeperblock-hidden")
      ) {
        blockDom.classList.remove("sweeperblock-hidden");
        blockDom.classList.add("sweeperblock-active");
        if (blockItem == 0) {
          let around = [-1, 0, 1]; //检测周围九格
          for (let l of around) {
            for (let r of around) {
              //检测数组下标是否越界，跳过自身递归
              if (
                !(r == 0 && l == 0) &&
                y + l >= 0 &&
                x + r >= 0 &&
                y + l < this.Vertical &&
                x + r < this.Horizontal
              ) {
                this.dig(y + l, x + r);
              }
            }
          }
        }
        // if (blockItem == 0) {
        //   if (y + 1 < this.Vertical && way != "up") this.dig(y + 1, x, "down");
        //   if (y - 1 >= 0 && way != "down") this.dig(y - 1, x, "up");
        //   if (x + 1 < this.Horizontal && way != "left")
        //     this.dig(y, x + 1, "right");
        //   if (x - 1 >= 0 && way != "right") this.dig(y, x - 1, "left");
        // }
      }
    },
    setAnimation(y, x) {
      /*
       对每个方块用勾股定理计算与点击方块的距离，距离和动画延时成正相关
       */
      for (let v = 0; v < this.Vertical; v++)
        for (let h = 0; h < this.Horizontal; h++) {
          let range = Math.sqrt(Math.pow(y - v, 2) + Math.pow(x - h, 2));
          let blockDom =
            document.getElementsByClassName("sweeperline")[v].children[h];
          blockDom.setAttribute(
            "style",
            `transition-delay:${(range * 0.1).toFixed(2)}s`
          );
          blockDom.children[0].style.setProperty(
            "transition-delay",
            `${range * 0.1}s`
          );
        }
    },
    onResize() {
      this.gameWindow = window.innerWidth;
    },
    flagMark(e, row, col) {
      if (e.button == 2) {
        e.currentTarget.appendChild("v-icon");
        this.$set(this.gamearr[row], col, "mdi-flag");
        this.$forceUpdate();
        e.style.style.setProperty;
      }
    },
    defeat(y, x) {
      let blockItem = this.gamearr[y][x];
      let blockDom =
        document.getElementsByClassName("sweeperline")[y].children[x];
      blockDom.classList.remove("sweeperblock-hidden");
      blockDom.classList.add("sweeperblock-active");
      if (blockItem == "mdi-mine") this.gameState = "游戏结束";
    },
    victory() {
      if (
        document.getElementsByClassName("sweeperblock-hidden").length ==
        this.mineTotal
      ) {
        this.gameState = "游戏胜利";
      }
    },
    // setAnimation(y, x) {
    //   let around = [1, 0, -1];
    //   document
    //     .getElementsByClassName("sweeperline")[y].children[x].setAttribute(
    //       "style",
    //       `transition-delay:0.2s`
    //     );
    //   for (
    //     let i = 1;
    //     y - i >= 0 ||
    //     y + i < this.Vertical ||
    //     x - i >= 0 ||
    //     x + i < this.Horizontal;
    //     i++
    //   ) {
    //     for (let l1 of around) {
    //       for (let r1 of around) {
    //         if (l1 == 0 && r1 == 0) break;

    //         if (
    //           y + i * l1 >= 0 &&
    //           y + i * l1 < this.Vertical &&
    //           x + i * r1 >= 0 &&
    //           x + i * r1 < this.Horizontal
    //         ) {
    //           let blockDom =
    //             document.getElementsByClassName("sweeperline")[y + i * l1]
    //               .children[x + i * r1];

    //           blockDom.setAttribute(
    //             "style",
    //             `transition-delay:${0.1 + i * 0.1}s`
    //           );
    //         }
    //       }
    //     }
    //   }
    // },
  },
  mounted: function () {
    this.initWindow();
    this.gamewindow =
      document.getElementsByClassName("minesweeper")[0].offsetWidth;
    this.$nextTick(function () {});
    // document.getElementsByClassName("minesweeper")[0].oncontextmenu =
    //   function () {
    //     return false;
    //   };
  },
};
</script>
<style>
.sweeperblock {
  position: relative;
  transition-duration: 0.3s;
  display: inline-block;
  margin: 1px;
  box-shadow: 1px 1px 2px black;
  background-color: darkorange;
  color: #000;
}
.sweeperblock i {
  transition-duration: 0.3s;
}
.sweeperblock-flag i {
  visibility: visible;
}
.sweeperblock-hidden i {
  color: rgba(1, 1, 1, 0);
  visibility: hidden;
}
.sweeperblock-active {
  background-color: rgb(221, 217, 217) !important;
}
/* .sweeperblock:hover {
  background-color: rgb(82, 81, 81);
} */
.sweeperblock:focus {
  background-color: gray;
}
.minesweeper {
  display: inline-block;
  width: auto;
  line-height: 0;
  white-space: pre-line;
}
.minesweeper div {
  display: inline-block;
  height: auto;
  width: 100%;
  white-space: pre-line;
}
.text-colorfull {
  background: linear-gradient(to right, pink, purple, deepskyblue);
  -webkit-background-clip: text;
  background-clip: text;
  font-size: large;
  font-weight: 700;
  color: transparent;
}
.dialogshadow {
  background-color: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(20px);
}
.mineshake {
  animation: shake 1s 1;
}

@keyframes shake {
  /* 水平抖动，核心代码 */
  10%,
  90% {
    transform: translate3d(-1px, 0, 0);
  }
  20%,
  80% {
    transform: translate3d(+2px, 0, 0);
  }
  30%,
  70% {
    transform: translate3d(-4px, 0, 0);
  }
  40%,
  60% {
    transform: translate3d(+4px, 0, 0);
  }
  50% {
    transform: translate3d(-4px, 0, 0);
  }
}
</style>
