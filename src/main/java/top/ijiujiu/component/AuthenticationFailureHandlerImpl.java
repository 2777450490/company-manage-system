package top.ijiujiu.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.ijiujiu.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登录验证失败的类
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-11-12 11:31
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandlerImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LOGGER.error("登录验证失败");
        ResultUtil result = new ResultUtil();
        if (exception instanceof BadCredentialsException) {
            result.failed("用户名或密码不正确~~~~~~");
        } else {
            result.failed(exception.getMessage());
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
