package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchant;

public interface CustomerDao extends LogicDAO<Customer, Long> {
    int countAllByPhone(String email);
    Customer getCustomerByPhone(String eamil);
}
