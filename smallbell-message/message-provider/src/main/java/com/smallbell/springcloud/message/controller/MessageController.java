package com.smallbell.springcloud.message.controller;

import com.smallbell.springcloud.common.result.RestOut;
import com.smallbell.springcloud.message.contract.dto.MqSimpleMessageDTO;
import com.smallbell.springcloud.message.service.impl.MqMessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/smallbell/message/" )
@Api(tags = "消息管理" )
public class MessageController
{
    @Resource
    MqMessageServiceImpl mqMessageService;


    @PostMapping("/simple/send/v1" )
    @ApiOperation(value = "发送简单消息" )
    public RestOut<String> simpleSend(@RequestBody MqSimpleMessageDTO dto)
    {

        mqMessageService.sendMessage(dto.getMessageBody(), dto.getMessageTopic());

        return RestOut.success("发送完成" );
    }

}
