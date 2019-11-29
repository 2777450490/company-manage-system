package top.ijiujiu.util;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 响应客户端对象封装
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-10-18 09:32
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="响应客户端对象", description="ResultUtil对象")
public class ResultUtil<T> implements Serializable {

    @ApiModelProperty(value = "请求响应状态码")
    private Integer status;

    @ApiModelProperty(value = "请求响应信息")
    private String message;

    @ApiModelProperty(value = "请求响应数据")
    private T data = null;

    public ResultUtil<T> success() {
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        return this;
    }

    public ResultUtil<T> success(String message) {
        this.status = HttpStatus.OK.value();
        this.message = message;
        return this;
    }

    public ResultUtil<T> success(T t) {
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = t;
        return this;
    }

    public ResultUtil<T> success(String message, T t) {
        this.status = HttpStatus.OK.value();
        this.message = message;
        this.data = t;
        return this;
    }

    public ResultUtil<T> failed() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        return this;
    }

    public ResultUtil<T> failed(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
        return this;
    }

    public ResultUtil<T> failed(String message, T t) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
        this.data = t;
        return this;
    }

    public ResultUtil<T> failed(Integer status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public ResultUtil<T> failed(Integer status, String message, T t) {
        this.status = status;
        this.message = message;
        this.data = t;
        return this;
    }

    public ResultUtil<T> noAuth() {
        this.status = HttpStatus.UNAUTHORIZED.value();
        this.message = HttpStatus.UNAUTHORIZED.getReasonPhrase();
        return this;
    }

    public ResultUtil<T> noAuth(String message) {
        this.status = HttpStatus.UNAUTHORIZED.value();
        this.message = message;
        return this;
    }
}
