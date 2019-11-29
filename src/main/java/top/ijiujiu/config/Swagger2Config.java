package top.ijiujiu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 对外访问地址 : 端口号/swagger-ui.html
 * Swagger2 配置
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-10-18 09:32
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {


    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("公司管理系统", "http://www.ijiujiu.top", "");
        return new ApiInfoBuilder()
                //页面标题
                .title("公司管理系统")
                //创建人
                .contact(contact)
                //版本号
                .version("1.0")
                //描述
                .description("公司管理系统API描述")
                .build();
    }
}
