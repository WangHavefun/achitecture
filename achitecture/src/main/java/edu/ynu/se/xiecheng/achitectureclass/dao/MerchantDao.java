package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchant;

public interface MerchantDao extends LogicDAO<Merchant,Long> {
    int countAllByEmail(String email);
    Merchant getMerchantByEmail(String eamil);
}
