package com.github.hunqi.calculator.houseloan.pojo;

public class Loan {

	private double amount;
	private double annualInterestRate;
	private int repaymentMonths;
	private int hasRepaidMonths;

	public Loan() {
	}

	public Loan(double amount, double annualInterestRate, int repaymentMonths) {
		this.amount = amount;
		this.annualInterestRate = annualInterestRate;
		this.repaymentMonths = repaymentMonths;
	}

	public Loan(double amount, double annualInterestRate, int repaymentMonths,
			int hasRepaidMonths) {
		super();
		this.amount = amount;
		this.annualInterestRate = annualInterestRate;
		this.repaymentMonths = repaymentMonths;
		this.hasRepaidMonths = hasRepaidMonths;
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

	public int getHasRepaidMonths() {
		return hasRepaidMonths;
	}

	public void setHasRepaidMonths(int hasRepaidMonths) {
		this.hasRepaidMonths = hasRepaidMonths;
	}

	@Override
	public String toString() {
		return "Loan [amount=" + amount + ", annualInterestRate="
				+ annualInterestRate + ", repaymentMonths=" + repaymentMonths
				+ ", hasRepaidMonths=" + hasRepaidMonths + "]";
	}

}
