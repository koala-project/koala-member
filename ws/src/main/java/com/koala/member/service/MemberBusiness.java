package com.koala.member.service;

import com.koala.base.entities.member.KlUser;
import com.koala.base.entities.member.KlUserCriteria;
import com.koala.base.logics.member.KlUserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Liuyf
 * @Date 2016-09-23
 * @Time 16:02
 * @Description
 */
@Component
public class MemberBusiness {

    @Autowired
    private KlUserLogic userLogic;


    public KlUser getKlUserByLoginName(String userName){
        KlUserCriteria example = new KlUserCriteria();
        example.createCriteria().andUserNameEqualTo(userName);
        List<KlUser> userList = userLogic.list(example);
        return userList != null && userList.size() > 0 ? userList.get(0):null;
    }

}
