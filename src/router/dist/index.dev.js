"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _vue = _interopRequireDefault(require("vue"));

var _vueRouter = _interopRequireDefault(require("vue-router"));

var _working = _interopRequireDefault(require("../components/working.vue"));

var _adminPost = _interopRequireDefault(require("../components/admin/admin-post.vue"));

var _dashboard = _interopRequireDefault(require("../components/dashboard.vue"));

var _admin = _interopRequireDefault(require("../components/admin/admin.vue"));

var _home = _interopRequireDefault(require("../views/home.vue"));

var _editor = _interopRequireDefault(require("../components/admin/editor.vue"));

var _post = _interopRequireDefault(require("../components/post.vue"));

var _postlist = _interopRequireDefault(require("../views/postlist.vue"));

var _login = _interopRequireDefault(require("../views/login.vue"));

var _game = _interopRequireDefault(require("../views/game.vue"));

var _adminComment = _interopRequireDefault(require("../components/admin/admin-comment.vue"));

var _adminImage = _interopRequireDefault(require("../components/admin/admin-image.vue"));

var _timelines = _interopRequireDefault(require("../views/timelines.vue"));

var _archive = _interopRequireDefault(require("../views/archive.vue"));

var _about = _interopRequireDefault(require("../views/about.vue"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

_vue["default"].use(_vueRouter["default"]);

var routes = [{
  path: '/admin',
  component: _admin["default"],
  meta: {
    requireAuth: true
  },
  children: [{
    path: 'dashboard',
    name: 'dashboard',
    component: _dashboard["default"]
  }, {
    path: 'image',
    name: 'adminImage',
    component: _adminImage["default"]
  }, {
    path: 'work',
    name: 'work',
    component: _working["default"]
  }, {
    path: 'post',
    name: 'adminpost',
    component: _adminPost["default"]
  }, {
    path: 'editor/:id',
    name: 'editorwithid',
    component: _editor["default"]
  }, {
    path: 'editor',
    name: 'editor',
    component: _editor["default"]
  }, {
    path: 'comment',
    name: 'adminComment',
    component: _adminComment["default"]
  }, {
    path: '/',
    redirect: 'post'
  }]
}, {
  path: '/login',
  name: 'login',
  component: _login["default"]
}, {
  path: '/',
  component: _home["default"],
  children: [{
    path: '/',
    name: 'home',
    component: _postlist["default"]
  }, {
    path: '/post/:id',
    name: 'post',
    component: _post["default"]
  }, {
    path: '/game',
    name: 'game',
    component: _game["default"]
  }, {
    path: '/timelines',
    name: 'timelines',
    component: _timelines["default"]
  }, {
    path: '/archive',
    name: 'archive',
    component: _archive["default"]
  }, {
    path: '/about',
    name: 'about',
    component: _about["default"]
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
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: function scrollBehavior() {
    return {
      x: 0,
      y: 0
    };
  }
});
router.beforeEach(function (to, from, next) {
  if (to.matched.some(function (res) {
    return res.meta.requireAuth;
  })) {
    // 验证是否需要登陆
    var token = getCookie('LOGIN_TOKEN');

    if (token) {
      // 查询本地存储信息是否已经登陆 
      console.log(token);
      next();
    } else {
      next({
        path: '/login',
        // 未登录则跳转至login页面
        query: {
          redirect: to.fullPath // 登陆成功后回到当前页面，这里传值给login页面，to.fullPath为当前点击的页面

        }
      });
    }
  } else {
    next();
  }
});

function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');

  for (var i = 0; i < ca.length; i++) {
    var c = ca[i].trim();
    if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
  }

  return "";
}

var _default = router;
exports["default"] = _default;