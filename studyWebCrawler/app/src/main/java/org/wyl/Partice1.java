package org.wyl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 爬取  http://www.max-logistics.com/tpl/home/default/public/hui/lib/echarts/2.2.7/ 下的所有文档
 * @author 吴宇亮 on 2021/9/30 15:16
 */
public class Partice1 {

//    private static final String rootDirectory = "C:\\Users\\Administrator\\Desktop\\echart文档\\build";
//    private static final String targetUrl = "http://www.max-logistics.com/tpl/home/default/public/hui/lib/echarts/2.2.7/build/";

    private static final String rootDirectory = "C:\\Users\\Administrator\\Desktop\\echart文档";
    private static final String targetUrl = "http://www.max-logistics.com/tpl/home/default/public/hui/lib/echarts/2.2.7/doc/";

    public static void main(String[] args) throws IOException {
        // 创建目录
        File rootDirectoryFile = new File(rootDirectory);
        FileUtil.del(rootDirectoryFile);
        FileUtil.mkdir(rootDirectoryFile);
        // 爬虫
        recursiveUrl2File(rootDirectoryFile, targetUrl);
    }

    private static void recursiveUrl2File(File parentFile, String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        // 后台资源
        if(0 == document.select("address").size()){
            int index = url.substring(0, url.length() - 1).lastIndexOf("/");
            String fileName = url.substring(index);
            String fileContent = document.outerHtml();

            File file = new File(parentFile, fileName);
            System.out.println("file: " + file.getAbsolutePath());
            FileUtil.writeUtf8String(fileContent, file);
            return;
        }
        // 静态资源
        Elements aTags = document.select("a");
        for (int i = 1; i < aTags.size(); i++) {
            Element aTag = aTags.get(i);
            String href = aTag.attr("abs:href");
            //文件夹 或 资源
            if (href.endsWith("/")) {
                String fileName = href.replaceFirst(url, "").replace("/", "");
                File file = new File(parentFile, fileName);
                FileUtil.mkdir(file);
                System.out.println("directory: " + file.getAbsolutePath());
                //递归访问文件夹
                recursiveUrl2File(file, href);
            }
            //文件
            else{
                String fileName = href.replaceFirst(targetUrl, "");
                File file = new File(rootDirectory, fileName);
                if(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")){
                    HttpUtil.downloadFile(href, file);
                }else{
                    String fileContent = HttpUtil.get(href);
                    FileUtil.writeUtf8String(fileContent, file);
                }
                System.out.println("file: " + file.getAbsolutePath());
            }
        }
    }
}
