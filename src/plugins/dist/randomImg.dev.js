"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;
var randomImg = {
  randomImg: function randomImg() {
    return require("../assets/bg/".concat(Math.floor(Math.random() * 16) + 1, ".jpg"));
  }
};
var _default = randomImg;
exports["default"] = _default;