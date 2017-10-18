package com.github.hunqi.calculator.houseloan.pojo;

public class Loan {

	private double amount;
	private double annualInterestRate;
	private int repaymentMonths;
	
	public Loan() {
	}
	
	public Loan(double amount, double annualInterestRate, int repaymentMonths) {
		this.amount = amount;
		this.annualInterestRate = annualInterestRate;
		this.repaymentMonths = repaymentMonths;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public int getRepaymentMonths() {
		return repaymentMonths;
	}

	public void setRepaymentMonths(int repaymentMonths) {
		this.repaymentMonths = repaymentMonths;
	}

}
