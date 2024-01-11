package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.MerchandiseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.StoreDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StoreService extends LogicService<StoreDao, Store, Long> {
    @Resource
    StoreDao storeDao;
    @Resource
    MerchandiseDao merchandiseDao;

    public StoreService(@Autowired StoreDao dao){
        super(dao);
    }

    public boolean CreatStore(Store store){
        storeDao.save(store);
        return true;
    }

    //门店列表商家
   public Set<Store> getStoresByMerchantId(Long id){
        return getDAO().getStoresByMerchantId(id);
   }

    //获取门店商品列表
    public List<Merchandise> getMerchandiseByStoreId(Store store){
        List<Long> Ids= store.merchandisesIdList();
        List<Merchandise> merchandises= new ArrayList<>();
        for (Long id : Ids) {
            merchandises.add(merchandiseDao.findMerchandiseById(id));
        }
        return merchandises;
    }

    //门店添加商品

    //门店商品更新通知
    public void MerchandiseUpdate(Long StoreId, Long MerchandiseId){
    }
}
