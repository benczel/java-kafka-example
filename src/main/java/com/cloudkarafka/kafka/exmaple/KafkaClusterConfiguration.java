package com.cloudkarafka.kafka.exmaple;

import java.util.Properties;

/**
 *
 * This class contains all necessary information which are required for connecting to the Kafka cluster
 */
public class KafkaClusterConfiguration {

    private final Properties props;

    /**
     * Public constructor
     *
     * @param broker url for the kafka cluster
     * @param username username for login the kafka cluster
     * @param password password for the given username in the kafka cluster
     */
    public KafkaClusterConfiguration(String broker,
                                     String username,
                                     String password)
    {
        props = new Properties();
        props.put("bootstrap.servers", broker);
        props.put("group.id", username + "-consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", getJaasConfiguration(username, password));
    }
    private String getJaasConfiguration(String username, String password) {
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";

        return String.format(jaasTemplate, username, password);
    }

    public Properties getProperties(){
        return this.props;
    }
}
