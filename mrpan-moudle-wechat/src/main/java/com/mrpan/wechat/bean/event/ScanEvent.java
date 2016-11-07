package com.mrpan.wechat.bean.event;

/**
 * 用户已关注事件
 */
public class ScanEvent extends BaseEvent {
	private String EventKey; // 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	private String Ticket; // 二维码的ticket，可用来换取二维码图片

	@Override
	public String setEvent() {
		return "SCAN";
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
