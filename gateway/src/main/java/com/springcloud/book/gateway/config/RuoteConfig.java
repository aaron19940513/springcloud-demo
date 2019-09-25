package com.springcloud.book.gateway.config;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author sam
 * @date 09/03/19 11:11
 */
@Configuration
public class RuoteConfig {

    /**
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //    断言
        //生成比当前时间早一个小时的UTC时间
        // ZonedDateTime minusTime = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        // ZonedDateTime datetime = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());
        // return builder.routes()
        //               //basic proxy
        //               .route(r ->r.path("/jd").uri("http://jd.com:80/").id("jd_route"))
        //               .route("after_route", r -> r.after(minusTime).uri("http://baidu.com"))
        //               .route("before_route", r -> r.before(datetime).uri("http://baidu.com"))
        //               .route("between_route", r -> r.between(minusTime,datetime).uri("http://baidu.com"))
        //               .route("cookie_route", r -> r.cookie("chocolate", "ch.p").uri("http://localhost:8071/test/cookie"))
        //               .route("header_route", r -> r.header("X-Request-Id", "xujin").uri("http://localhost:8071/test/head"))
        //               .route("host_route", r -> r.host("**.baidu.com:8080").uri("http://jd.com"))
        //               .route("method_route", r -> r.method("GET").uri("http://jd.com"))
        //               .route("query_route", r -> r.query("foo","baz").uri("http://baidu.com"))
        //               .route("remote_addr_route", r -> r.remoteAddr("127.0.0.1").uri("http://baidu.com"))
        //               .build();


        //过滤器
        return builder.routes()
                      .route("add_request_header_route", r -> r.path("/test")
                                                               .filters(f -> f.addRequestHeader("X-Request-Acme", "ValueB"))
                                                               .uri("http://localhost:8081/test/head"))
                      .route("add_request_parameter_route", r -> r.path("/addRequestParameter")
                                                                  .filters(f -> f.addRequestParameter("example", "ValueB"))
                                                                  .uri("http://localhost:8081/test/addRequestParameter"))
                      .route("rewritepath_route", r -> r.path("/foo/**")
                                                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/$\\{segment}"))
                                                        .uri("http://www.baidu.com"))
                      .route("add_request_header_route", r -> r.path("/test")
                                                               .filters(f -> f.addResponseHeader("X-Response-Foo", "Bar"))
                                                               .uri("http://www.baidu.com"))
                      .route("retry_route", r -> r.path("/test/retry")
                                                  .filters(f -> f.retry(config -> config.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
                                                  .uri("http://localhost:8081/retry?key=abc&count=2"))
                      .build();
    }

}
