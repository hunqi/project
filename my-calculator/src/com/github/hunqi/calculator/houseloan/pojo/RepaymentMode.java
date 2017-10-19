package com.github.hunqi.calculator.houseloan.pojo;

public enum RepaymentMode {
	AVERAGE_CAPITAL_PLUS_INTEREST,
	AVERAGE_CAPITAL;
	
	public static RepaymentMode get(int idx){
		return RepaymentMode.values()[idx];
	}
	
}
