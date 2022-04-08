const CompressionPlugin = require('compression-webpack-plugin');
const productionGzipExtensions = /\.(js|css|json|txt|html|ico|svg)(\?.*)?$/i;

/** 开发环境 */
const DEV = process.env.NODE_ENV !== 'production';
module.exports = {
    devServer: {
        port: 8888,
        proxy: {
            '/api': {
                //   target: 'http://senkaryouran.top:1145', // 后端提供给你的接口地址
                target: 'http://localhost:1145',
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
        if (!DEV) {
            config.plugin('compressionPlugin')
                .use(new CompressionPlugin({
                    filename: "[path][base].gz",
                    algorithm: "gzip",
                    test: productionGzipExtensions,  //所有匹配此{RegExp}的资产都会被处理
                    threshold: 512,   // 只处理大于此大小的资产。以字节为单位
                    minRatio: 0.8,    //只有压缩好这个比率的资产才能被处理
                    deleteOriginalAssets: false //是否删除原资源
                }))
        }
    },
    outputDir: './dist',
    productionSourceMap: false,
    configureWebpack: {
    }
}
