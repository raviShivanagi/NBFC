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

@Entity
@Table(name = "transaction")
public class Transaction {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String transactionId;

	@Column(nullable = false)
	private String amount;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    private ApplicantDetails applicantDetails;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private CustLoanDetails custLoanDetails;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private java.sql.Timestamp creationDate;

	@Column(nullable = false)
	private java.sql.Date txnDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public ApplicantDetails getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(ApplicantDetails applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	public CustLoanDetails getCustLoanDetails() {
		return custLoanDetails;
	}

	public void setCustLoanDetails(CustLoanDetails custLoanDetails) {
		this.custLoanDetails = custLoanDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.sql.Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public java.sql.Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(java.sql.Date txnDate) {
		this.txnDate = txnDate;
	}


}
