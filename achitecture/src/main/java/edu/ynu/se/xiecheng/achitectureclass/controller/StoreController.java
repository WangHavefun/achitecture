package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.StoreDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.Store;
import edu.ynu.se.xiecheng.achitectureclass.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Api(tags = "商店控制器")
@RestController
@RequestMapping("/store")
public class StoreController extends LogicController<StoreService, StoreDao, Store, Long> {
    public StoreController(@Autowired StoreService service){
        super(service);
    }

    @ApiOperation("商家获取门店")
    @GetMapping("/MerchantStores")
    public Set<Store> MerchantStore(@ApiParam Long id){
        return getService().getStoresByMerchantId(id);
    }

    @ApiOperation("门店获取商品")
    @GetMapping("/StoreMerchandise")
    public List<Merchandise> StoreMerchandise(@ApiParam Store store){
        return getService().getMerchandiseByStoreId(store);
    }
}
