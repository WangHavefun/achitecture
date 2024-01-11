package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.config.Properties;
import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.MerchantDao;
import edu.ynu.se.xiecheng.achitectureclass.dto.CustomerInDTO;
import edu.ynu.se.xiecheng.achitectureclass.dto.MerchantInDTO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchandise;
import edu.ynu.se.xiecheng.achitectureclass.entity.Merchant;
import edu.ynu.se.xiecheng.achitectureclass.service.MerchandiseService;
import edu.ynu.se.xiecheng.achitectureclass.service.MerchantService;
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
import java.util.Set;

@Api(tags = "商家控制器")
@RestController
@RequestMapping("/merchant")
public class MerchantController extends LogicController<MerchantService, MerchantDao, Merchant, Long> {
    @Resource
    MerchandiseService merchandiseService;
    @Resource
    UserService userService;
    public MerchantController(@Autowired MerchantService service){
        super(service);
    }

    @ApiOperation("注册")
    @GetMapping("/signin")
    public String MerchantSignIn(@ApiParam Merchant merchant){
        merchant = getService().SignIn(merchant);
        if(merchant==null){
            return null;
        }else{
            Cookie cookie = new Cookie();
            cookie.setName(getService().toToken(merchant));
            cookie.setPath("/");
            cookie.setMaxAge(Properties.cookieTokenExpire);
            return cookie.toString();
        }
    }


    @ApiOperation("登录")
    @GetMapping("/login")
    public String MerchantLogin(@ApiParam MerchantInDTO merchantInDTO){
        Merchant merchant = getService().LoginIn(merchantInDTO.getEmail(), merchantInDTO.getPassword());
        if(merchant == null){
            return null;
        }else{
            Cookie cookie = new Cookie();
            cookie.setName(getService().toToken(merchant));
            cookie.setPath("/");
            cookie.setMaxAge(Properties.cookieTokenExpire);
            return cookie.toString();
        }
    }

    @ApiOperation("获取商品列表")
    @GetMapping("/list")
    public Set<Merchandise> MerchantList(@ApiParam Merchant merchant){
        return getService().GetMerchandiseList(merchant.getId());
    }

    @ApiOperation("添加商品")
    @GetMapping("/addMerchandise")
    public Boolean CreatMerchandise(@ApiParam Merchandise merchandise){
        return merchandiseService.CreatMerchandise(merchandise);
    }

    @ApiOperation("获取商品详情")
    @GetMapping("/getMerchandiseDetail")
    public Merchandise GetMerchandise(@ApiParam Long id){
        return merchandiseService.getMerchandise(id);
    }

    @ApiOperation("更新商品")
    @GetMapping("/updateMerchandise")
    public Boolean UpdateMerchandise(@ApiParam Merchandise merchandise){
        merchandiseService.UpdateMerchandise(merchandise);
        return true;
    }
}
