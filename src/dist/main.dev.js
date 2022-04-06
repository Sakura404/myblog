"use strict";

var _vue = _interopRequireDefault(require("vue"));

var _App = _interopRequireDefault(require("./App.vue"));

var _vuetify = _interopRequireDefault(require("./plugins/vuetify"));

var _index = _interopRequireDefault(require("./router/index.js"));

require("./styles.scss");

var _axios = _interopRequireDefault(require("axios"));

var _vueAxios = _interopRequireDefault(require("vue-axios"));

var _prismjs = _interopRequireDefault(require("prismjs"));

var _randomImg = _interopRequireDefault(require("./plugins/randomImg.js"));

var _moment = _interopRequireDefault(require("moment"));

require("moment/locale/zh-cn");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

_vue["default"].use(_prismjs["default"]);

_prismjs["default"].highlightAll();

_vue["default"].prototype.$randomImg = _randomImg["default"];
_vue["default"].prototype.$Moment = _moment["default"];

_vue["default"].use(_vueAxios["default"], _axios["default"]);

_vue["default"].config.productionTip = false;
new _vue["default"]({
  router: _index["default"],
  vuetify: _vuetify["default"],
  render: function render(h) {
    return h(_App["default"]);
  }
}).$mount('#app');