package net.javaguides.springbootsecurity.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Mrunal Borge
 *
 */
@Entity
@Table(name = "cust_loan_details")
public class CustLoanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    private ApplicantDetails applicantDetails;

	@Column(nullable = false)
	private String loanAmount;

	@Column(nullable = false)
	private String interest;

	@Column(nullable = false)
	private String loanTenure;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private String interestRate;

	@Column(nullable = false)
	private String processingFee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ApplicantDetails getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(ApplicantDetails applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}


}
