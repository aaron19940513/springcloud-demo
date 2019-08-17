package com.springcloud.book.consumer.service;

import com.springcloud.book.consumer.model.User;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider")
public interface UserFeignService {

    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public String helloFeign();

    /**
     *
     * feign请求结果是一个图片流，怎么接收
     * 生成图片验证码
     * @param imagekey
     * @return
     */
    @RequestMapping(value = "createImagesCode")
    public Response createImageCode(@RequestParam("imagekey") String imagekey);

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user);
}
