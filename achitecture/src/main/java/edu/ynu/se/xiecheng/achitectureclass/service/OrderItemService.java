package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.MerchandiseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.StoreMerchandiseDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.OrderItem;
import edu.ynu.se.xiecheng.achitectureclass.entity.StoreMerchandise;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderItemService extends LogicService<OrderItemDao, OrderItem, Long> {
    @Resource
    StoreMerchandiseDao storeMerchandiseDao;
    public OrderItemService(@Autowired OrderItemDao dao){super(dao);}


    //支付订单，价格持久化
    public Boolean PayOrderItem(OrderItem orderItem){
        StoreMerchandise storeMerchandise = storeMerchandiseDao.findStoreMerchandiseById(orderItem.getStoreMerchandiseId());
        orderItem.setPrice(storeMerchandise.getPrice());
        return true;
    }

    //订单添加商品
    public void UpdateOrderItem(OrderItem orderItem){
        getDAO().save(orderItem);
    }
}
