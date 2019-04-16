package com.xiaohulu.bean;

public class TyrantsNumAndGiftPriceBean {

	public int getTyrants_num() {
		return tyrants_num;
	}

	public void setTyrants_num(int tyrants_num) {
		this.tyrants_num = tyrants_num;
	}

	private int tyrants_num;
	private double all_gift_value;
	private int level;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	private String memo;


	public double getAll_gift_value() {
		return all_gift_value;
	}

	public void setAll_gift_value(double all_gift_value) {
		this.all_gift_value = all_gift_value;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(int platform_id) {
		this.platform_id = platform_id;
	}

	public String getStatistics_date() {
		return statistics_date;
	}

	public void setStatistics_date(String statistics_date) {
		this.statistics_date = statistics_date;
	}

	private int platform_id;
	private String statistics_date;


}
