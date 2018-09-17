package com.zqw.springsession.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 **/
@RestController
public class WebSoketDemoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //spring提供的发送消息模板
    @Autowired
    private SimpMessagingTemplate messagingTemplate;




    /*点对点通信*/
    @MessageMapping(value = "/P2P")
    public void templateTest(Principal principal) {
        //发送消息给指定用户
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/message","服务器主动推的数据");
    }

    /*广播*/
    @MessageMapping("/broadcast")
    @SendTo("/topic/getResponse")
    public String  topic() throws Exception {
        return "success";
    }


}
