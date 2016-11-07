package com.mrpan.wechat.pay.response;

/**
 * 网页中获取getTicket中的链接
 */
public class TicketResponse implements java.io.Serializable{
	private int errcode;
	private String errmsg;
	private String ticket;
	private int expires_in;

	public TicketResponse() {
		super();
	}

	public TicketResponse(int errcode, String errmsg, String ticket,
			int expires_in) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.ticket = ticket;
		this.expires_in = expires_in;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

}
