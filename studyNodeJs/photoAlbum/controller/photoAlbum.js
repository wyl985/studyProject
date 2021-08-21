var photoalbum = {
    init: function(app){
        app.get('/photoAlbum/list', function (req, res) {
            let list = [{"isDir": true, "name": "文件夹"}, {"isDir": false, "name": "图片"}];
            res.end(JSON.stringify(list));
        });
    }
} 

module.exports = photoalbum;