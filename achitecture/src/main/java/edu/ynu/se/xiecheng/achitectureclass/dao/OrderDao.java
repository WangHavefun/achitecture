package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;

import java.util.List;

public interface OrderDao extends LogicDAO<Order, Long> {
    public List<Order> getOrdersByCustomerId(Long id);
    public List<Order> getOrdersByMerchantId(Long id);
    public Order getOrderByCustomerIdAndMerchantId(Long customerId, Long merchantId);
}
