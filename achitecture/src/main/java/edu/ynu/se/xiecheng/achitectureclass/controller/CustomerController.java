package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.config.Properties;
import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.dto.CustomerInDTO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Address;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.service.AddressService;
import edu.ynu.se.xiecheng.achitectureclass.service.CustomerService;
import edu.ynu.se.xiecheng.achitectureclass.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags="客户实体控制器")
@RestController
@RequestMapping("/customer")
public class CustomerController extends LogicController<CustomerService, CustomerDao, Customer, Long> {
    @Resource
    AddressService addressService;
    @Resource
    UserService userService;
    public CustomerController(@Autowired CustomerService service){
        super(service);
    }

    @ApiOperation("注册")
    @GetMapping("/signIn")
    public String CustomerSignIn(@ApiParam Customer customer){
        customer = getService().SignIn(customer);
        if(customer==null){
            return null;
        }else{
            Cookie cookie = new Cookie();
            cookie.setName(getService().toToken(customer));
            cookie.setPath("/");
            cookie.setMaxAge(Properties.cookieTokenExpire);
            return cookie.toString();
        }
    }

    @ApiOperation("登录")
    @GetMapping("/logIn")
    public String CustomerLogin(@ApiParam CustomerInDTO customerInDTO){
        Customer customer = getService().LoginIn(customerInDTO.getPhone(), customerInDTO.getPassword());
        if(customer == null){
            return null;
        }else{
            Cookie cookie = new Cookie();
            cookie.setName(getService().toToken(customer));
            cookie.setPath("/");
            cookie.setMaxAge(Properties.cookieTokenExpire);
            return cookie.toString();
        }
    }

    @ApiOperation("查看收货地址列表")
    @GetMapping("/AddressList")
    public List<Address> CustomerAddressList(@ApiParam Long id){
        return  addressService.AddressList(id);
    }

    @ApiOperation("添加收货地址")
    @GetMapping("/AddAddress")
    public Boolean CustomerAddAddress(@ApiParam Address address){
        addressService.AddressToString(address);
        return true;
    }
}
