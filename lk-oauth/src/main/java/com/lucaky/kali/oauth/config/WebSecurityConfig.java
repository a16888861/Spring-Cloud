package com.lucaky.kali.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

/**
 * @author elliot
 */
@Configuration
//开启web保护
@EnableWebSecurity
//开启方法注解权限配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // password 支持多种编码，通过密码的前缀区分编码方式,默认 bcrypt
    @Bean
    PasswordEncoder createPasswordEncoder(){
        DelegatingPasswordEncoder delegatingPasswordEncoder=(DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }

    /**
     * 配置拦截保护请求,什么请求放行,什么请求需要验证
     * @param http          http安全
     * @throws Exception    异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/business/info/**").permitAll()
//                .antMatchers("/oauth/**","/sysOffice/Offices/**","/sysOffice/secondOffices/**","/sysOffice/firstOffices/**").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and().httpBasic() //启用http基础验证
                .and().cors()
                .and().csrf().disable();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    /**
     * 配置token验证管理的Bean
     * @return 验证结果
     * @throws Exception    异常
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
