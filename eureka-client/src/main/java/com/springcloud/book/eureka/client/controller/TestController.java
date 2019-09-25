package com.springcloud.book.eureka.client.controller;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sam
 * @date 08/23/19 16:23
 */
@RestController
public class TestController {
    private final Logger log = LoggerFactory.getLogger(TestController.class);

    ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        System.out.println("----------------header----------------");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            System.out.println(key + ": " + request.getHeader(key));
        }
        System.out.println("----------------header----------------");
        return "hellooooooooooooooo!";
    }

    /**
     * 测试Cookies路由断言工厂
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/test/cookie")
    public String testGateway(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName()+":"+cookie.getValue());
            }
        }
        return "Spring Cloud Gateway,Hello world!";
    }


    /**
     * 测试Head路由断言工厂
     * @param request
     * @param response
     * @return
     */
    // @GetMapping("/test/head")
    // public String testGatewayHead(HttpServletRequest request, HttpServletResponse response){
    //     String head=request.getHeader("X-Request-Id");
    //     return "return head info:"+head;
    // }


    /**
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/test/head")
    public String testGatewayHead(HttpServletRequest request, HttpServletResponse response){
        String head=request.getHeader("X-Request-Acme");
        return "return head info:"+head;
    }


    @GetMapping("/test/addRequestParameter")
    public String addRequestParameter(HttpServletRequest request, HttpServletResponse response){
        String parameter=request.getParameter("example");
        return "return addRequestParameter info:"+parameter;
    }

    @GetMapping("/test/prefixpath")
    public String prefixpath(){
        return "Hello,Prefixpath:";
    }



    @GetMapping("/retry")
    public String testRetryByException(@RequestParam("key") String key, @RequestParam(name = "count") int count) {
        AtomicInteger num = map.computeIfAbsent(key, s -> new AtomicInteger());
        //对请求或重试次数计数
        int i = num.incrementAndGet();
        log.warn("重试次数: "+i);
        //计数i小于重试次数2抛出异常，让Spring Cloud Gateway进行重试
        if (i < count) {
            throw new RuntimeException("Deal with failure, please try again!");
        }
        //当重试两次时候，清空计数，返回重试两次成功
        map.clear();
        return "重试"+count+"次成功！";
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/test/sethead")
    public String testSetHead(HttpServletRequest request, HttpServletResponse response){
        String head=request.getHeader("Host");
        return "return head info:"+head;
    }

    @GetMapping("/test/Hystrix")
    public String index(@RequestParam("isSleep") boolean isSleep) throws InterruptedException {
        log.info("issleep is " + isSleep);
        //isSleep为true开始睡眠，睡眠时间大于Gateway中的fallback设置的时间
        if (isSleep) {
            TimeUnit.MINUTES.sleep(10);
        }
        return "No Sleep";
    }
}
