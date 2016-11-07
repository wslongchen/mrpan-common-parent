package com.mrpan.wechat.bean.results.handler;

import com.mrpan.wechat.bean.req.ImageMessage;
import com.mrpan.wechat.bean.req.TextMessage;
import com.mrpan.wechat.bean.req.VideoMessage;
import com.mrpan.wechat.bean.req.VoiceMessage;
import com.mrpan.wechat.bean.resp.MusicMessage;
import com.mrpan.wechat.bean.resp.NewsMessage;
import com.mrpan.wechat.bean.resp.bean.*;
import com.mrpan.wechat.bean.results.utils.XStreamFactory;
import com.thoughtworks.xstream.XStream;

/**
 * 消息处理类
 */
public class MessageHandler {
	
	/**
	 * 文本消息
	 * @param text
	 * @return
	 */
	public static String TextMsg(TextMessage text){
		String result="";
		if(text!=null){
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml", TextMessage.class);
			result = xs.toXML(text);
		}
		return result;
	}
	
	/**
	 * 图片消息
	 * @param image
	 * @return
	 */
	public static String ImageMsg(ImageMessage image){
		String result="";
		if(image!=null){
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml", ImageMessage.class);
			xs.aliasField("Image", Image.class,"image");
			result = xs.toXML(image);
		}
		return result;
	}
	
	/**
	 * 音乐消息
	 * @param music
	 * @return
	 */
	public static String MusicMsg(MusicMessage music){
		String result="";
		if(music!=null){
		  XStream xs = XStreamFactory.init(true);
		  xs.alias("xml",MusicMessage.class);
		  xs.aliasField("Music", Music.class, "music");
		  result=xs.toXML(music);
		}
		return result;
	}
	
	/**
	 * 图文消息
	 * @param news
	 * @return
	 */
	public static String NewsMsg(NewsMessage news){
		String result="";
		if(news!=null){
		  XStream xs = XStreamFactory.init(true);
		  xs.alias("xml",NewsMessage.class);
		  xs.addImplicitCollection(Articles.class,"list", "item", Item.class);
		  xs.aliasField("Articles", NewsMessage.class,"articles");
		  result = xs.toXML(news);
		}
		return result;
	}
	
	/**
	 * 视频消息
	 * @param video
	 * @return
	 */
	public static String VideoMsg(VideoMessage video){
		String result="";
		if(video!=null){
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml",VideoMessage.class);
			xs.aliasField("Video",Video.class,"video");
			result = xs.toXML(video);
		}
		return result;
	}
	
	/**
	 * 语音消息
	 * @param voice
	 * @return
	 */
	public static String VoiceMsg(VoiceMessage voice){
		String result="";
		if(voice!=null){
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml",VoiceMessage.class);
			xs.aliasField("Voice", Voice.class,"voice");
			result = xs.toXML(voice);
		}
		return result;
	}
}
