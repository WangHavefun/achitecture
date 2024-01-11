package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Store;
import java.util.Set;


public interface StoreDao extends LogicDAO<Store, Long> {
    public Set<Store> getStoresByMerchantId(Long id);

}
