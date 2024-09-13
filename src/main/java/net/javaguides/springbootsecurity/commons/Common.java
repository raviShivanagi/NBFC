package net.javaguides.springbootsecurity.commons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import net.javaguides.springbootsecurity.bean.EmiCalculatorBean;

public class Common {


	public static String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public static List<EmiCalculatorBean> calcEmiAllMonths(double principal, double rateOfInterest,
			double numberOfMonths) {
		List<EmiCalculatorBean> emiCalculatorList = new ArrayList<>();

		double rateOfInterestPerMonth = (rateOfInterest / 12) / 100;
		double emi = calcEmi(principal, rateOfInterest, numberOfMonths);
		double totalInt = Math.round((emi * numberOfMonths) - principal);
		double intPerMonth = Math.round(totalInt / numberOfMonths);

		for (double i = 1; i <= numberOfMonths; i++) {
			EmiCalculatorBean  emiCalculatorBean = new EmiCalculatorBean();
			intPerMonth = (principal * rateOfInterestPerMonth);
			principal = ((principal) - ((emi) - (intPerMonth)));
			emiCalculatorBean.setEmiNumber(" Month -> " + i);
			emiCalculatorBean.setInterestPerMonth(Math.round(intPerMonth));
			emiCalculatorBean.setPrincipalPerMonth(Math.round((emi) - intPerMonth));
			emiCalculatorBean.setBalancePrincipa(Math.round(principal));
			emiCalculatorList.add(emiCalculatorBean);
		}
		return emiCalculatorList;
	}

	public static Double calcEmi(double p, double r, double n) {
		double R = (r / 12) / 100;
		double e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));

		return e;
	}

	public static EmiCalculatorBean calcTotalEmi(double principal, double rateOfInterest, double numberOfMonths) {
		EmiCalculatorBean  emiCalculatorBean = new EmiCalculatorBean();
		double emi = calcEmi(principal, rateOfInterest, numberOfMonths);
		double totalInt = Math.round((emi * numberOfMonths) - principal);
		double totalAmt = Math.round((emi * numberOfMonths));
		emiCalculatorBean.setLoanAmount(principal);
		emiCalculatorBean.setInterestRate(rateOfInterest);
		emiCalculatorBean.setLoanTenure(numberOfMonths);
		emiCalculatorBean.setEmi(Math.round(emi));
		emiCalculatorBean.setTotalInterest(totalInt);
		emiCalculatorBean.setTotalAmount(totalAmt);
		return emiCalculatorBean;
	}

	public static java.sql.Date getTodaysDate() {
		return new java.sql.Date(new Date(System.currentTimeMillis()).getTime());
	}

    public static java.sql.Date getFirstDateOfMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new java.sql.Date(new Date(cal.getTimeInMillis()).getTime());
    }

    public static java.sql.Date getLastDateOfMonth(){
        Calendar cal = Calendar.getInstance();
        cal.getActualMaximum(Calendar.DATE);
        return new java.sql.Date(new Date(cal.getTimeInMillis()).getTime());
    }
}
