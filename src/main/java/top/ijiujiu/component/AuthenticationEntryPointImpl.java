package top.ijiujiu.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.ijiujiu.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录请求资源处理
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-11-12 14:29
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        LOGGER.error("未登录,或登录超时");
        ResultUtil result = new ResultUtil();
        result.failed(HttpStatus.UNAUTHORIZED.value(),"未登录,或登录超时~~~~~~");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
