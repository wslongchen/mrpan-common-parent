package com.mrpan.wechat.rtma;

import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.Connection;
import com.mrpan.wechat.bean.report.InterAnlyse;
import com.mrpan.wechat.bean.report.MsgAnalyse;
import com.mrpan.wechat.bean.report.NewsAnlyse;
import com.mrpan.wechat.bean.report.UserAnlyse;
import com.mrpan.wechat.bean.results.WechatResult;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by mrpan on 2017/1/22.
 */
public class RtmaConn extends Connection {
    private final String USER="user";			//用户实体返回的标识
    private final String NEWS="news";			//图文实体标识
    private final String INF="interface";		//接口实体标识
    private final String MSG="msg";				//消息实体标识


    // 用户分析接口获取用户增减数据 (跨度 7 天)
    private String getUsersummary ="https://api.weixin.qq.com/datacube/getusersummary";
    // 获取累计用户数据(跨度 7 天)
    private String getUsercumulate="https://api.weixin.qq.com/datacube/getusercumulate";


    /*************图文分析****************/
    //获取图文群发每日数据(跨度 1天)
    private String getarticlesummary="https://api.weixin.qq.com/datacube/getarticlesummary";
    //图文群发总数据(跨度 1 天)
    private String getarticletotal="https://api.weixin.qq.com/datacube/getarticletotal";
    //获取图文统计数据(跨度 3 天)
    private String getuserread = "https://api.weixin.qq.com/datacube/getuserread";
    //获取图文统计分时数据(跨度 1 天)
    private String getuserreadhour="https://api.weixin.qq.com/datacube/getuserreadhour";
    //获取图文分享数据 (跨度 7天)
    private String getusershare = "https://api.weixin.qq.com/datacube/getusershare";
    //获取图文分享转发分时数据(跨度 1 天)
    private String getusersharehour="https://api.weixin.qq.com/datacube/getusersharehour";

    /*********************接口分析数据接口*****************/
    //获取接口分析数据 (跨度 30 天)
    private String getinterfacesummary="https://api.weixin.qq.com/datacube/getinterfacesummary";
    //获取接口分析分时数据
    private String getinterfacesummaryhour="https://api.weixin.qq.com/datacube/getinterfacesummaryhour";

    /*********************消息数据分析接口*****************/
    //获取消息发送概况数据(跨度 7 天)
    private String getupstreammsg="https://api.weixin.qq.com/datacube/getupstreammsg";
    //获取消息分送分时数据(跨度 1 天)
    private String getupstreammsghour="https://api.weixin.qq.com/datacube/getupstreammsghour";
    //获取消息发送周数据 (跨度 30 天)
    private String getupstreammsgweek="https://api.weixin.qq.com/datacube/getupstreammsgweek";
    //获取消息发送月数据(跨度 30 天)
    private String getupstreammsgmonth="https://api.weixin.qq.com/datacube/getupstreammsgmonth";
    //获取消息发送分布数据 (跨度 15天)
    private String getupstreammsgdist="https://api.weixin.qq.com/datacube/getupstreammsgdist";
    //获取消息发送分布周数据(跨度 30天)
    private String getupstreammsgdistweek="https://api.weixin.qq.com/datacube/getupstreammsgdistweek";
    //获取消息发送分布月数据(跨度 30天)
    private String getupstreammsgdistmonth="https://api.weixin.qq.com/datacube/getupstreammsgdistmonth";


