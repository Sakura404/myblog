"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;
var end = 16,
    start = 1;
var randomImg = {
  cdnRandomImg: function cdnRandomImg() {
    return require("../assets/docsmall/".concat(Math.floor(Math.random() * end) + start, ".jpg"));
  },
  RandomImg: function RandomImg(indexd) {
    var random = Math.floor(Math.random() * end) + start;

    while (random === indexd) {
      random = Math.floor(Math.random() * end) + start;
      if (random) break;
    }
    return "https://cdn.jsdelivr.net/gh/Sakura404/myblog/src/assets/docsmall/".concat(random, ".jpg");
  }
};
var _default = randomImg;
exports["default"] = _default;