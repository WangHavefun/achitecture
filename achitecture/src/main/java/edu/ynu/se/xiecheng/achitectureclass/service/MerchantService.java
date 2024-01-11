package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.MerchandiseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.MerchantDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchant;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class MerchantService extends LogicService<MerchantDao, Merchant, Long> {

    @Resource
    MerchandiseDao merchandiseDao;

    public MerchantService(@Autowired MerchantDao dao) {
        super(dao);
    }

    //注册，成功后返回对象。若用户已存在则返回null
    public Merchant SignIn(Merchant merchant){
        if(getDAO().countAllByEmail(merchant.getEmail())!=0){
            return merchant;
        }else{
            getDAO().save(merchant);
            return getDAO().getMerchantByEmail(merchant.getEmail());
        }

    }

    //登录，若登陆成功，则返回带具体信息的对象；失败则返回空值
    public Merchant LoginIn(String email, String pwd){
        Merchant merchant = getDAO().getMerchantByEmail(email);
        if(BCrypt.checkpw(pwd, merchant.getPassword())){
            return merchant;
        }else{
            return null;
        }
    }

    public Set<Merchandise> GetMerchandiseList(Long id){
        return merchandiseDao.getMerchandisesByMerchantId(id);
    }

    public String toToken(Merchant merchant){
        return merchant.getId() + ":" + merchant.getPassword();
    }
}
