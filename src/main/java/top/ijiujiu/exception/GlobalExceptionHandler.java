package top.ijiujiu.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.ijiujiu.util.ResultUtil;

/**
 * 全局异常处理
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-10-18 09:32
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultUtil exceptionHandler(Exception e) {
        ResultUtil data = new ResultUtil();
        data.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "程序异常,请联系管理员~~~~~~");
        LOGGER.error("异常信息:[{}]", e.getMessage());
        return data;
    }

    /**
     * 默认异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DefaultException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultUtil defaultExceptionHandler(DefaultException e) {
        ResultUtil data = new ResultUtil();
        data.failed(e.getStatus(), e.getMessage());
        LOGGER.error("异常信息:[{}]", e.getMessage());
        return data;
    }
}
