package com.zemoso.springbootassignment.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @EqualsAndHashCode
public class CompositeKey implements Serializable {
    private String username;
    private String authority;
}
