package net.javaguides.springbootsecurity.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.javaguides.springbootsecurity.bean.EmiCalculatorBean;
import net.javaguides.springbootsecurity.commons.Common;
@Controller
@RequestMapping("emi")
public class EmiCalculatorController {

	 private static final DecimalFormat df = new DecimalFormat("0.00");

	@GetMapping("/getEmiCalculator")
	public String getApplicant(Model model, HttpSession session)
	{
		EmiCalculatorBean emiCalculatorBean = new EmiCalculatorBean();
		model.addAttribute("emiCalculatorBean", emiCalculatorBean);
		model.addAttribute("isCreate", "true");
		model.addAttribute("role", "ADMIN");

		return "emiCalculator";
	}



	@RequestMapping(value = "/calculateEmi", method = RequestMethod.POST)
	public String calculateEmi(Model model, @Param("loanAmount") String loanAmount, @Param("interestRate") String interestRate, @Param("loanTenure") String loanTenure ) throws IOException {
		String message = null;
		double principal, rate, time;
        principal = Double.parseDouble(loanAmount);
        rate = Double.parseDouble(interestRate);
        time = Double.parseDouble(loanTenure);
        time=time*12; /*one month period*/
        //time passed in months
        EmiCalculatorBean emiCalculatorBean = Common.calcTotalEmi(principal, rate, time);
        emiCalculatorBean.setLoanTenure(emiCalculatorBean.getLoanTenure() / 12);
        List<EmiCalculatorBean> emiList = Common.calcEmiAllMonths(principal, rate, time);
		model.addAttribute("emiList", emiList);
		model.addAttribute("emiCalculatorBean", emiCalculatorBean);
		model.addAttribute("isCreate", "false");
		model.addAttribute("role", "ADMIN");
		return "emiCalculator";
	}

}
