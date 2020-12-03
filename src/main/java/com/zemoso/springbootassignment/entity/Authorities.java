package com.zemoso.springbootassignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="authorities")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@IdClass(CompositeKey.class)
public class Authorities {

    @Id
    @Column(name="username")
    private String username;

    @Id
    @Column(name="authority")
    private String authority;
}
