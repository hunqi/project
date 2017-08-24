package com.raysun.spring.portal;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raysun.spring.config.ReloadableProperties;

@Controller
@RequestMapping("/loan")
public class LoanCalculator {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private ReloadableProperties properties;

	@RequestMapping("/calculate")
	public String calculate(HttpServletRequest request, Model model) {
		model.addAttribute("totoalPayment", calcuateTotoalPayment(request));

		return "loan-calculator";
	}

	private BigDecimal calcuateTotoalPayment(HttpServletRequest request) {
		double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
		int loanTerms = Integer.parseInt(request.getParameter("loanTerms"));
		double loanRate = Double.parseDouble(properties
				.getString("biz.loan.rate"));

		logger.info("loanAmount is {}, loanTerms is {}, loanRate is {}.",
				loanAmount, loanTerms, loanRate);
		
		return BigDecimal.valueOf(1 + loanRate/100.00).pow(loanTerms, MathContext.DECIMAL32).multiply(BigDecimal.valueOf(loanAmount));
	}
	
}
