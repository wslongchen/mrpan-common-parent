package com.mrpan.wechat.bean.resp;


import com.mrpan.wechat.bean.resp.bean.Image;

/**
 * 回复图片消息
 */
public class ImageMessage extends BaseMessage {
	private Image image;

	@Override
	public String setMsgType() {
		return "image";
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
