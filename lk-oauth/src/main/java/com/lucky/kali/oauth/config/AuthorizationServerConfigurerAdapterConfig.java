package com.lucky.kali.oauth.config;

import com.lucky.kali.oauth.service.impl.UserInfoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BenjaminEngle
 */
@Configuration
/*开启认证服务器*/
@EnableAuthorizationServer
/*继承Spring Security Oauth2配置类AuthorizationServerConfigurerAdapter(授权自定义)*/
public class AuthorizationServerConfigurerAdapterConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 加密
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 加载用户信息
     */
    @Resource
    private UserInfoServiceImpl userInfoService;

    /**
     * 认证管理器
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * token管理服务 JWT令牌存储方案
     *
     */
    @Resource
    private TokenStore tokenStore;

    /**
     * 数据源，用于从数据库获取数据进行认证操作，测试可以从内存中获取
     */
    @Resource
    private DataSource dataSource;

    /**
     * jwt设置需要的字段
     */
//    @Resource
//    private TokenEnhancer tokenEnhancer;

    /**
     * jks公钥
     */
//    @Resource
//    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 授权码
     */
    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    /**
     * ClientDetails信息加载实现类
     * 将客户端client id secret这些信息存储到数据库
     */
    @Resource
    private ClientDetailsService clientDetailsService;

    /**
     * 客户端配置，将客户端client id secret这些信息存储到数据库
     *
     * @return 客户端详细信息服务
     */
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public RedisTokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("user-token:");
        return tokenStore;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder)
                /*oauth/token_key是公开*/
                .tokenKeyAccess("permitAll()")
                /*oauth/check_token公开*/
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 设置授权码模式的授权码如何存取
     *
     * @param dataSource 数据源
     * @return 授权代码服务
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置客户端详情信息（也就是回调配置 Client Details)
     *
     * @param clients ClientDetailsService实例对象
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 令牌管理服务
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        /*jwt令牌内容增强*/
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
//        delegates.add(tokenEnhancer);
//        delegates.add(jwtAccessTokenConverter);
        /*配置JWT的内容增强器*/
        enhancerChain.setTokenEnhancers(delegates);
        /*配置tokenServices参数*/
        DefaultTokenServices service = new DefaultTokenServices();
        /*客户端详情服务*/
        service.setClientDetailsService(clientDetailsService);
        /*支持刷新令牌*/
        service.setSupportRefreshToken(true);
        /*是否复用 refreshToken*/
        service.setReuseRefreshToken(false);
        /*令牌存储,把access_token和refresh_token保存到数据库*/
        service.setTokenStore(tokenStore);
        /*配置JWT的内容增强*/
        service.setTokenEnhancer(enhancerChain);

        /*令牌默认有效期2小时*/
        service.setAccessTokenValiditySeconds(7200);
        /*刷新令牌默认有效期3天*/
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }

    /**
     * config方法
     *
     * @param endpoints 令牌管理服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        /*配置授权管理认证对象*/
        endpoints.authenticationManager(authenticationManager)
                /*配置加载用户信息的服务*/
                .userDetailsService(userInfoService)
                /*授权码服务,添加就可以保存到数据库了*/
                .authorizationCodeServices(authorizationCodeServices)
                /*jwt保存的信息*/
//                .accessTokenConverter(jwtAccessTokenConverter)
                /*令牌管理服务，调用上面的方法*/
                .tokenServices(tokenService())
                /*允许的令牌端点请求方法(默认情况下，仅启用POST方法)*/
                .allowedTokenEndpointRequestMethods(
                        HttpMethod.GET,
                        HttpMethod.POST
                );
    }

}
