package com.github.hunqi.calculator.investment;

import java.math.BigDecimal;
import java.math.MathContext;

public class InvestmentCalculator {

	private static class Investment {
		int years;
		double rate;
		
		public Investment(int years, double rate) {
			this.years = years;
			this.rate = rate;
		}		
	}

	BigDecimal calculate(Investment investment) {
		BigDecimal base = BigDecimal.valueOf(1 + investment.rate);
		return base.pow(investment.years, MathContext.DECIMAL64);
	}

	public static void main(String[] args) {
		// 投资n年，每年投资1w，年利率8%， 计算n年后 "本金 + 收益" 的总额
		int years = 10;
		double rate = 0.08;
		
		InvestmentCalculator calculator1 = new InvestmentCalculator();		
		BigDecimal totalIncome = BigDecimal.ZERO;
		
		for(int i=years; i>0; i--){
			BigDecimal annualIncome = calculator1.calculate(new Investment(i, rate));
			System.out.println(years + " 年后， 第 "+ (years-i + 1) +" 年投资的1w的收益=" +annualIncome);
			totalIncome = totalIncome.add(annualIncome);
		}
		
		System.out.println("年投资1万，年利率8%，"+ years +"年后 本金 + 收益 = " + totalIncome);
	}

}
