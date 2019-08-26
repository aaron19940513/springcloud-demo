package com.springcloud.book.zuul.config;
import com.springcloud.book.zuul.filter.FirstPreFilter;
import com.springcloud.book.zuul.filter.PostFilter;
import com.springcloud.book.zuul.filter.SecondPreFilter;
import com.springcloud.book.zuul.filter.ThirdPreFilter;
import org.springframework.context.annotation.Bean;

/**
 * @author sam
 * @date 08/22/19 16:48
 */
//@Configuration
public class AutoConfiguration {
    @Bean
    public FirstPreFilter firstPreFilter() {
        return new FirstPreFilter();
    }

    @Bean
    public SecondPreFilter secondPreFilter() {
        return new SecondPreFilter();

    }

    @Bean
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();

    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
}
