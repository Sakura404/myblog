"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _snackbar = _interopRequireDefault(require("./snackbar.vue"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var _default = {
  // 导出一个对象，里面包含vue注册插件所调用的方法install
  install: function install(Vue) {
    var SnackbarConstructor = Vue.extend(_snackbar["default"]);
    var instance = new SnackbarConstructor(); // 生成一个该子类的实例

    instance.$mount(); // 将这个实例挂载在新创建的div上，并加入到body中

    document.querySelector("#blogSncakbar").appendChild(instance.$el);
    var $snackbar = {
      success: function success(mes) {
        if (instance.visible) return;
        instance.message = mes;
        instance.type = "success";
        instance.visible = true;
      },
      error: function error(mes) {
        if (instance.visible) return;
        instance.message = mes;
        instance.type = "error";
        instance.visible = true;
      },
      info: function info(mes) {
        if (instance.visible) return;
        instance.message = mes;
        instance.type = "info";
        instance.visible = true;
      },
      warning: function warning(mes) {
        if (instance.visible) return;
        instance.message = mes;
        instance.type = "warning";
        instance.visible = true;
      }
    };
    Vue.prototype.$snackbar = $snackbar;
  }
};
exports["default"] = _default;