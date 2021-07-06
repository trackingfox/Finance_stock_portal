package com.JPA.stock.entity;

public class DataTemp {

	private int DataId;

	private String Date;

	private float open_price;

	private float close_price;

	private float high_price;

	private float low_price;

	private Stock stock;

	public int getDataId() {
		return DataId;
	}

	public void setDataId(int dataId) {
		DataId = dataId;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

//	public DataTemp(int date, float open_price, float close_price, float high_price, float low_price) {
//		super();
//		Date = date;
//		this.open_price = open_price;
//		this.close_price = close_price;
//		this.high_price = high_price;
//		this.low_price = low_price;
//	}

//	public Date getDate() {
//		Date date1 = null;
//		try {
//			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(Date);
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//		return date1;
//	}

	public String getDate() {
		return Date;

	}

	public void setDate(String date) {
		this.Date = date;

	}

	public float getOpen_price() {
		return open_price;
	}

	public void setOpen_price(float open_price) {
		this.open_price = open_price;
	}

	public float getClose_price() {
		return close_price;
	}

	public void setClose_price(float close_price) {
		this.close_price = close_price;
	}

	public float getHigh_price() {
		return high_price;
	}

	public void setHigh_price(float high_price) {
		this.high_price = high_price;
	}

	public float getLow_price() {
		return low_price;
	}

	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}

	@Override
	public String toString() {
		return "Data [Date=" + Date + ", open_price=" + open_price + ", close_price=" + close_price + ", high_price="
				+ high_price + ", low_price=" + low_price + "]";
	}

}
