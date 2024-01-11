package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.OrderItem;
import edu.ynu.se.xiecheng.achitectureclass.service.OrderItemService;
import edu.ynu.se.xiecheng.achitectureclass.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "订单实体控制器")
@RestController
@RequestMapping("/order")
public class OrderController extends LogicController<OrderService, OrderDao, Order, Long> {
    @Resource
    OrderItemService orderItemService;

    public OrderController(@Autowired OrderService service){
        super(service);
    }

    @ApiOperation("用户查询订单")
    @GetMapping("/customer/{c_id}")
    public List<Order> CustomerOrders(@ApiParam @PathVariable Long c_id){
        return getService().GetOrderListByCustomerId(c_id);
    }

    @ApiOperation("商家查询订单")
    @GetMapping("/merchant/{m_id}")
    public List<Order> MerchantOrders(@ApiParam @PathVariable Long m_id){
        return getService().GetOrderListByMerchantId(m_id);
    }

    @ApiOperation("查询订单详情")
    @GetMapping("/detail")
    public Order GetOrderDetail(@ApiParam Long c_id, @ApiParam Long m_id){
        return getService().GetOrder(c_id, m_id);
    }

    @ApiOperation("支付订单")
    @GetMapping("/payOrder")
    public Boolean PayOrder(@ApiParam Order order){
        return getService().OrderPay(order);
    }

    @ApiOperation("删除未支付订单")
    @GetMapping("/deleteOrder")
    public Boolean DeleteOrder(@ApiParam Long id){
        getService().DeleteOrder(id);
        return true;
    }

    @ApiOperation("添加")
    @GetMapping("/updateOrderItem")
    public Boolean UpdateOrder(@ApiParam OrderItem orderItem){
        orderItemService.UpdateOrderItem(orderItem);
        return true;
    }

}
