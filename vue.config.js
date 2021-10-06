module.exports = {
    devServer: {
		port: 8888,
	},

    transpileDependencies: [
		'vuetify'
	],
    chainWebpack: (config) => {
        config
            .plugin('html')
            .tap((args) => {
                args[0].title = 'Blog';
                return args;
            });
    },
    publicPath: './'
}
