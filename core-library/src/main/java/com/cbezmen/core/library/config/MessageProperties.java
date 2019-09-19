package com.cbezmen.core.library.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author canbezmen
 */
@Configuration
@ConfigurationProperties(prefix = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageProperties {

    private String hello;

    private String common;
}
