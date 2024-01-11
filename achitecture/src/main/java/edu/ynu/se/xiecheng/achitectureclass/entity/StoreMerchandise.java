package edu.ynu.se.xiecheng.achitectureclass.entity;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class StoreMerchandise extends LogicEntity {
    @Column
    private Long StoreId;

    @Column
    private Long MerchandiseId;

    @Column
    private Double Price;

    @Column
    private int SalesVolume = 0;
}
