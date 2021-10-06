"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _moment = _interopRequireDefault(require("moment"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var momentVue = {
  install: function install(Vue) {
    Vue.myGlobalMethod = function () {
      // 1. 添加全局方法或属性，如: vue-custom-element
      (0, _moment["default"])(); // 逻辑...
    };
  }
};
var _default = momentVue;
exports["default"] = _default;