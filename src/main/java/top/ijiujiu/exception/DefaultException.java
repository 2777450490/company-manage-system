package top.ijiujiu.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * 自定义异常
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-10-18 09:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultException extends Exception {

    private Integer status;

    private String message;

    public DefaultException(HttpStatus status, String message) {
        this.status = status.value();
        if (StringUtils.isEmpty(message)) {
            this.message = status.getReasonPhrase();
        } else {
            this.message = message;
        }
    }

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }
}
