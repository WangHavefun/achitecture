package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.StoreMerchandise;

import java.util.List;

public interface StoreMerchandiseDao extends LogicDAO<StoreMerchandise, Long> {
    StoreMerchandise findStoreMerchandiseById(Long id);
    List<StoreMerchandise> findStoreMerchandisesByMerchandiseId(Long id);
}
