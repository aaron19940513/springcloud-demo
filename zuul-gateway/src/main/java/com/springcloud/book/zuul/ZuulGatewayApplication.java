package com.springcloud.book.zuul;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
    /**
     * Groovy加载方法配置，20秒自动刷新
     */
    @Component
    public static class GroovyRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();
            FilterLoader.getInstance().setCompiler(new GroovyCompiler());
            try {
                FilterFileManager.setFilenameFilter(new GroovyFileFilter());
                FilterFileManager.init(20, "D:/MyProjects/springcloud-demo/zuul-gateway/groovy");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
