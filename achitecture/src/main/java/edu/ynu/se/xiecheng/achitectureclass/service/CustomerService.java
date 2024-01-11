package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchant;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerService extends LogicService<CustomerDao, Customer, Long> {
    @Resource
    CustomerDao customerDao;

    public CustomerService(@Autowired CustomerDao dao){
        super(dao);
    }

    //注册，成功后返回对象。若用户已存在则返回null
    public Customer SignIn(Customer customer){
        if(getDAO().countAllByPhone(customer.getPhone())!=0){
            return customer;
        }else{
            customerDao.save(customer);
            return getDAO().getCustomerByPhone(customer.getPhone());
        }
    }

    //登录，若登陆成功，则返回带具体信息的对象；失败则返回空值
    public Customer LoginIn(String phone, String pwd){
        Customer customer = getDAO().getCustomerByPhone(phone);
        if(BCrypt.checkpw(pwd, customer.getPassword())){
            return customer;
        }else{
            return null;
        }
    }

    //将ID和密码保存为Token，后续Cookie用
    public String toToken(Customer customer){
        return customer.getId() + ":" + customer.getPassword();
    }
}
