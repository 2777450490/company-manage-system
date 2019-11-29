package top.ijiujiu.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 初始化用户
 *
 * @author pengxl
 * @version 1.0
 * @since 2019-11-12 13:37
 */
@Component
public class InitUserComponent implements CommandLineRunner {

//    @Autowired
//    private ISysUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        SysUser user = this.userService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getLoginName, "admin"));
//        if (ObjectUtils.isEmpty(user)) {
//            user = new SysUser();
//            user.setLoginName("admin");
//            user.setLoginPassword(this.passwordEncoder.encode("123456"));
//            user.setName("admin");
//            user.setSex(1);
//            user.setCreator(user.getName());
//            user.setCreateDate(new Date());
//            this.userService.save(user);
//        }
    }
}
