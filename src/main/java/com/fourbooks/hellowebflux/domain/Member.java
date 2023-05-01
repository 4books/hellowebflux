package com.fourbooks.hellowebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("member")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private Integer id;
    private String name;
    private Integer age;
}
