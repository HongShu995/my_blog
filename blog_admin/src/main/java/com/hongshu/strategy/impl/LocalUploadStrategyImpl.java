package com.hongshu.strategy.impl;

import com.hongshu.exception.BlogException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

/**
 * 本地上传策略
 *
 * @author Hong_Shu995
 * @date 2021-12-20
 */
@Service("localUploadStrategyImpl")
public class LocalUploadStrategyImpl extends AbstractUploadStrategyImpl
{

    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;

    /**
     * 访问url
     */
    @Value("${upload.local.url}")
    private String localUrl;

    @Override
    public Boolean exists(String filePath)
    {
        return new File(localPath + filePath).exists();
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) throws IOException
    {
        // 判断目录是否存在
        File directory = new File(localPath + path);
        System.out.println("============================");
        System.out.println(directory);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BlogException("创建目录失败");
            }
        }
        // 写入文件
        File file = new File(localPath + path + fileName);
        if (file.createNewFile()) {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
            }
            bos.flush();
            inputStream.close();
            bis.close();
            bos.close();
        }
    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return localUrl + filePath;
    }

}
