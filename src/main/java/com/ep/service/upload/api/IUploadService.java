package com.ep.service.upload.api;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public interface IUploadService {

    public String uploadImage(MultipartFile uploadFile);
}
