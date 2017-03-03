package com.goodtime.user.controller;

import com.goodtime.base.result.BaseResult;
import com.goodtime.base.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传下载Controller
 * 请求Content-type 为multipart/form-data
 * Created by zhongcy on 2017-02-27.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class FileController extends BaseController {
    @RequestMapping("/fileUpload")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file) {
        try {
            file.transferTo(new File("D:/study/goodtime2/gd-user/src/main/webapp/files/" + file.getOriginalFilename()));
        } catch (IOException e) {
            logger.error("upload file error.file={},e", file);
            return SYSTEM_ERROR;
        }
        return new BaseResult<>(file.getOriginalFilename());
    }

    @RequestMapping("/filesUpload")
    public Result uploadFiles(@RequestParam(value = "files") MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        try {
            for(MultipartFile file:files) {
                fileNames.add(file.getOriginalFilename());
                file.transferTo(new File("D:/study/goodtime2/gd-user/src/main/webapp/files/" + file.getOriginalFilename()));
            }
        } catch (IOException e) {
            logger.error("upload file error.files={},e", files);
            return SYSTEM_ERROR;
        }
        return new BaseResult<>(fileNames.toArray());
    }
}
