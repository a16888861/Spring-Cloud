package com.lucky.kali.business.config;

import com.lucky.kali.common.oauth.MyPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Elliot
 * @date 2021-06-29 16:27
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这样，页面提交时候，密码以明文的方式进行匹配。
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zys").password("zys").roles("ADMIN");
    }

    /**
     * 配置拦截保护请求,什么请求放行,什么请求需要验证
     *
     * @param http Http安全类
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().csrf().disable()
                .authorizeRequests()
                //放行请求
                .antMatchers("/", "/login", "/doc.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().permitAll();
        //关闭默认的csrf认证
        http.csrf().disable();
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
        web.ignoring().antMatchers("/js/**", "/cs/**", "/images/**");
    }
}
