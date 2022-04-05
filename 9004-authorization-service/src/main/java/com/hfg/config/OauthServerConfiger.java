package com.hfg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @Author: Zero
 * @Date: 2022/4/5 21:38
 * @Description:当前类为Oauth server的配置类 需要继承特定的AuthorizationServerConfigurerAdapter
 */
@Configuration
@EnableAuthorizationServer   //开启认证服务器功能
public class OauthServerConfiger extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 配置安全访问
     * 认证服务器最终是以api接口的方式对外提供服务(校验合法性并生成令牌、校验令牌)
     * 那么，以api接口方式对外的话，就涉及到接口的访问权限，我们需要在这里进行配置
     *
     * 输入密码和用户名---验证合法性---来换取令牌
     * @param security 认证安全服务
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        //相当于打开endpoints访问接口开关  这样的话后期我们能够访问接口
        security.
                allowFormAuthenticationForClients()  //允许客户端表单验证
                .tokenKeyAccess("permitAll()")  //开启端口 /oauth/token_key的访问权限 (允许)
                .checkTokenAccess("permitAll()"); //开启端口 /oauth/check_token的访问权限(允许)
    }

    /**
     * 客户端详情配置 比如配置client——id、secret
     * 客户端向该认证服务表面自己是谁？
     * @param clients 客户端详情服务配置
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        //客户端消息存储在什么地方,可以在内存中,也可以在数据库里面。
        clients.inMemory()
                //添加一个Client配置  指定client_id
                .withClient("client_hfg")
                //指定客户端的密码  / 安全码
                .secret("hfg123")
                //指定客户端所能访问资源的ID清单 此处的资源id是需要在具体的资源服务器上面
                .resourceIds("resume-service")
                //token授权颁发模式
                .authorizedGrantTypes("password","refresh_token")
                //客户端权限范围
                .scopes("all");
    }

    /**
     * 认证服务节点配置
     * 配置token令牌相关   (token此时就是一个字符串,当下的token需要在服务器端存储，都是在这配置)
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints
                .tokenStore(createInMemoryTokenStore())   //指定token的存储方法
                .tokenServices(authorizationServerTokenServices())   //token服务的一个描述  可以认为是token生成细节的描述 比如有效时间是多少
                .authenticationManager(authenticationManager) //指定认证管理器 随后注入一个到当前类使用即可
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
    }

    //

    /**
     * 该方法用于创建tokenstore对象
     * 表示token以内存的方式进行存储
     * @return
     */
    public InMemoryTokenStore createInMemoryTokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * 获取一个token服务对象(该对象描述了token有效期信息)
     */

    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        //使用默认实现
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true); //是否开启令牌刷新
        defaultTokenServices.setTokenStore(createInMemoryTokenStore());
        //设置令牌的有效时间
        defaultTokenServices.setAccessTokenValiditySeconds(20);
        //设置刷新令牌的有效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(30000);
        return defaultTokenServices;
    }
}
