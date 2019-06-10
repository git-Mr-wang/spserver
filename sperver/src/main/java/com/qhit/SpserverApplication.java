package com.qhit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
public class SpserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpserverApplication.class, args);
    }
    /**
     * 2.1版本的security默认加上了 csrf 拦截, 所以需要通过重写方法, 把csrf拦截禁用
     * 参考: https://github.com/spring-cloud/spring-cloud-netflix/issues/2754
     * <pre>
     *     This is because @EnableWebSecurity is now added by default when Spring Security is on the classpath.
     *     This enable CSRF protection by default. You will have the same problem in 1.5.10 if you add @EnableWebSecurity.
     *     One work around, which is not the most secure workaround if you have browsers using the Eureka dashboard, is to disable CSRF protection.
     *     This can be done by adding the following configuration to your app.
     * </pre>
     */
    @EnableWebSecurity
    @Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable(); //关闭csrf
            super.configure(http);
//            http.csrf().disable().authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .httpBasic();
        }
    }
}
