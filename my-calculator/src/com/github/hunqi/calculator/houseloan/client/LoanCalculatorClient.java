package com.github.hunqi.calculator.houseloan.client;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

import com.github.hunqi.calculator.houseloan.impl.CalculatorProxy;
import com.github.hunqi.calculator.houseloan.pojo.InterestRate;
import com.github.hunqi.calculator.houseloan.pojo.Loan;
import com.github.hunqi.tool.P;

public class LoanCalculatorClient {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true){
			P.rintln("请输入贷款金额：/万");
			double loanAmount = scanner.nextDouble();
			P.rintln("请输入还款期限：/年");
			int repaymentYears = scanner.nextInt();
			
			Loan loan = new Loan(loanAmount, InterestRate.COMMERCIAL, repaymentYears*12);
			CalculatorProxy calc = new CalculatorProxy();

			P.rint("您的月还款额是：" + calc.getMonthlyPayments(loan).multiply(BigDecimal.valueOf(10000), MathContext.DECIMAL32) + " 元");
			P.rint(", ");
			P.rint("您的总还款额是：" + calc.getTotalPayments(loan) + " 万元 ");
			P.rintln("\n---------------------------------------------------");
		}
		
	}
}
