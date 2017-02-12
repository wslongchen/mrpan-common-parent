package com.mrpan.wechat.material;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.Connection;
import com.mrpan.wechat.bean.material.*;
import com.mrpan.wechat.bean.message.NewsEntity;
import com.mrpan.wechat.bean.results.WechatResult;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by mrpan on 2017/2/12.
 */
public class MeaterConn extends Connection{
    //新增临时素材
    private String addMeaterPath="https://api.weixin.qq.com/cgi-bin/media/upload";
    //获取临时素材
    private String getTempMeater="https://api.weixin.qq.com/cgi-bin/media/get";
    //新增永久图文素材
    private String addForeverPath="https://api.weixin.qq.com/cgi-bin/material/add_news";
    //新增其他类型的永久素材
    private String addForverOther="http://api.weixin.qq.com/cgi-bin/material/add_material";
    //获取永久素材
    private String getForverPath="https://api.weixin.qq.com/cgi-bin/material/get_material";
    //删除永久素材
    private String deleteForverPath="https://api.weixin.qq.com/cgi-bin/material/del_material";
    //修改永久图文素材
    private String updateForverNewsPath="https://api.weixin.qq.com/cgi-bin/material/update_news";
    //获取素材总数
    private String countMaterialPath="https://api.weixin.qq.com/cgi-bin/material/get_materialcount";
    //获取素材列表
    private String getListMeater="https://api.weixin.qq.com/cgi-bin/material/batchget_material";

    /**
     * 新增临时素材
     * @param accessToken	授权token
     * @param mediaType		媒体id
     * @param mediaPath     媒体path所在的位置
     * @return	 reuslt.isSuccess==true并且result.type==1 则返回MeterResult,表示成功
     * 如果rreuslt.isSuccess==true并且result.type==0  则返回 MaterMsg, 否则提示对应的信息
     */
    public WechatResult addTempMeater(String accessToken, String mediaType, String mediaPath){
        WechatResult resultObj = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        params.put("type",mediaType);
        String result = uploadMediaMethod(params,"GET",addMeaterPath,mediaPath, "");
        resultObj = ConvertMeter(result);
        return resultObj;
    }

    /**
     * 获取素材列表
     * @param accessToken  授权token
     * @param type		    素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset	    从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count      返回素材的数量，取值在1到20之间
     * @return
     *  如果Result.isSuccess==true并且Result.type==0,getObj可强制为MaterMsg对象
     *  如果Result.isSuccess==true并且Result.type==1,getObj可强制为NewsList对象
     * 	如果Result.isSuccess==true并且Result.type==2,getObj可强制为OtherList对象
     *  否则将其getObj转换为字符串
     */
    public WechatResult getMeaterList(String accessToken,String type,int offset,int count){
        WechatResult resultObj = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token",accessToken);
        //post params
        TreeMap<String,String> postParams = new TreeMap<String,String>();
        postParams.put("type",type);
        postParams.put("offset",offset+"".trim());
        postParams.put("count",count+"".trim());
        String data = JSONObject.toJSONString(postParams);
        String result = HttpsDefaultExecute("POST",getListMeater,params,data);
        resultObj = ConvertMaterList(result,type);		//注意此处的type应该与参数中的type保持一致
        return resultObj;
    }

    /**
     * 获取临时素材
     * @param accessToken	授权token
     * @param meadiId		媒体id
     * @return	 reuslt.isSuccess==true 表示获取成功并保存在 savePath目录下
     *  如果reuslt.isSuccess==false 则可将查看Result.getObj()的信息
     */
    public WechatResult getTempMeater(String accessToken,String meadiId,String savePath){
        WechatResult result = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token",accessToken);
        params.put("media_id", meadiId);
        result = downMeaterMetod(params,"GET",getTempMeater,savePath);
        return result;
    }


