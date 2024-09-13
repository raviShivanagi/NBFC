package net.javaguides.springbootsecurity.bean;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mrunal Borge
 *
 */
public class ApplicantDetailsBean {

	private String applicantFirstName;

	private String applicantMidName;

	private String applicantLastName;

	private MultipartFile idProof;

	private String fgFirstName;

	private String fgMidName;

	private String fgLastName;

	private String sgFirstName;

	private String sgMidName;

	private String sgLastName;

	private MultipartFile fgAdhar;

	private MultipartFile fgPan;

	private MultipartFile fgCancelledCheque;

	private MultipartFile sgAdhar;

	private MultipartFile sgPan;

	private MultipartFile sgCancelledCheque;

	private String mobileNo;

	private String email;

	private String processingFee;

	private String address;

	private String loanAmount;

	private String interest;

	private String loanTenure;

	private String status;

	private String interestRate;

	public String getApplicantFirstName() {
		return applicantFirstName;
	}

	public void setApplicantFirstName(String applicantFirstName) {
		this.applicantFirstName = applicantFirstName;
	}

	public String getApplicantMidName() {
		return applicantMidName;
	}

	public void setApplicantMidName(String applicantMidName) {
		this.applicantMidName = applicantMidName;
	}

	public String getApplicantLastName() {
		return applicantLastName;
	}

	public void setApplicantLastName(String applicantLastName) {
		this.applicantLastName = applicantLastName;
	}

	public MultipartFile getIdProof() {
		return idProof;
	}

	public void setIdProof(MultipartFile idProof) {
		this.idProof = idProof;
	}

	public String getFgFirstName() {
		return fgFirstName;
	}

	public void setFgFirstName(String fgFirstName) {
		this.fgFirstName = fgFirstName;
	}

	public String getFgMidName() {
		return fgMidName;
	}

	public void setFgMidName(String fgMidName) {
		this.fgMidName = fgMidName;
	}

	public String getFgLastName() {
		return fgLastName;
	}

	public void setFgLastName(String fgLastName) {
		this.fgLastName = fgLastName;
	}

	public String getSgFirstName() {
		return sgFirstName;
	}

	public void setSgFirstName(String sgFirstName) {
		this.sgFirstName = sgFirstName;
	}

	public String getSgMidName() {
		return sgMidName;
	}

	public void setSgMidName(String sgMidName) {
		this.sgMidName = sgMidName;
	}

	public String getSgLastName() {
		return sgLastName;
	}

	public void setSgLastName(String sgLastName) {
		this.sgLastName = sgLastName;
	}

	public MultipartFile getFgAdhar() {
		return fgAdhar;
	}

	public void setFgAdhar(MultipartFile fgAdhar) {
		this.fgAdhar = fgAdhar;
	}

	public MultipartFile getFgPan() {
		return fgPan;
	}

	public void setFgPan(MultipartFile fgPan) {
		this.fgPan = fgPan;
	}

	public MultipartFile getFgCancelledCheque() {
		return fgCancelledCheque;
	}

	public void setFgCancelledCheque(MultipartFile fgCancelledCheque) {
		this.fgCancelledCheque = fgCancelledCheque;
	}

	public MultipartFile getSgAdhar() {
		return sgAdhar;
	}

	public void setSgAdhar(MultipartFile sgAdhar) {
		this.sgAdhar = sgAdhar;
	}

	public MultipartFile getSgPan() {
		return sgPan;
	}

	public void setSgPan(MultipartFile sgPan) {
		this.sgPan = sgPan;
	}

	public MultipartFile getSgCancelledCheque() {
		return sgCancelledCheque;
	}

	public void setSgCancelledCheque(MultipartFile sgCancelledCheque) {
		this.sgCancelledCheque = sgCancelledCheque;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}
