module.exports = {
  publicPath: './',
 devServer: {
        port: 8080,
        // 查阅 https://github.com/vuejs/vue-doc-zh-cn/vue-cli/cli-service.md#配置代理
        proxy: null, // string | Object
        before: app => {}
      }
}