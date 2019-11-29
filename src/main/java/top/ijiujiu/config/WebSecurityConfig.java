package top.ijiujiu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import top.ijiujiu.component.AuthenticationEntryPointImpl;
import top.ijiujiu.component.AuthenticationFailureHandlerImpl;
import top.ijiujiu.component.AuthenticationSuccessHandlerImpl;
import top.ijiujiu.component.LogoutSuccessHandlerImpl;

/**
 * WebSecurityConfiguration
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-11-12 11:31
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private SysUserServiceImpl sysUserService;

    /**
     * 自定义的登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    /**
     * 自定义的登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    /**
     * 自定义的登出成功处理器
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * 自定义未登录处理器
     */
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(sysUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .cors() //开启跨域
                .and().authorizeRequests()
                .antMatchers("/**").permitAll() // 所有都不拦截
                .anyRequest().authenticated() // 其它请求,都需要身份认证
                .and().formLogin()
                .loginProcessingUrl("/login").permitAll()
                .and().logout().logoutUrl("/logout").permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().csrf().disable(); // 取消跨站请求伪造防护
    }


    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }


    /**
     * 配置跨域处理
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    /**
     * 配置对swagger的请求路径全部放开
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/**")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-ui.html/**");
    }

}
