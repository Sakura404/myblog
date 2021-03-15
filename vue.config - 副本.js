module.exports = {
    publicPath: './',

    devServer: {
		port: 8888,
	},

    pages: {
		homepage: {
			entry: "src/index/main.js",
			template: "public/index.html",
			filename: "index.html",
			title: "散の华",
		},
		management: {
			entry: "src/management/main.js",
			template: "public/management.html",
			filename: "management.html",
			title: "博客后台管理",
		}
	},

    transpileDependencies: [
      'vuetify'
    ]
}
