var $ = require('jquery');

//请求后台，获取当前目录json
$.get("./photoAlbum/list", {}, function(res){
    console.log(res);
}, "json");