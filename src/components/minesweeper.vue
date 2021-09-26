<template>
  <div class="minesweeper text-center">
    <div class="sweeperline" v-for="(y, i) of gamearr" :key="i">
      <span
        class="sweeperblock sweeperblock-hidden"
        v-for="(x, n) of y"
        :key="n"
        @click="dig(i, n, 'center'), setAnimation(i, n)"
        ><v-icon style="height: 40px; width: 40px" x-large>{{
          x ? x : "&nbsp;"
        }}</v-icon
        >&nbsp;</span
      >
    </div>
  </div>
</template>
<script>
export default {
  data: () => ({
    Vertical: 20,
    Horizontal: 20,
    mineTotal: 50,
    gamearr: null,
    test: 0,
  }),
  methods: {
    initGame() {
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
      //炸弹生成
      let mineXY = new Set();
      const minelength = this.mineTotal.toString().length;
      while (mineXY.size < this.mineTotal) {
        let locationX = Math.floor(Math.random() * this.Horizontal);
        let locationY =
          Math.floor(Math.random() * (this.Vertical - 1)) *
          (0.1 ** minelength).toFixed(minelength);
        mineXY.add((locationX + locationY).toFixed(minelength));
      }
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

      console.log(minelocations);
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
              this.gamearr[minelocations[i][1] + l][minelocations[i][0] + r] !=
                "mdi-mine"
            )
              this.gamearr[minelocations[i][1] + l][minelocations[i][0] + r]++;
          }
      }
      //
    },
    dig(y, x, way) {
      let blockItem = this.gamearr[y][x];
      let blockDom =
        document.getElementsByClassName("sweeperline")[y].children[x];

      if (
        blockItem != "mdi-mine" &&
        !blockDom.classList.contains("sweeperblock-active")
      ) {
        blockDom.classList.remove("sweeperblock-hidden");
        blockDom.classList.add("sweeperblock-active");
        if (blockItem == 0) {
          console.log(this.test++);
          if (y + 1 < this.Vertical && way != "up") this.dig(y + 1, x, "down");
          if (y - 1 >= 0 && way != "down") this.dig(y - 1, x, "up");
          if (x + 1 < this.Horizontal && way != "left")
            this.dig(y, x + 1, "right");
          if (x - 1 >= 0 && way != "right") this.dig(y, x - 1, "left");
        }
      }
    },
    setAnimation(y, x) {
      for (let v = 0; v < this.Vertical; v++)
        for (let h = 0; h < this.Horizontal; h++) {
          let range = Math.sqrt(Math.pow(y - v, 2) + Math.pow(x - h, 2));
          let blockDom =
            document.getElementsByClassName("sweeperline")[v].children[h];
          blockDom.setAttribute("style", `transition-delay:${(range * 0.1).toFixed(2)}s`);
          blockDom.children[0].style.setProperty(
            "transition-delay",
            `${range * 0.1}s`
          );
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
    this.initGame();
  },
};
</script>
<style>
.sweeperblock {
  transition-duration: 0.3s;
  display: inline-block;
  margin: 1px;
  box-shadow: 1px 1px 1px black;
  background-color: rgb(150, 150, 150);
  color: #000;
  width: 40px;
  height: 40px;
}
.sweeperblock i {
  transition-duration: 0.3s;
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
</style>
