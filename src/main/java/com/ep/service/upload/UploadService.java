package com.ep.service.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.service.upload.api.IUploadService;
import com.ep.util.ConfigUtils;

/**
 * Created by MengWeiBo on 2017-10-12
 */
@Service
public class UploadService implements IUploadService {

    @Override
    public String uploadImage(MultipartFile uploadFile) {
        String configPath = ConfigUtils.getProp("image.path");
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        String temp = formater.format(new Date());

        String url = null;
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String myFileName = uploadFile.getOriginalFilename();

            if (StringUtils.isNotBlank(myFileName.trim())) {
                String fileName = getName(uploadFile.getOriginalFilename());

                // 定义上传路径
                String path = configPath + "/" + temp + "/" + fileName;
                File localFile = new File(path);

                url = ApplicationContextUtils.getDomain() + "/upload/image/show?id=" + temp + "/" + fileName;
                try {
                    if (!localFile.exists()) {
                        localFile.mkdirs();
                    }

                    uploadFile.transferTo(localFile);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        return url;
    }

    private String getName(String fileName) {
        Random random = new Random();
        return "" + random.nextInt(10000)
                + System.currentTimeMillis() + this.getFileExt(fileName);
    }
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