    /**
     * 将获取素材列表的数据转换为对象
     * @param jsonData	json 格式的字符串
     * @param type
     * @return
     *  如果Result.isSuccess==true并且Result.type==0,getObj可强制为MaterMsg对象
     *  如果Result.isSuccess==true并且Result.type==1,getObj可强制为NewsList对象
     * 	如果Result.isSuccess==true并且Result.type==2,getObj可强制为OtherList对象
     *  否则将其getObj转换为字符串
     */
    private WechatResult ConvertMaterList(String jsonData,String type){
        WechatResult result = new WechatResult();
        if(jsonData!=null&&!"".equals(jsonData)){
            JSONObject object = JSONObject.parseObject(jsonData);
            if(object.containsKey("item")){		//表示返回成功
                if(type.equalsIgnoreCase("news")){		//用户输入的类型是否为news
                    NewsList newsList = JSONObject.parseObject(jsonData,NewsList.class); //
                    JSONArray newsItemsStr = object.getJSONArray("item");
                    //item content
                    List<NewsItem> newsListw = new ArrayList<NewsItem>();
                    for(int i=0;i<newsItemsStr.size();i++){			//将其中的集合设置值
                        JSONObject objct = newsItemsStr.getJSONObject(i);
                        NewsItem item = JSONObject.parseObject(objct.toString(),NewsItem.class);
                        String entitystr = objct.getJSONObject("content").getString("news_item");
                        List<NewsEntity> entityArray = JSONObject.parseArray(entitystr,NewsEntity.class);
                        item.setNewsItem(entityArray);
                        newsListw.add(item);
                    }
                    newsList.setNewsItems(newsListw);
                    result.setObj(newsList);
                    result.setSuccess(true);
                    result.setType(1);
                }else{
                    OtherList list = JSONObject.parseObject(jsonData, OtherList.class);
                    String itemsData = object.getString("item");
                    List<OtherItem> itemArray = JSONObject.parseArray(itemsData,OtherItem.class);
                    list.setList(itemArray);
                    result.setObj(list);
                    result.setSuccess(true);
                    result.setType(2);
                }
            }else if(object.containsKey("errcode")){ //表示返回失败!
                MaterMsg msg = ConvertMaterMsg(jsonData);
                result.setObj(msg);
                result.setSuccess(true);
            }
        }else{
            result.setObj("Convert data is null!");
        }
        return result;
    }


    /**
     * 获取素材的总数
     * 包括语言,视频,图片,图文总数量
     * 如果Result.iSuccess==true,并且Result.type==1表示获取成功,result.getObj可强制转换为CountEntity对象
     * 如果Result.iSuccess==true,并且Result.type==0表示获取到了错误吗,result.getObj可强制转换为MaterMsg对象
     * 否则 可将result.getObj 转换为字符串
     * @param accessToken
     */
    public WechatResult getCountMeterial(String accessToken){
        WechatResult resultObj = new WechatResult();
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token",accessToken);
        String result = HttpsDefaultExecute("GET",countMaterialPath,params,"");
        if(result!=null&&!"".equals(result)){
            JSONObject obj = JSONObject.parseObject(result);
            if(obj.containsKey("voice_count")){			//表示获取成功!
                CountEntity entity = JSONObject.parseObject(result, CountEntity.class);
                resultObj.setObj(entity);
                resultObj.setSuccess(true);
                resultObj.setType(1);
            }else if(obj.containsKey("errcode")){		//获取失败!
                MaterMsg msg = JSONObject.parseObject(result,MaterMsg.class);
                resultObj.setObj(msg);
                resultObj.setSuccess(true);
            }else{
                resultObj.setObj(result);
            }
        }else{
            resultObj.setObj("result is null");
        }
        return resultObj;
    }

    /**
     * 修改永久图文消息
     * @param accessToken   授权token
     * @param mediaId		消息id 即上传所得的mediaId
     * @param index			要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param list			多图文中的图文选项
     * @return   MaterMsg.errcode==0  表示修改成功
     */
    public MaterMsg updateNewsForver(String accessToken,String mediaId,int index,List<NewsEntity> list){
        MaterMsg msg = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token",accessToken);
        //post data
        TreeMap<String,Object> postParams = new TreeMap<String,Object>();
        postParams.put("index", index);
        postParams.put("media_id", mediaId);
        postParams.put("articles",list);
        String data = JSONObject.toJSONString(postParams);
        String result =HttpsDefaultExecute("POST",updateForverNewsPath,params,data);
        msg = ConvertMaterMsg(result);
        return msg;
    }

    /**
     * 删除永久素材
     * @param accessToken	授权token
     * @param mediaId		消息的mediaId
     * @return				MaterMsg.errcode==0 表示删除成功
     */
    public MaterMsg deleteForverNews(String accessToken,String mediaId){
        MaterMsg msg = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        //post params
        TreeMap<String,String> postParams = new TreeMap<String,String>();
        postParams.put("media_id", mediaId);
        String data = JSONObject.toJSONString(postParams);
        String result = HttpsDefaultExecute("POST",deleteForverPath,params,data);
        msg = ConvertMaterMsg(result);
        return msg;
    }

