package com.webapptest.webapptest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity (name = "app_user")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
}
