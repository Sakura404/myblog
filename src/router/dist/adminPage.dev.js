"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

function _typeof(obj) { if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function _getRequireWildcardCache() { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

var work = function work() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/working.vue'));
  });
};

var adminpost = function adminpost() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/admin/admin-post.vue'));
  });
};

var dashboard = function dashboard() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/dashboard.vue'));
  });
};

var admin = function admin() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/admin/admin.vue'));
  });
};

var editor = function editor() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/admin/editor.vue'));
  });
};

var adminComment = function adminComment() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/admin/admin-comment.vue'));
  });
};

var adminImage = function adminImage() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../components/admin/admin-image.vue'));
  });
};

var adminPage = {
  path: '/admin',
  component: admin,
  meta: {
    requireAuth: true
  },
  children: [{
    path: 'dashboard',
    name: 'dashboard',
    component: dashboard
  }, {
    path: 'image',
    name: 'adminImage',
    component: adminImage
  }, {
    path: 'work',
    name: 'work',
    component: work
  }, {
    path: 'post',
    name: 'adminpost',
    component: adminpost
  }, {
    path: 'editor/:id',
    name: 'editorwithid',
    component: editor
  }, {
    path: 'editor',
    name: 'editor',
    component: editor
  }, {
    path: 'comment',
    name: 'adminComment',
    component: adminComment
  }, {
    path: '/',
    redirect: 'post'
  }]
};
var _default = adminPage;
exports["default"] = _default;