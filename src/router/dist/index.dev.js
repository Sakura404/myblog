"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _vue = _interopRequireDefault(require("vue"));

var _vueRouter = _interopRequireDefault(require("vue-router"));

var _working = _interopRequireDefault(require("../components/working.vue"));

var _adminPost = _interopRequireDefault(require("../components/admin-post.vue"));

var _dashboard = _interopRequireDefault(require("../components/dashboard.vue"));

var _navm = _interopRequireDefault(require("../components/navm.vue"));

var _home = _interopRequireDefault(require("../views/home.vue"));

var _edior = _interopRequireDefault(require("../components/edior.vue"));

var _post = _interopRequireDefault(require("../components/post.vue"));

var _postpage = _interopRequireDefault(require("../views/postpage.vue"));

var _game = _interopRequireDefault(require("../views/game.vue"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

_vue["default"].use(_vueRouter["default"]);

var routes = [{
  path: '/admin',
  component: _navm["default"],
  children: [{
    path: 'dashboard',
    name: 'dashboard',
    component: _dashboard["default"]
  }, {
    path: 'work',
    name: 'work',
    component: _working["default"]
  }, {
    path: 'post',
    name: 'post',
    component: _adminPost["default"]
  }, {
    path: 'edior',
    name: 'edior',
    component: _edior["default"]
  }, {
    path: '/',
    redirect: 'dashboard'
  }]
}, {
  path: '/',
  component: _home["default"],
  children: [{
    path: '/',
    name: 'home',
    component: _postpage["default"]
  }, {
    path: '/post',
    name: 'post',
    component: _post["default"]
  }, {
    path: '/game',
    name: 'game',
    component: _game["default"]
  }, {
    path: '/*',
    redirect: '/'
  }]
}, {
  path: '/*',
  redirect: '/'
}];
var router = new _vueRouter["default"]({
  routes: routes,
  base: process.env.BASE_URL,
  scrollBehavior: function scrollBehavior() {
    return {
      x: 0,
      y: 0
    };
  }
});
var _default = router;
exports["default"] = _default;