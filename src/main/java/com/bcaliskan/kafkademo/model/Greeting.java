package com.bcaliskan.kafkademo.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {

    private String message;

    private String name;

}
