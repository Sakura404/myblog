"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

function _typeof(obj) { if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function _getRequireWildcardCache() { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

var home = function home() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../views/home'));
  });
};

var article = function article() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/post.vue'));
  });
};

var postlist = function postlist() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../views/postlist.vue'));
  });
};

var timelines = function timelines() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../views/timelines.vue'));
  });
};

var archive = function archive() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../views/archive.vue'));
  });
};

var about = function about() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../views/about.vue'));
  });
};

var game = function game() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../views/game.vue'));
  });
};

var homePage = {
  path: '/',
  component: home,
  children: [{
    path: '/',
    name: 'home',
    component: postlist
  }, {
    path: '/post/:id',
    name: 'post',
    component: article
  }, {
    path: '/game',
    name: 'game',
    component: game
  }, {
    path: '/timelines',
    name: 'timelines',
    component: timelines
  }, {
    path: '/archive',
    name: 'archive',
    component: archive
  }, {
    path: '/about',
    name: 'about',
    component: about
  }, {
    path: '/*',
    redirect: '/'
  }]
};
var _default = homePage;
exports["default"] = _default;