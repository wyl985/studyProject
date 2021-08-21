// 基础模块
var fs = require('fs');
var express = require('express');
// contoller
var photoAlbum = require('./controller/photoAlbum');

var app = express();

fs.readFile(__dirname + '/resources/setting.json', (err, data) => {
    if (err) throw err;
    let photoAlbumPath = JSON.parse(data.toString())["photoAlbumPath"];

    // 检查当前目录中是否存在该文件。
    fs.access(photoAlbumPath, (err) => {
        console.log(`${photoAlbumPath} ${err ? '不存在' : '存在'}`);
        if(err){
            //文件夹不存在，创建文件夹
            fs.mkdirSync(photoAlbumPath);
        }
        runServer();
    });
});

function runServer(){
    photoAlbum.init(app);

    app.use(express.static('web/page'));
    app.use("/static", express.static('web/static'));
    app.listen(3000);
}



