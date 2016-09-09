package com.koala.member.ws;

import com.koala.base.entities.member.KlUser;
import com.koala.base.logics.member.KlUserLogic;
import com.koala.member.api.MemberService;
import com.koala.member.api.response.UserInfo;
import com.koala.utils.config.MessageSender;
import com.koala.utils.config.Queue;
import com.koala.utils.config.annotation.EnableJMSSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberWs implements MemberService {

    @Resource
    private KlUserLogic userLogic;
    //消息推送
    @Resource
    MessageSender messageSender;

    @Override
    public void saveUser(UserInfo userInfo) {
        System.out.println("=================="+userInfo.getDesc());
        KlUser user = new KlUser();
        user.setUserName(userInfo.getUserName());
        user.setPassword(userInfo.getPassword());
        user.setRealName(userInfo.getRealName());
        user.setDesc(userInfo.getDesc());
        user.setCreateId(userInfo.getCreateId());
        user.setCreateDate(userInfo.getCreateDate());
        user.setUpdateId(userInfo.getUpdateId());
        user.setUpdateDate(userInfo.getUpdateDate());
        userLogic.insert(user);
        Map<String, Object> map = new HashMap<>();
        map.put("userName",user.getUserName());
        messageSender.sendMessage(map);
    }
}
