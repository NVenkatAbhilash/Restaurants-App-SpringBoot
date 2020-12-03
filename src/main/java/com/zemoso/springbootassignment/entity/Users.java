package com.zemoso.springbootassignment.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Users {

    @Id
    @Length(min=3,message="username must be atleast 3 characters")
    @NotEmpty(message="is required")
    @Column(name="username")
    private String username;

    @Length(min=4,message="password must be atleast 4 characters")
    @NotEmpty(message="is required")
    @Column(name="password")
    private String password;

    @Transient
    private String confirmpassword;

    @Column(name = "enabled")
    private short enabled;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private List<Authorities> authorities;

    public void bcryptPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encrptedPassword = "{bcrypt}"+ passwordEncoder.encode(getPassword());
        setPassword(encrptedPassword);
    }

    public void addAuthority(Authorities theAuthority) {
        if(authorities==null)
            authorities = new ArrayList<>();
        authorities.add(theAuthority);
    }
}
