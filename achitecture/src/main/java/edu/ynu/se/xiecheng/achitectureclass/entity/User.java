package edu.ynu.se.xiecheng.achitectureclass.entity;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.util.DigestUtils;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
@Where(clause = "deleted_time is null")
@DiscriminatorColumn(name = "type")
public abstract class User extends LogicEntity {
    @Column
    protected String username;

    @Column
    protected String password;

    /**
     * 创建所有User时，密码经过md5加密
     * 修改为经过bcrypt进行加密
     * @param psd
     */
    public void setPassword(String psd){
        this.password = BCrypt.hashpw(psd, BCrypt.gensalt());
    }

    @Column
    protected Integer type = 0;

    @OneToOne(mappedBy = "user_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Merchant merchant;

    @OneToOne(mappedBy = "user_id",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Customer customer;
}
