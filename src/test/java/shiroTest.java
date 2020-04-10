
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zyq
 * @date 2020-03-26-9:09
 */
public class shiroTest {

    @Test
    public void testHelloworld() {
        //获取SecurtiyManager工厂，此处使用的ini配置文件初始化SecurityManager

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("shiro.ini");
        //2、得到SecurityManager实列 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);

        } catch (AuthenticationException e) {//包括了账号的异常和密码的异常
            //身份验证失败
            System.out.println("验证失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        //6、退出
        subject.logout();
    }
}
