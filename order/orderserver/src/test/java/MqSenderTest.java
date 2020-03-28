import com.cloud.order.OrderApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OrderApplication.class)
public class MqSenderTest {
    @Autowired(required = false)
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMqMsg(){
        amqpTemplate.convertAndSend("myQueue","hello ");
        System.err.println("myQueue   hello --------");
    }

    @Test
    public void sendMqMsg1(){
        amqpTemplate.convertAndSend("myQueue","key1","hello ");
        System.err.println("myQueue   hello --------");
    }
}
