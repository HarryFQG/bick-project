package com.it.project.securilty.security;

import com.it.project.cache.CommonCacheUtil;
import com.it.project.common.constants.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author fengqigui
 * @Date 2017/12/26 19:47
 */
@Configuration
@EnableWebSecurity
// 可以在方法上进行拦截
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 一个统一处理安全验证，调用manager，一个manager管理一到多个provider，同时有一个同意的异常处理类（entrypoint）
     *
     */
    @Autowired
    private Parameters parameters;
    @Autowired
    CommonCacheUtil commonCacheUtil;

    private RestPreAuthenticatedProcessingFilter getPreAuthenticatedProcessingFilter() throws Exception {
        RestPreAuthenticatedProcessingFilter filter = new RestPreAuthenticatedProcessingFilter(parameters.getNoneSecurityPath(), commonCacheUtil);
        filter.setAuthenticationManager(this.authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加 provider
        auth.authenticationProvider(new RestAuthenticatedProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 防止跨站脚本攻击，可以做表单唯一性，加一个隐藏域。
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(parameters.getNoneSecurityPath().toArray((new String[(parameters.getNoneSecurityPath().size())]))).permitAll()
            // 只要校验了，都放过
            .anyRequest().authenticated()
            .and()
                // session的创键策略,这里是无状态
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()// 表示还要添加新的东西
            .httpBasic().authenticationEntryPoint(new RestAuthenticationEntryPoint())// 同意异常处理类
            .and()
            .addFilter(getPreAuthenticatedProcessingFilter())
        ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略 OPTIONS方法的请求。OPTIONS：嗅探的一个方法
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

}


