package com.wch.snippet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wch
 */
@RestController
public class TestController {

    @GetMapping("/multi")
    public List<String> multi() {
        List<String> multi = new ArrayList<>();
        multi.add("multi");
        multi.add("multi");
        return multi;
    }

    @GetMapping("/error")
    public void error() {
        throw new RuntimeException("error");
    }

    @PostMapping("/file/upload")
    public String fileUpload(MultipartFile file) {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        return "success";
    }
}
