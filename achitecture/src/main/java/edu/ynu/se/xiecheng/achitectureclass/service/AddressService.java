package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.AddressDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressService extends LogicService<AddressDao, Address, Long> {
    public AddressService(@Autowired AddressDao dao){super(dao);}

    public boolean UpdateAddress(Address address){
        getDAO().save(address);
        return true;
    }

    public List<Address> AddressList(Long customerId){
        return getDAO().findAllByCustomerId(customerId);
    }

    //用于将地址保存为字符串存入订单中
    public String AddressToString(Address address){
        String a;
        a = address.getName()+","+address.getPhone()+","+address.getProvince()+address.getCity()
                +address.getStreet();
        return a;
    }
}