    /**
     * 获取永久图文素材
     * @param accessToken  授权token
     * @param mediaId	   消息id
     * @return	 * @return	 reuslt.isSuccess==true并且result.type==1 则返回NewsEntity的集合List,
     * 如果rreuslt.isSuccess==true并且result.type==0  则返回 MaterMsg, 否则提示对应的信息
     */
    public WechatResult getForverNews(String accessToken,String mediaId){
        WechatResult resultObj = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        //post params
        TreeMap<String,String> postParams = new TreeMap<String,String>();
        postParams.put("media_id",mediaId);
        String data = JSONObject.toJSONString(postParams);
        String result = HttpsDefaultExecute("POST",getForverPath,params,data);
        resultObj = ConvertGetForMeter(result);
        return resultObj;
    }

    /**
     *
     * 新增其他类型的永久素材
     * @param accessToken		授权token
     * @parma mediaPath   		待上次媒体的路径,暂时只支持网络图片
     * @return	 reuslt.isSuccess==true并且result.type==1 则返回MeterResult,
     * 如果rreuslt.isSuccess==true并且result.type==0  则返回 MaterMsg, 否则提示对应的信息
     */
    public String addForverOther(String accessToken,String mediaPath){
        String result="";
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token",accessToken);
        result = defaultUploadImg(addForverOther,params,"POST",mediaPath,"");
        return result;
    }
    /**
     * 新增永久图文素材
     * @param accessToken   授权token
     * @param news			图文素材 NewsEntity集合  只能一次添加10个图文素材
     * @return	 reuslt.isSuccess==true并且result.type==1 则返回MeterResult,
     * 如果rreuslt.isSuccess==true并且result.type==0  则返回 MaterMsg, 否则提示对应的信息
     */
    public WechatResult addForeverNew(String accessToken,List<NewsEntity> news){
        WechatResult resultObj = null;
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        //post params
        TreeMap<String,List<NewsEntity>> postParams = new TreeMap<String,List<NewsEntity>>();
        postParams.put("articles",news);
        String data = JSONObject.toJSONString(postParams);
        String result =  HttpsDefaultExecute("POST",addForeverPath,params,data);
        resultObj = ConvertMeter(result);
        return resultObj;
    }

    /**
     * 转换获取永久素材
     * @param jsonData   json格式的字符串
     * @return	 * @return	 reuslt.isSuccess==true并且result.type==1 则返回 NewsEntity的集合List,
     * 如果rreuslt.isSuccess==true并且result.type==0  则返回 MaterMsg, 否则提示对应的信息
     */
    private WechatResult ConvertGetForMeter(String jsonData){
        WechatResult result = new WechatResult();
        if(jsonData!=null&&!"".equals(jsonData)){
            JSONObject obj = JSONObject.parseObject(jsonData);
            if(obj.containsKey("news_item")){				//获取成功
                String json = obj.getString("news_item");
                List<NewsEntity> list = JSONObject.parseArray(json,NewsEntity.class);
                result.setObj(list);
                result.setSuccess(true);
                result.setType(1);
            }else if(obj.containsKey("errcode")){			//获取失败
                MaterMsg msg = JSONObject.parseObject(jsonData,MaterMsg.class);
                result.setObj(msg);
                result.setSuccess(true);
            }else{
                result.setObj("Convert type not exist");
            }
        }else{
            result.setObj("Convert GetforMeter is null");
        }
        return result;
    }

    /**
     * 将上传文件后返回的字符串为转为java对象
     * @param jsonData
     * @return	 reuslt.isSuccess==true并且result.type==1 则返回MeterResult,
     * 如果rreuslt.isSuccess==true并且result.type==0  则返回 MaterMsg, 否则提示对应的信息
     */
    private WechatResult ConvertMeter(String jsonData){
        WechatResult result = new WechatResult();
        if(jsonData!=null&&!"".equals(jsonData)){
            JSONObject jsonObj = JSONObject.parseObject(jsonData);
            if(jsonObj.containsKey("media_id")){		//表示上传成功
                MeterResult meter = JSONObject.parseObject(jsonData,MeterResult.class);
                result.setObj(meter);
                result.setSuccess(true);
                result.setType(1);
            }else if(jsonObj.containsKey("errcode")){    //表示上传失败
                MaterMsg msg = JSONObject.parseObject(jsonData,MaterMsg.class);
                result.setObj(msg);
            }else{										//other
                result.setObj("Convert type not's exist");
            }
        }else{
            result.setObj("Convert content is null");
        }
        return result;
    }

    /**
     * 将json格式的字符串转换为 MaterMsg
     * @param jsonData
     * @return MaterMsg.errcode==0 表示成功
    }
     */
    private MaterMsg ConvertMaterMsg(String jsonData){
        MaterMsg msg = null;
        if(jsonData!=null&&!"".equals(jsonData)){
            msg = JSONObject.parseObject(jsonData,MaterMsg.class);
        }
        return msg;
    }
}
