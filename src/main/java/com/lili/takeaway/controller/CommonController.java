package com.lili.takeaway.controller;/*
 *@ClassName:CommonController
 *@Description:
 *@Author:LL
 *@Date:2023/3/27
 */

import com.lili.takeaway.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/*
* 文件上传和下载
* */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${takeaway.path}")
    private String basePath;

    /*
    * 文件上传
    * */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件
        log.info(file.toString());

        //原始文件名
        String originFilename = file.getOriginalFilename();
        String suffix = originFilename.substring(originFilename.lastIndexOf("."));//文件后缀名
        //使用UUID随机生成文件名 防止文件名重复导致文件覆盖
        String fileName = UUID.randomUUID().toString()+suffix;
        //创建一个目录对象
        File dir = new File(basePath);
        //判断目录是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            //输入流，读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器显示图片
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
