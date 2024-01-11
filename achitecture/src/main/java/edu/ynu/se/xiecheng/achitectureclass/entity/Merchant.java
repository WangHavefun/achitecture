package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Merchant extends User{

    private String Name;

    private String Email;

    private String Explain;

    @OneToMany(mappedBy = "merchant_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"merchant_id"})
    private Set<Store> stores;

    @OneToMany(mappedBy = "merchant_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"merchant_id"})
    private Set<Merchandise> merchandises;

    @OneToMany(mappedBy = "merchant_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"merchant_id"})
    private Set<Order> orders;
}
