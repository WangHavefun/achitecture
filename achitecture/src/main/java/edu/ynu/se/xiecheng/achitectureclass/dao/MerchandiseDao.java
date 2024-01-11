package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchant;

import java.util.Set;

public interface MerchandiseDao extends LogicDAO<Merchandise, Long> {
    public Merchandise findMerchandiseById(Long id);
    public Set<Merchandise> getMerchandisesByMerchantId(Long id);
}
