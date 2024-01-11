package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Store extends LogicEntity {
    @Column
    private Long MerchantId;

    @Column
    private String Name;

   @Column
    private String Address;

    @OneToMany(mappedBy = "store_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"store_id"})
    private Set<StoreMerchandise> storeMerchandises;

    public List<Long> merchandisesIdList(){
        List<Long> results = new ArrayList<>();
        storeMerchandises.forEach(selection -> {
            results.add(selection.getMerchandiseId());
        });
        return results;
    }
}
