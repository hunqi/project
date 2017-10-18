package com.github.hunqi.calculator.houseloan.impl;

import java.math.BigDecimal;

import com.github.hunqi.calculator.houseloan.pojo.Loan;

public class CalculatorProxy {
	
	private Calculator calculator;
	
	public CalculatorProxy() {
		if(calculator == null)
			calculator = new AverageCapitalPlusInterestCalculator();
	}
	
	public BigDecimal getMonthlyPayments(Loan loan){
		return calculator.getMonthlyPayments(loan);
	}
	
	public BigDecimal getTotalPayments(Loan loan){
		return calculator.getTotalPayments(loan);
	}
	
}
