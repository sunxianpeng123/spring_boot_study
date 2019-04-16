package com.xiaohulu.bean;

public class PriceAndMsgTotalBean {

	public String getSourcegname() {
		return sourcegname;
	}

	public void setSourcegname(String sourcegname) {
		this.sourcegname = sourcegname;
	}

	public double getAll_gift_value() {
		return all_gift_value;
	}

	public void setAll_gift_value(double all_gift_value) {
		this.all_gift_value = all_gift_value;
	}

	public long getGift_sender_num() {
		return gift_sender_num;
	}

	public void setGift_sender_num(long gift_sender_num) {
		this.gift_sender_num = gift_sender_num;
	}

	public long getAll_msg_num() {
		return all_msg_num;
	}

	public void setAll_msg_num(long all_msg_num) {
		this.all_msg_num = all_msg_num;
	}

	public long getMsg_sender_num() {
		return msg_sender_num;
	}

	public void setMsg_sender_num(long msg_sender_num) {
		this.msg_sender_num = msg_sender_num;
	}

	private String sourcegname;
	private double all_gift_value;
	private long gift_sender_num;
	private long all_msg_num;
	private long  msg_sender_num;
	private long gift_sender_num_all;

	public long getGift_sender_num_all() {
		return gift_sender_num_all;
	}

	public void setGift_sender_num_all(long gift_sender_num_all) {
		this.gift_sender_num_all = gift_sender_num_all;
	}
}
