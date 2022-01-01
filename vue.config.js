module.exports = {
    devServer: {
        port: 8888,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:1145', // 后端提供给你的接口地址
                ws: true,
                pathRewrite: {
                    '^/api': '/api' // 代理api使用方法=> /proxyApi/test/where
                }
            }
        },
    }
    ,
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
    outputDir: './myblog/src/main/resources/static'
}
