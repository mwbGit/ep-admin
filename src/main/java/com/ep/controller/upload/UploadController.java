package com.ep.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ep.controller.upload.api.UploadResponse;
import com.ep.service.upload.api.IUploadService;
import com.ep.util.ConfigUtils;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @RequestMapping("/image/show")
    public void showImage(HttpServletRequest request, HttpServletResponse response) {
        String res_path = ConfigUtils.getProp("image.path");

//        // 来源网站
        String dir = "";
        // 来源位置
        String img = request.getParameter("id");

        File pic = null;
        if (img.contains(".gif") || img.contains(".jpg")
                || img.contains(".png")) {
            pic = new File(res_path + dir + File.separator + img);
        }

        if (pic != null && pic.exists()) {
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(pic);
                os = response.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 1024];
                while ((count = fis.read(buffer)) != -1)
                    os.write(buffer, 0, count);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null)
                        os.close();
                    if (fis != null)
                        fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/image")
    @ResponseBody
    public UploadResponse image(@RequestParam("file") MultipartFile uploadFile) throws IOException {
        UploadResponse response = new UploadResponse();

        String url = uploadService.uploadImage(uploadFile);

        if (url != null) {
            response.setUrl(url);
        } else {
            response.setCode("1");
            response.setMessage("上传失败");
        }

        return response;
    }

}
