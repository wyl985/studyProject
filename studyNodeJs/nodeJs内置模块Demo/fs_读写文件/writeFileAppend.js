const fs = require("fs");

//重置文件内容
let data = "nodeJs读写文件练习";
fs.writeFile('./hello.txt', data, (err) => {
    if (err) throw err;

    //追加内容
    fs.readFile('./hello.txt', {encoding: "utf-8"}, (err, data) => {
        if (err) throw err;

        data = data + "append!";
        fs.writeFile('./hello.txt', data, (err) => {
            if (err) throw err;
            console.log('文件内容已追加');
        });
    });
});




