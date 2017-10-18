package com.github.hunqi.calculator.houseloan.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import com.github.hunqi.calculator.houseloan.pojo.Loan;

class AverageCapitalPlusInterestCalculator implements Calculator {

	@Override
	public BigDecimal getMonthlyPayments(Loan loan) {
		
		BigDecimal amount = BigDecimal.valueOf(loan.getAmount());
		BigDecimal annualRate = BigDecimal.valueOf(loan.getAnnualInterestRate()).divide(BigDecimal.valueOf(100));
		
		BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), MathContext.DECIMAL64);
		BigDecimal monthlyPayments = amount.multiply(monthlyRate.
				multiply(getMonthlyPaymentsFactor(monthlyRate, loan.getRepaymentMonths()))).
				divide(getMonthlyPaymentsFactor(monthlyRate, loan.getRepaymentMonths()).subtract(BigDecimal.ONE), MathContext.DECIMAL64);
		
		return monthlyPayments;
	}
	
	private BigDecimal getMonthlyPaymentsFactor(BigDecimal monthlyRate, int raymentMonths){
		return monthlyRate.add(BigDecimal.ONE).pow(raymentMonths, MathContext.DECIMAL64);
	}
	
	@Override
	public BigDecimal getTotalPayments(Loan loan) {
		return getMonthlyPayments(loan).multiply(
				BigDecimal.valueOf(loan.getRepaymentMonths()), MathContext.DECIMAL32);
	}
	
	
	
}
