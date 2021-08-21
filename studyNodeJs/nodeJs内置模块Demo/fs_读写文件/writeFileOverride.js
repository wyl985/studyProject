const fs = require("fs");

const data = "nodeJs读写文件练习override";
fs.writeFile('./hello.txt', data, (err) => {
  if (err) throw err;
  console.log('文件已被保存');
});