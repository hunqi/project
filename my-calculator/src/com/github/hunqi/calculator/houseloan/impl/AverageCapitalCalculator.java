package com.github.hunqi.calculator.houseloan.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import com.github.hunqi.calculator.houseloan.pojo.Loan;
//import com.github.hunqi.tool.debug.P; //just for debugging
import com.github.hunqi.tool.P;

class AverageCapitalCalculator implements Calculator {

	@Override
	public BigDecimal getMonthlyPayments(Loan loan) {
		return getMonthlyRepaymentCapital(loan).
				add(getOutstandingCapitalBalance(loan).
						multiply(getMonthlyInterestRate(loan), MathContext.DECIMAL64), MathContext.DECIMAL64).
						multiply(BigDecimal.valueOf(10000), new MathContext(6));
	}
	
	@Override
	public BigDecimal getTotalPayments(Loan loan) {
		return BigDecimal.valueOf(loan.getAmount()).add(getTotalInterest(loan), new MathContext(8));
	}
	
	private BigDecimal getTotalInterest(Loan loan){
		return getMonthlyRepaymentCapital(loan).
				add(BigDecimal.valueOf(loan.getAmount()).multiply(getMonthlyInterestRate(loan)), MathContext.DECIMAL64).
				add(getMonthlyRepaymentCapital(loan).multiply(getMonthlyInterestRate(loan).add(BigDecimal.ONE), MathContext.DECIMAL64))
				.divide(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(loan.getRepaymentMonths()), MathContext.DECIMAL64)
				.subtract(BigDecimal.valueOf(loan.getAmount()));
	}
	
	private BigDecimal getOutstandingCapitalBalance(Loan loan){
		BigDecimal outstandingCapitalBalance = BigDecimal.valueOf(loan.getAmount()).
				multiply(BigDecimal.valueOf(loan.getRepaymentMonths() - loan.getHasRepaidMonths()), MathContext.DECIMAL64).
				divide(BigDecimal.valueOf(loan.getRepaymentMonths()), MathContext.DECIMAL64);
		P.rintln("outstanding balance of capital is : " + outstandingCapitalBalance);
		return outstandingCapitalBalance;
	}
	
	private BigDecimal getMonthlyRepaymentCapital(Loan loan){
		BigDecimal monthlyRepaymentCapital = BigDecimal.valueOf(loan.getAmount()).
				divide(BigDecimal.valueOf(loan.getRepaymentMonths()), MathContext.DECIMAL64);
		P.rintln("monthly repayment capital is : " + monthlyRepaymentCapital);
		
		return monthlyRepaymentCapital;
	}
	
//	private BigDecimal getMonthlyDecreasedRepayment(Loan loan){
//		return getMonthlyRepaymentCapital(loan).
//				multiply(getMonthlyInterestRate(loan), MathContext.DECIMAL64);
//	}
	
	private BigDecimal getMonthlyInterestRate(Loan loan){
		BigDecimal monthlyInterestRate = BigDecimal.valueOf(loan.getAnnualInterestRate()).
				divide(BigDecimal.valueOf(12*100), MathContext.DECIMAL64);
		P.rintln("monthly rate is : " + monthlyInterestRate);
		return monthlyInterestRate;
	}
	
}
