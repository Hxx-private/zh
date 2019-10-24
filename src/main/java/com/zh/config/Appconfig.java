package com.zh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class Appconfig {
    @Value("${pdfRootPath}")
    private String pdfRootPath;

    public String getPdfRootPath() {
        return pdfRootPath;
    }

}