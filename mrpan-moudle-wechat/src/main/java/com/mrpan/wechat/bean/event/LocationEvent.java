package com.mrpan.wechat.bean.event;

/**
 * 地理位置信息
 */
public class LocationEvent extends BaseEvent {
	private double Latitude; // 地理位置纬度
	private double Longitude; // 地理位置经度
	private double Precision; // 地理位置精度

	@Override
	public String setEvent() {
		return "LOCATION";
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getPrecision() {
		return Precision;
	}

	public void setPrecision(double precision) {
		Precision = precision;
	}

}
