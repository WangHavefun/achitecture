package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("0")
public class Customer extends User {

    @Column
    private String Name;

    @Column
    private String Phone;

    @OneToMany(mappedBy = "customer_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"customer_id"})
    private Set<Address> addresses;

    @OneToMany(mappedBy = "customer_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"customer_id"})
    private Set<Order> orders;

}
