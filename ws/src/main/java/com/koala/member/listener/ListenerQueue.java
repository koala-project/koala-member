package com.koala.member.listener;

import com.koala.utils.config.Queue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author Liuyf
 * @Date 2016-09-09
 * @Time 15:48
 * @Description
 */
@Component
public class ListenerQueue{

    @JmsListener(destination = Queue.GROUP_ORDERE_PAID)
    public void receiveQueue(Map<String, Object> map){
        System.out.println("---------------------"+map.get("userName"));
    }
}
