const path = require('path')

module.exports = {
    entry: './web/page/index.js' //入口文件
    ,output: { //出口文件
        path: path.join(__dirname, 'dist')
        ,filename: 'bundle.js'
    }
    ,devServer: {
        port: 3000 //devServer服务端口
        ,publicPath: '/dist'
    }
}