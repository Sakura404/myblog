module.exports = {
	publicPath: './',
	devServer: {
		port: 8888,
		proxy: {
			'/myapi': {
				target: 'https://api.bilibili.com',
				changeOrigin: true,
				ws: true,
				pathRewrite: {
					'^/myapi': ''
				}
			}
		},
	}
}
