package net.javaguides.springbootsecurity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mrunal Borge
 *
 */
@Entity
@Table(name = "applicant_Details")
public class ApplicantDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String applicantFirstName;

	@Column(nullable = false)
	private String applicantMidName;

	@Column(nullable = false)
	private String applicantLastName;

	@Column(nullable = false)
	private String mobileNo;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String processingFee;

	@Column(nullable = false)
	private String idProofName;

	@Column(nullable = false, length = 2147483646)
	private byte[] idProof;

	@Column(nullable = false)
	private String fgFirstName;

	@Column(nullable = false)
	private String fgMidName;

	@Column(nullable = false)
	private String fgLastName;

	@Column(nullable = false)
	private String sgFirstName;

	@Column(nullable = false)
	private String sgMidName;

	@Column(nullable = false)
	private String sgLastName;

	@Column(nullable = false)
	private String fgAdharName;

	@Column(nullable = false, length = 2147483646)
	private byte[] fgAdhar;

	@Column(nullable = false)
	private String fgPanName;

	@Column(nullable = false, length = 2147483646)
	private byte[] fgPan;

	@Column(nullable = false)
	private String fgCancelledChequeName;

	@Column(nullable = false, length = 2147483646)
	private byte[] fgCancelledCheque;

	@Column(nullable = false)
	private String sgAdharName;

	@Column(nullable = false, length = 2147483646)
	private byte[] sgAdhar;

	@Column(nullable = false)
	private String sgPanName;

	@Column(nullable = false, length = 2147483646)
	private byte[] sgPan;

	@Column(nullable = false)
	private String sgCancelledChequeName;

	@Column(nullable = false, length = 2147483646)
	private byte[] sgCancelledCheque;

	@Column(nullable = false)
	private java.sql.Timestamp creationDate;

	@Column(nullable = true)
	private java.sql.Timestamp modificationDate;

	@Column(nullable = true)
	private String remark;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private String createdBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}

	public String getIdProofName() {
		return idProofName;
	}

	public void setIdProofName(String idProofName) {
		this.idProofName = idProofName;
	}

	public byte[] getIdProof() {
		return idProof;
	}

	public void setIdProof(byte[] idProof) {
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

	public String getFgAdharName() {
		return fgAdharName;
	}

	public void setFgAdharName(String fgAdharName) {
		this.fgAdharName = fgAdharName;
	}

	public byte[] getFgAdhar() {
		return fgAdhar;
	}

	public void setFgAdhar(byte[] fgAdhar) {
		this.fgAdhar = fgAdhar;
	}

	public String getFgPanName() {
		return fgPanName;
	}

	public void setFgPanName(String fgPanName) {
		this.fgPanName = fgPanName;
	}

	public byte[] getFgPan() {
		return fgPan;
	}

	public void setFgPan(byte[] fgPan) {
		this.fgPan = fgPan;
	}

	public String getFgCancelledChequeName() {
		return fgCancelledChequeName;
	}

	public void setFgCancelledChequeName(String fgCancelledChequeName) {
		this.fgCancelledChequeName = fgCancelledChequeName;
	}

	public byte[] getFgCancelledCheque() {
		return fgCancelledCheque;
	}

	public void setFgCancelledCheque(byte[] fgCancelledCheque) {
		this.fgCancelledCheque = fgCancelledCheque;
	}

	public String getSgAdharName() {
		return sgAdharName;
	}

	public void setSgAdharName(String sgAdharName) {
		this.sgAdharName = sgAdharName;
	}

	public byte[] getSgAdhar() {
		return sgAdhar;
	}

	public void setSgAdhar(byte[] sgAdhar) {
		this.sgAdhar = sgAdhar;
	}

	public String getSgPanName() {
		return sgPanName;
	}

	public void setSgPanName(String sgPanName) {
		this.sgPanName = sgPanName;
	}

	public byte[] getSgPan() {
		return sgPan;
	}

	public void setSgPan(byte[] sgPan) {
		this.sgPan = sgPan;
	}

	public String getSgCancelledChequeName() {
		return sgCancelledChequeName;
	}

	public void setSgCancelledChequeName(String sgCancelledChequeName) {
		this.sgCancelledChequeName = sgCancelledChequeName;
	}

	public byte[] getSgCancelledCheque() {
		return sgCancelledCheque;
	}

	public void setSgCancelledCheque(byte[] sgCancelledCheque) {
		this.sgCancelledCheque = sgCancelledCheque;
	}

	public java.sql.Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.sql.Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public java.sql.Timestamp getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(java.sql.Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
