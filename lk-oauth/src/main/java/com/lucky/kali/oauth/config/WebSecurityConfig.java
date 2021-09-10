package com.lucky.kali.oauth.config;

import com.lucky.kali.oauth.service.impl.UserInfoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Elliot
 * @date 2021-06-29 16:27
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自己写的用户登录处理逻辑
     *
     * @return 自己写的
     */
    @Override
    public UserDetailsService userDetailsService() {
        return new UserInfoServiceImpl();
    }

    /**
     * 加密器
     *
     * @return 创建的加密器
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //这样，页面提交时候，密码以明文的方式进行匹配。
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zys").password("zys").roles("ADMIN");
////        auth.userDetailsService(new UserDetailsServiceImpl());
//    }


    /**
     * 配置拦截保护请求,什么请求放行,什么请求需要验证
     *
     * @param http Http安全类
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*关闭默认的csrf认证*/
        http.csrf().disable()
                /*启用http基础验证*/
                .httpBasic()
                .and().csrf().disable()
                .authorizeRequests()
                /*放行请求*/
                .antMatchers("/", "/doc.html", "/doc").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().formLogin()
                .and().logout()
                .permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 放行静态资源
     */
    @Override
    public void configure(WebSecurity web) {
        //设置静态资源不要拦截
        web.ignoring().antMatchers("/js/**", "/cs/**", "/images/**", "/image/**");
    }
}
