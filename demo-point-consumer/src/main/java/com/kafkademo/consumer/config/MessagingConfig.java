package com.kafkademo.consumer.config;

import com.kafkademo.consumer.channel.OrderStorageChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Configures Spring Cloud Stream support.
 * <p>
 * This works out-of-the-box if you use the Docker Compose configuration at "src/main/docker/kafka.yml".
 * <p>
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for the official Spring Cloud Stream documentation.
 */
@EnableBinding(value = {OrderStorageChannel.class})
public class MessagingConfig {

}
