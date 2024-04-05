import edu.bistu.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = Admin.class)
public class AdminTest {
    @Test
    public void testStream() {
        List<String> urls = List.of("1", "2", "3");
        System.out.println("urls = " + String.join(",", urls));
    }

}
