package com.cbezmen.microservice.address.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author canbezmen
 */
@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String id;

    private String city;
}
