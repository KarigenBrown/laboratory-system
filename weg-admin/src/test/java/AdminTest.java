import edu.bistu.Admin;
import edu.bistu.utils.JwtUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = Admin.class)
public class AdminTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testStream() {
        List<String> urls = List.of("1", "2", "3");
        System.out.println("urls = " + String.join(",", urls));
    }

    @Test
    public void testPasswordEncoder() {
        String rawPassword = "123456";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("encodedPassword = " + encodedPassword);
        System.out.println("rawPassword matches encodedPassword: " + passwordEncoder.matches(rawPassword, encodedPassword));
    }

    @Test
    public void testSplit() {
        String text = "";
        System.out.println("text.split(\",\") = " + Arrays.stream(text.split(",")).toList());
    }

    @Test
    public void testJwt() {
        String number = "2020000000";
        String jwt = JwtUtils.createJWT(number);
        System.out.println("jwt = " + jwt);
        String subject = JwtUtils.parseJWT(jwt).getSubject();
        System.out.println("subject = " + subject);
    }

    @Test
    public void testTextBlock() {
        String url = "url";
        String businessName = "businessName";
        String httpMethod = "httpMethod";
        String className = "className";
        String methodName = "methodName";
        String ip = "ip";
        String args = "args";
        String r = "result";
        String log = """
                URL          : %s
                Business Name: %s
                HTTP Method  : %s
                Class Method : %s
                IP           : %s
                Args         : %s
                Result       : %s
                """.formatted(url, businessName, httpMethod, className + "." + methodName, ip, args, r);
        System.out.println("log = " + log);
    }

}
