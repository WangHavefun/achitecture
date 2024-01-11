package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService extends LogicService<OrderDao, Order, Long> {
    @Resource
    OrderDao orderDao;

    public OrderService(@Autowired OrderDao dao){super(dao);}

    //根据用户ID查询订单列表
    public List<Order> GetOrderListByCustomerId(Long userId){
        return getDAO().getOrdersByCustomerId(userId);
    }

    //根据店铺ID查询订单列表
    public List<Order> GetOrderListByMerchantId(Long userId){
        return getDAO().getOrdersByMerchantId(userId);
    }

    //若已有订单，返回已有订单；若无，则创建订单并返回
    public Order GetOrder(Long customerId, Long merchantId){
        Order order = getDAO().getOrderByCustomerIdAndMerchantId(customerId, merchantId);
        if(order == null){
            order = new Order();
            order.setCustomerId(customerId);
            order.setMerchantId(merchantId);
            getDAO().save(order);
        }
        return order;
    }

    //支付订单，Address记得修改
    //设置Address，OrderItem持久化
    public Boolean OrderPay(Order order){
        order.setStatus(1);
        getDAO().save(order);
        order.SetItemsPrice(order.getOrderItems());
        return true;
    }

    //未支付订单删除
    public void DeleteOrder(Long orderId){
        getDAO().deleteById(orderId);
    }
}
