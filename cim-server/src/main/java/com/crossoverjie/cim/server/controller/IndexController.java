package com.crossoverjie.cim.server.controller;

import com.crossoverjie.cim.common.constant.Constants;
import com.crossoverjie.cim.common.enums.StatusEnum;
import com.crossoverjie.cim.common.res.BaseResponse;
import com.crossoverjie.cim.server.api.ServerApi;
import com.crossoverjie.cim.server.api.vo.req.SendMsgReqVO;
import com.crossoverjie.cim.server.api.vo.res.SendMsgResVO;
import com.crossoverjie.cim.server.server.CIMServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 22/05/2018 14:46
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/")
public class IndexController implements ServerApi {


    /**
     * 在这里启动nettyServer
     */
    @Autowired
    private CIMServer cimServer;


    /**
     * 统计 service
     */
    @Autowired
    private CounterService counterService;

    /**
     *
     * @param sendMsgReqVO
     * @return
     */
    @Override
    @ApiOperation("Push msg to client")
    @RequestMapping(value = "sendMsg",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<SendMsgResVO> sendMsg(@RequestBody SendMsgReqVO sendMsgReqVO){
        BaseResponse<SendMsgResVO> res = new BaseResponse();
        cimServer.sendMsg(sendMsgReqVO) ;
        // 记数，具体实现，我还是不知道的
        counterService.increment(Constants.COUNTER_SERVER_PUSH_COUNT);

        // 返回给前端信息
        SendMsgResVO sendMsgResVO = new SendMsgResVO() ;
        sendMsgResVO.setMsg("OK") ;
        res.setCode(StatusEnum.SUCCESS.getCode()) ;
        res.setMessage(StatusEnum.SUCCESS.getMessage()) ;
        res.setDataBody(sendMsgResVO) ;
        return res ;
    }

}