    /**
     * 获取消息发送分布月数据(时间跨度为30天)
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return 判断Result中的isSuccess List
     * 	如果为true,则可强制为MsgAnalyseEntity对象
     */
    public WechatResult getupstreammsgdistmonth(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsgdistmonth,accessToken,startDate,endDate,MSG);
    }
    /**
     *获取消息发送分布周数据(30天)
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getupstreammsgdistweek(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsgdistweek,accessToken,startDate,endDate,MSG);
    }
    /**
     * 获取消息发送分布数据 (15天)
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getupstreammsgdist(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsgdist,accessToken,startDate,endDate,MSG);
    }
    /**
     * 获取消息发送月数据(跨度 30 天)
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getupstreammsgmonth(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsgmonth,accessToken,startDate,endDate,MSG);
    }
    /**
     * 获取消息发送周数据 (跨度 30 天)
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getupstreammsgweek(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsgweek,accessToken,startDate,endDate,MSG);
    }

    /**
     * 获取消息发送概况数据(跨度 7 天)
     * 获取消息发送的概况数据
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess
     * 		如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getupstreammsg(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsg,accessToken,startDate,endDate,MSG);
    }
    /**
     * 获取消息分送分时数据
     * 获取消息分送分时数据(跨度 1 天)
     * @param accessToken	授权token
     * @param startDate     开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getupstreammsghour(String accessToken,String startDate,String endDate){
        return baseMethod(getupstreammsghour,accessToken,startDate,endDate,MSG);
    }
    /**
     *  获取接口分析数据 (跨度 30 天)
     * @param accessToken  授权token
     * @param startDate	   开始日期
     * @param endDate	   结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为InterAnlyseEntity 的List集合
     */
    public WechatResult getinterfacesummary(String accessToken,String startDate,String endDate){
        return baseMethod(getinterfacesummary,accessToken,startDate,endDate,INF);
    }

    /**
     * 获取接口分析分时数据(跨度 1 天)
     * @param accessToken  授权token
     * @param startDate	   开始日期
     * @param endDate	 结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为InterAnlyseEntity 的List集合
     */
    public WechatResult getinterfacesummaryhour(String accessToken,String startDate,String endDate){
        return baseMethod(getinterfacesummaryhour,accessToken,startDate,endDate,INF);
    }


    /**
     * 获取图文统计分时数据
     * @param accessToken	授权token
     * @param startDate		开始时间
     * @param endDate		结束时间
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为NewsAnlyseEntity 的List集合
     */
    public WechatResult getArticleuserreadhour(String accessToken,String startDate,String endDate){
        return baseMethod(getuserreadhour,accessToken,startDate,endDate,NEWS);
    }

    /**
     * 获取图文分享数据(时间跨度为 7天)
     * @param accessToken	授权token
     * @param startDate		开始时间
     * @param endDate		结束时间
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为NewsAnlyseEntity 的List集合
     */
    public WechatResult getArticleusershare(String accessToken,String startDate,String endDate){
        return baseMethod(getusershare,accessToken,startDate,endDate,NEWS);
    }

    /**
     * 获取图文分享转发分时数据(时间跨度为 1天)
     * @param accessToken   授权token
     * @param startDate		开始日期
     * @param endDate		结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为List<MsgAnalyseEntity>
     */
    public WechatResult getArticleusersharehour(String accessToken,String startDate,String endDate){
        return baseMethod(getusersharehour,accessToken,startDate,endDate,NEWS);
    }


    /**
     * 获取图文统计数据接口(时间跨度为3天)
     * @param accessToken	授权token
     * @param startDate	   开始日期
     * @param endDate	结束日期
     * @return  判断Result中的isSuccess 如果为true,则可强制转换为NewsAnlyseEntity
     */
    public WechatResult getArticleUserread(String accessToken,String startDate,String endDate){
        return baseMethod(getuserread,accessToken,startDate,endDate,NEWS);
    }


    /**
     * 获取图文群发每日数据(日期跨度为 1 天)
     * @param accessToken	授权token
     * @param startDate	    开始日期
     * @param endDate	  结束日期
     * @return   result.iscusse==true时,可强制转换为 NewsAnlyseEntity 集合
     */
    public WechatResult getArticlesummary(String accessToken,String startDate,String endDate){
        return baseMethod(getarticlesummary,accessToken,startDate,endDate,NEWS);
    }

    /**
     * 获取图文群发总数据		跨度为(1 天)
     * @param accessToken	授权token
     * @param startDate		开始日期
     * @param endDate		结束日期
     * @return   result.isSuccess为true时,可强制转换为NewsAnlyseEntity 的list集合
     */
    public WechatResult getArticletotal(String accessToken,String startDate,String endDate){
        return baseMethod(getarticletotal,accessToken,startDate,endDate,NEWS);
    }


    /**
     * 用户分析接口
     *
     * @param accessToken
     *            授权token
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     *            @return result.issuccess为true时,可强制转换为UserAnlyseEntity 集合
     */
    public WechatResult getUserSummary(String accessToken, String startDate,
                                       String endDate) {
        return baseMethod(getUsersummary,accessToken,startDate,endDate,USER);
    }

    /**
     * 获取累计用户数据
     * (跨度为 7 天)
     * @param accessToken
     *            授权token
     * @param startDate
     *            开始统计的开始日期
     * @param endDate
     *            开始统计的结束日期
     * @return  统计数据的	result.issuccess为true时,可强制转换为UserAnlyseEntity 集合
     */
    public WechatResult getUsercumulate(String accessToken, String startDate,
                                        String endDate) {
        return baseMethod(getUsercumulate,accessToken,startDate,endDate,USER);
    }

    /**
     * 通过传入不同的api 获取不同的数据
     * @param path  api的类型不同传入不同的api
     * @param accessToken 授权码
     * @param startDate  开始日期
     * @param endDate	 结束日期
     * @param type 		消息类型
     */
    private WechatResult baseMethod(String path,String accessToken,String startDate,String endDate,String type){
        String result = "";
        WechatResult resultObj = null;
        //url参数
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("access_token", accessToken);
        //post参数map
        TreeMap<String,String> data = new TreeMap<String,String>();
        data.put("begin_date", startDate);
        data.put("end_date", endDate);
        String paramsData = JSONObject.toJSONString(data);
        result = HttpsDefaultExecute("POST", path, params, paramsData);
        if(type.equals(USER)){				//转换为用户实体
            resultObj =ConvertEntity(result,UserAnlyse.class);
        }else if(type.equals(NEWS)){		//返回图文实体
            resultObj = ConvertEntity(result,NewsAnlyse.class);
        }else if(type.equals(INF)){			//返回接口实体类型
            resultObj =ConvertEntity(result,InterAnlyse.class);
        }else if(type.equals(MSG)){			//消息实体类型
            resultObj =ConvertEntity(result,MsgAnalyse.class);
        }else{
            resultObj = new WechatResult();
            resultObj.setObj("msg type not exist");
        }
        return resultObj;
    }




    /**
     * 将json格式的字符串转换为 消息分析对象
     * @param jsonData	json格式的数据
     * @return		success如果true 则可强制转换为List<MsgAnalyseEntity>  否则为error Info
     */
    private WechatResult ConvertEntity(String jsonData,Class T){
        WechatResult result = new WechatResult();
        List<MsgAnalyse> list = null;
        if(jsonData!=null&&!"".equals(jsonData)){
            JSONObject jsonObj = JSONObject.parseObject(jsonData);
            if(jsonObj.containsKey("list")){			//判断是否返回正确的结果
                String jsonArray = jsonObj.getString("list").toString();
                list = JSONObject.parseArray(jsonArray,T);
                if(list!=null&&list.size()>0){
                    result.setSuccess(true);
                    result.setObj(list);
                }else{
                    result.setObj("list is null");
                }
            }else{
                result.setObj(jsonData);
            }
        }else{
            result.setObj("params is null");
        }
        return result;
    }
}
