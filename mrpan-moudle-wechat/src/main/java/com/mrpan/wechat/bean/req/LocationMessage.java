package com.mrpan.wechat.bean.req;

public class LocationMessage extends BaseMessage{
	private double Location_X;			//地理位置维度
	private double Location_Y;			//地理位置经度
	private int Scale;					//地图缩放大小
	private String Label;				//地理位置信息
	
	@Override
	public String SetMsgType() {
		return "location";
	}
	
	public double getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(double locationX) {
		Location_X = locationX;
	}

	public double getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(double locationY) {
		Location_Y = locationY;
	}

	public int getScale() {
		return Scale;
	}

	public void setScale(int scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}
}
