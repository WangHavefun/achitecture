package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.MerchandiseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.StoreMerchandiseDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.StoreMerchandise;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class MerchandiseService extends LogicService<MerchandiseDao, Merchandise, Long> {
    @Resource
    StoreMerchandiseDao storeMerchandiseDao;
    @Resource
    StoreService storeService;
    public MerchandiseService(@Autowired MerchandiseDao dao){super(dao);}

    public Boolean CreatMerchandise(Merchandise merchandise){
        getDAO().save(merchandise);
        return true;
    }

    public Merchandise getMerchandise(Long id){
        return getDAO().findMerchandiseById(id);
    }

    //更新商品信息
    //观察者模式
    public Boolean UpdateMerchandise(Merchandise merchandise){
        getDAO().save(merchandise);
        List<StoreMerchandise> list = storeMerchandiseDao.findStoreMerchandisesByMerchandiseId(merchandise.getId());
        for (StoreMerchandise storeMerchandise : list) {
            storeService.MerchandiseUpdate(storeMerchandise.getStoreId(), merchandise.getId());
        }
        return true;
    }
}
