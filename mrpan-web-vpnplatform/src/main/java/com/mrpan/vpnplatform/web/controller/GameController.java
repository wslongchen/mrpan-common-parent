package com.mrpan.vpnplatform.web.controller;

import com.mrpan.common.core.utils.RenderKit;
import com.mrpan.vpnplatform.web.BaseController;
import com.mrpan.wechat.bean.results.utils.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mrpan on 2017/2/13.
 */
@Controller
@RequestMapping(value="/game")
@Scope(value = "prototype")
public class GameController extends BaseController{

    private static final Logger logger = LoggerFactory
            .getLogger(GameController.class);

    @RequestMapping(value = "/hjkg", method = RequestMethod.GET)
    public String hjkg() {
        logger.debug("hjkg..");
        return "game/hjkg";
    }
}
