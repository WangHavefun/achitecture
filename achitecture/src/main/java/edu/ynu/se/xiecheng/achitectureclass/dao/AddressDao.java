package edu.ynu.se.xiecheng.achitectureclass.dao;

import com.mysql.cj.log.Log;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Address;

import java.util.List;

public interface AddressDao extends LogicDAO<Address,Long> {
    public List<Address> findAllByCustomerId(Long id);
}
