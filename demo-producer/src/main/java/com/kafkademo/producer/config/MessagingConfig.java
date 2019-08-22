package com.kafkademo.producer.config;

import com.kafkademo.producer.channel.FruitStorageChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Configures Spring Cloud Stream support.
 * <p>
 * This works out-of-the-box if you use the Docker Compose configuration at "src/main/docker/kafka.yml".
 * <p>
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for the official Spring Cloud Stream documentation.
 */
@EnableBinding(value = {FruitStorageChannel.class})
public class MessagingConfig {

}
