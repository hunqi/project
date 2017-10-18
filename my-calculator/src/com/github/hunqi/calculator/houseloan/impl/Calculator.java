package com.github.hunqi.calculator.houseloan.impl;

import java.math.BigDecimal;

import com.github.hunqi.calculator.houseloan.pojo.Loan;

interface Calculator {
	BigDecimal getMonthlyPayments(Loan loan);

	BigDecimal getTotalPayments(Loan loan);
}
