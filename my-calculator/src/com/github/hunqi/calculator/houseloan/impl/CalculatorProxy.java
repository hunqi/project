package com.github.hunqi.calculator.houseloan.impl;

import java.math.BigDecimal;

import com.github.hunqi.calculator.houseloan.pojo.Loan;
import com.github.hunqi.calculator.houseloan.pojo.RepaymentMode;

public class CalculatorProxy {
	
	private Calculator calculator;
	
	public CalculatorProxy(RepaymentMode mode){
		if(RepaymentMode.AVERAGE_CAPITAL.equals(mode)){
			calculator = new AverageCapitalCalculator();
		}else {
			calculator = new AverageCapitalPlusInterestCalculator();
		}
	}
	
	public BigDecimal getMonthlyPayments(Loan loan){
		return calculator.getMonthlyPayments(loan);
	}
	
	public BigDecimal getTotalPayments(Loan loan){
		return calculator.getTotalPayments(loan);
	}
	
}
