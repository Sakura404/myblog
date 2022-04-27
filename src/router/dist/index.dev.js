"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _vue = _interopRequireDefault(require("vue"));

var _vueRouter = _interopRequireDefault(require("vue-router"));

var _axios = _interopRequireDefault(require("axios"));

var _login = _interopRequireDefault(require("../views/login.vue"));

var _homePage = _interopRequireDefault(require("./homePage.js"));

var _adminPage = _interopRequireDefault(require("./adminPage.js"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

_vue["default"].use(_vueRouter["default"]);

var loginPage = {
  path: '/login',
  name: 'login',
  component: _login["default"]
};
var routes = [_adminPage["default"], loginPage, _homePage["default"], {
  path: '/*',
  redirect: '/'
}];
var router = new _vueRouter["default"]({
  routes: routes,
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: function scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return {
        top: 0
      };
    }
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
      //  console.log(token)
      _axios["default"].post('/api/users/isLogin/', token).then(function (res) {
        if (res.data.code === 10000) {
          next();
        } else throw new Error('未登录');
      })["catch"](function (err) {
        console.log(err);
        next({
          path: '/login',
          // 未登录则跳转至login页面
          query: {
            redirect: to.fullPath // 登陆成功后回到当前页面，这里传值给login页面，to.fullPath为当前点击的页面

          }
        });
      });
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