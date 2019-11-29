package top.ijiujiu.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页查询接收参数工具类
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
@ApiModel(value="分页查询接收参数工具类", description="PageUtil对象")
public class PageUtil<T> implements Serializable {

    @ApiModelProperty(value = "当前页数，默认1", example = "1")
    private Long current = 1L;

    @ApiModelProperty(value = "每页条数，默认10", example = "10")
    private Long size = 10L;

    @ApiModelProperty(value = "是否查询总条数，默认false返回总条数为:0", example = "false")
    private Boolean searchCount = false;

    @ApiModelProperty(value = "查询参数，默认null")
    private T param;

}
