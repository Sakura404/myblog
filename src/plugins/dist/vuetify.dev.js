"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _vue = _interopRequireDefault(require("vue"));

var _framework = _interopRequireDefault(require("vuetify/lib/framework"));

require("@mdi/font/css/materialdesignicons.css");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

// Ensure you are using css-loader
_vue["default"].use(_framework["default"]);

var opts = {};

var _default = new _framework["default"]({
  icons: {
    iconfont: 'mdi' // 默认值 - 仅用于展示目的

  },
  opts: opts
});

exports["default"] = _default;