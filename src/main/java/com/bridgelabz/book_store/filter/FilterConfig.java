package com.bridgelabz.book_store.filter;


import com.bridgelabz.book_store.util.TokenUtility;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(TokenUtility tokenUtility) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(tokenUtility));
        registrationBean.addUrlPatterns("/filter/*"); // Apply the filter to any endpoint under /api
        return registrationBean;
    }
}
