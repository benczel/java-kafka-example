import com.cloudkarafka.kafka.exmaple.KafkaClusterConfiguration;
import com.cloudkarafka.kafka.exmaple.KafkaExample;
import com.cloudkarafka.kafka.exmaple.Today;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaExampleMain {

    public static void main(String[] args) {
		String broker = System.getenv("CLOUDKARAFKA_BROKERS");
		String username = System.getenv("CLOUDKARAFKA_USERNAME");
		String password = System.getenv("CLOUDKARAFKA_PASSWORD");
        String topic = username + "-"+"hello-topic";
        KafkaClusterConfiguration clusterConfiguration = new KafkaClusterConfiguration(
                broker,
                username,
                password
        );

        KafkaExample kafkaExample = new KafkaExample();
        Properties props = clusterConfiguration.getProperties();
        String serializer = Today.class.getName();
        String deserializer = Today.class.getName();
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", serializer);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", deserializer);
        kafkaExample.produce(props, topic);
        kafkaExample.consume(props, topic);
    }
}
