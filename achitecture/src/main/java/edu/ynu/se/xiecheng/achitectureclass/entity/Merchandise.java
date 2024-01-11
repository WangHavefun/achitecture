package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Merchandise extends LogicEntity {
    @Column
    private Long MerchantId;

    @Column
    private String Name;

    @Column
    private Double StandardPrice;

    @Column
    private Double MinimumPrice;

    //库存
    @Column
    private int inventory;

    @Column
    private String Remark;

    @OneToMany(mappedBy = "merchandise_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"merchandise_id"})
    private Set<StoreMerchandise> storeMerchandises;
}
