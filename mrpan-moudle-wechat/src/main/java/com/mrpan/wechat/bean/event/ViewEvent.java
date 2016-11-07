package com.mrpan.wechat.bean.event;

/**
 * 自定义菜单跳转链接
 */
public class ViewEvent extends BaseEvent {
	private String EventKey; // 事件KEY值，设置的跳转URL

	@Override
	public String setEvent() {
		return "VIEW";
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
