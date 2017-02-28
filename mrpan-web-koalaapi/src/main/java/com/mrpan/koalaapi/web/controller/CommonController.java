package com.mrpan.koalaapi.web.controller;

import com.google.gson.Gson;
import com.mrpan.common.core.utils.*;
import com.mrpan.koala.bean.Koala_Coin;
import com.mrpan.koala.bean.Koala_Step;
import com.mrpan.koala.service.Koala_CoinService;
import com.mrpan.koala.service.Koala_StepService;
import com.mrpan.koalaapi.web.BaseController;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.bean.Ann_Wechat;
import com.mrpan.user.service.Ann_UserService;
import com.mrpan.wechat.message.MessageConn;
import com.mrpan.wechat.utls.Util;
import com.mrpan.wechat.utls.XMLParser;
import org.apache.commons.lang.StringUtils;
import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mrpan on 2017/2/21.
 */
@Controller
@RequestMapping(value = "/common")
@Scope(value = "prototype")
public class CommonController extends BaseController{

    private static final Logger logger = LoggerFactory
            .getLogger(CommonController.class);

    @Autowired
    private Koala_StepService koala_StepService;
    @Autowired
    private Koala_CoinService koala_CoinService;
    @Autowired
    private Ann_UserService ann_UserService;

    /**
     * 每日排行
     * @param request
     * @param response
     */
    @RequestMapping(value = "/chartList")
    public void chartList(HttpServletRequest request, HttpServletResponse response) {
        Pager<Koala_Step> koalaStepPagers=new Pager<Koala_Step>();
        try {
            String date= WebUtil.getParam(request,"date","");
            if(StringUtils.isNotBlank(date)){
                List<FourObject> mapWhere=new ArrayList<FourObject>();
                mapWhere.add(new FourObject("targetDate",date));
                Pager<Koala_Step> steps=this.koala_StepService.findSteps(1,10,mapWhere);
                if(steps!=null){
                    koalaStepPagers=steps;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        Gson gson = new Gson();
        RenderKit.renderJson(response, gson.toJson(koalaStepPagers));
    }

    /***
     * 历史步数
     * @param request
     * @param response
     */
    @RequestMapping(value = "/historyList")
    public void historyList(HttpServletRequest request, HttpServletResponse response) {
        Pager<Koala_Step> koalaStepPagers=new Pager<Koala_Step>();
        try {
            int pageNo=WebUtil.getParam(request,"pageNo",1);
            int pageSize=WebUtil.getParam(request,"pageSize",30);
            String userId= WebUtil.getParam(request,"userId","");
            if(StringUtils.isNotBlank(userId)){
                List<FourObject> mapWhere=new ArrayList<FourObject>();
                mapWhere.add(new FourObject("userId",userId));
                Pager<Koala_Step> steps=this.koala_StepService.findSteps(pageNo,pageSize,mapWhere);
                if(steps!=null){
                    koalaStepPagers=steps;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        Gson gson = new Gson();
        RenderKit.renderJson(response, gson.toJson(koalaStepPagers));
    }

    /***
     * 更新或新增每日步数
     * @param request
     * @param response
     */
    @RequestMapping(value = "/updateStepToday")
    public void updateStepToday(HttpServletRequest request, HttpServletResponse response) {
        JsonResponse result=new JsonResponse(0,"操作成功");
        try {
            int userId= WebUtil.getParam(request,"userId",0);
            int step=WebUtil.getParam(request,"step",0);
            int target=WebUtil.getParam(request,"target",0);

            if(userId==0){
                throw new Exception("操作失败，用户ID为空！");
            }

            List<FourObject> mapWhere=new ArrayList<FourObject>();
            mapWhere.add(new FourObject("userId",userId));
            mapWhere.add(new FourObject("targetDate",TimeUtil.getCurrentDate()));
            List<Koala_Step> steps=this.koala_StepService.listSteps(mapWhere);
            if(steps.size()>0){
                Koala_Step s=steps.get(0);
                s.setStep(step);
                s.setTarget(target);
                if(step>=target){
                    s.setStatus(1);
                }else{
                    s.setStatus(0);
                }
                this.koala_StepService.updateStep(s);
            }else{
                Koala_Step koala_step=new Koala_Step();
                koala_step.setCreateDate(new Date());
                koala_step.setTargetDate(new Date());
                koala_step.setTarget(target);
                if(step>=target){
                    koala_step.setStatus(1);
                }else{
                    koala_step.setStatus(0);
                }
                koala_step.setUserId(userId);
                this.koala_StepService.addStep(koala_step);
            }

        }catch (Exception e) {
            e.printStackTrace();
            result=new JsonResponse(-1,e.getMessage());
            logger.info(e.getMessage());
        }
        Gson gson = new Gson();
        RenderKit.renderJson(response, gson.toJson(result));
    }

    /***
     * 检查用户，并创建用户
     * @param request
     * @param response
     */
    @RequestMapping(value = "/checkUser")
    public void checkUser(HttpServletRequest request, HttpServletResponse response) {
        JsonResponse result=new JsonResponse(0,"操作成功");
        Ann_User u=null;
        try {
            String userName=WebUtil.getParam(request,"userName","");
            List<FourObject> mapWhere=new ArrayList<FourObject>();
            mapWhere.add(new FourObject("userName",userName));
            List<Ann_User> users=this.ann_UserService.listUsers(mapWhere);
            if(users.size()>0){
                Ann_User user=users.get(0);
                user.setLastLogonDate(new Date());
                String addr=request.getRemoteAddr();
                user.setLastLogonIP(addr);
                int count=user.getLoginCount()==null?0:user.getLoginCount()+1;
                user.setLoginCount(count);
                this.ann_UserService.updateUser(user);
                u=user;
            }else{
                Ann_User user=new Ann_User();
                user.setLastLogonIP(request.getRemoteAddr());
                user.setLastLogonDate(new Date());
                user.setCreateDate(new Date());
                user.setLoginCount(0);
                String username="koala_"+StringUtil.getShuffle(6);
                user.setUserName(username);
                user.setPassword(MyMD5Util.getEncryptedPwd("123456"));
                user.setAuditStatus("0");
                user.setDescription("考拉运动用户"+username);
                user.setEnabled(0);
                user.setIsVisible(0);
                user.setRoleId(4);
                user.setUserStatus(0);
                this.ann_UserService.addUser(user);
                List<FourObject> where=new ArrayList<FourObject>();
                where.add(new FourObject("userName",username));
                List<Ann_User> ann_users=this.ann_UserService.listUsers(where);
                if(ann_users.size()>0){
                    u=ann_users.get(0);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            result=new JsonResponse(-1,e.getMessage());
            logger.info(e.getMessage());
        }
        result.setRtData(u);
        Gson gson = new Gson();
        RenderKit.renderJson(response, gson.toJson(result));
    }

    /***
     * 用户领取考拉币
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCoin")
    public void getCoin(HttpServletRequest request, HttpServletResponse response) {
        JsonResponse result=new JsonResponse(0,"操作成功");
        try {
            int userId= WebUtil.getParam(request,"userId",0);
            if(userId==0){
                throw new Exception("操作失败，用户ID为空！");
            }

            List<FourObject> mapWhere=new ArrayList<FourObject>();
            mapWhere.add(new FourObject("userId",userId));
            mapWhere.add(new FourObject("targetDate",TimeUtil.getCurrentDate()));
            List<Koala_Step> steps=this.koala_StepService.listSteps(mapWhere);
            if(steps.size()>0){
                Koala_Step s=steps.get(0);
                int status=s.getStatus();
               if(status==0){
                   throw new Exception("操作失败,运动还未达标！");
               }else if(status==1){
                   s.setStatus(2);
                   Koala_Coin coin=new Koala_Coin();
                   coin.setUserId(userId);
                   coin.setDirection(1);
                   coin.setCoin(1);
                   coin.setType(0);//系统赠送
                   coin.setRemark("运动达标领取");
                   this.koala_CoinService.addCoin(coin);
                   this.koala_StepService.updateStep(s);
               }else if(status==2){
                   throw new Exception("操作失败，已经领取考拉币！");
               }
            }

        }catch (Exception e) {
            e.printStackTrace();
            result=new JsonResponse(-1,e.getMessage());
            logger.info(e.getMessage());
        }
        Gson gson = new Gson();
        RenderKit.renderJson(response, gson.toJson(result));
    }

}
