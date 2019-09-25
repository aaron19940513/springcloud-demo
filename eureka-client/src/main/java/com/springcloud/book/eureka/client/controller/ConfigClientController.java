package com.springcloud.book.eureka.client.controller;
import com.springcloud.book.eureka.client.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Autowired
    private ConfigInfoProperties configInfoValue;

    @RequestMapping("/getConfigInfo")
    public String getConfigInfo() {
        return configInfoValue.getConfig();
    }
}
