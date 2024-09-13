package net.javaguides.springbootsecurity.bean;

public class EmiCalculatorBean {
	
	private String emiNumber;

	private double principalPerMonth;

	private double interestPerMonth;

	private double balancePrincipa;

	private double totalInterest;

	private double loanAmount;

	private double loanTenure;

	private double interestRate;

	private double totalAmount;

	private double emi;

	public String getEmiNumber() {
		return emiNumber;
	}

	public void setEmiNumber(String emiNumber) {
		this.emiNumber = emiNumber;
	}

	public double getPrincipalPerMonth() {
		return principalPerMonth;
	}

	public void setPrincipalPerMonth(double principalPerMonth) {
		this.principalPerMonth = principalPerMonth;
	}

	public double getInterestPerMonth() {
		return interestPerMonth;
	}

	public void setInterestPerMonth(double interestPerMonth) {
		this.interestPerMonth = interestPerMonth;
	}

	public double getBalancePrincipa() {
		return balancePrincipa;
	}

	public void setBalancePrincipa(double balancePrincipa) {
		this.balancePrincipa = balancePrincipa;
	}

	public double getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(double loanTenure) {
		this.loanTenure = loanTenure;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}
}
