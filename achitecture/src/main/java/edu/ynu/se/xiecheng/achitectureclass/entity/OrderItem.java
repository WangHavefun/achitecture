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
public class OrderItem extends LogicEntity {
    @Column
    private Long OrderId;

    @Column
    private Long StoreMerchandiseId;

    @Column
    private int Amount;

    //下单后写入数据，记录成交价格
    @Column
    private Double Price;

}
