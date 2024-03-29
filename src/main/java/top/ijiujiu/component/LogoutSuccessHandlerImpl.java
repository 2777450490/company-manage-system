package top.ijiujiu.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import top.ijiujiu.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理注销成功
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-11-12 11:31
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);

    /**Json转化工具*/
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        LOGGER.info("登出成功");
        ResultUtil result = new ResultUtil();
        result.success("登出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
