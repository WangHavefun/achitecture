package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Order extends LogicEntity {
    @Column
    private Long CustomerId;

    @Column
    private Long MerchantId;

    @Column
    private int status=0;

    @Column
    private String Address;

    @OneToMany(mappedBy = "order_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"order_id"})
    private Set<OrderItem> orderItems;

    public void SetItemsPrice(Set<OrderItem> orderItems){

    }
}
