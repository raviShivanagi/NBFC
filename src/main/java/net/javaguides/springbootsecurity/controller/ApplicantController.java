package net.javaguides.springbootsecurity.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.javaguides.springbootsecurity.bean.ApplicantDetailsBean;
import net.javaguides.springbootsecurity.commons.Common;
import net.javaguides.springbootsecurity.commons.Constant;
import net.javaguides.springbootsecurity.entities.ApplicantDetails;
import net.javaguides.springbootsecurity.entities.CustLoanDetails;
import net.javaguides.springbootsecurity.repositories.ApplicantRepository;
import net.javaguides.springbootsecurity.repositories.CustomerLoanRepository;

/**
 * @author Mrunal Borge
 *
 */
@Controller
@RequestMapping("applicant")
public class ApplicantController {

	@Autowired
	private ApplicantRepository applicantRepository;

	@Autowired
	private CustomerLoanRepository customerLoanRepository;

	@GetMapping("/add")
	public String getApplicant(Model model, HttpSession session) {
		Common.getPrincipal();
		model.addAttribute("role", "ADMIN");
		return "addApplicant";
	}

	@GetMapping("/getDocuments")
	public String home(Model model, HttpSession session) {
		List<ApplicantDetails> applicantDetails = applicantRepository.getPendingApplicant();
		model.addAttribute("role", "ADMIN");
		model.addAttribute("applicantDetails", applicantDetails);

		return "kyc";
	}

	@PostMapping("/save")
	public String saveApplicant(Model model, ApplicantDetailsBean applicantDetailsBean) throws IOException {
		ApplicantDetails applicantDetails = new ApplicantDetails();

		String message = null;
		BeanUtils.copyProperties(applicantDetailsBean, applicantDetails);
		// Set File
		applicantDetails.setIdProof(applicantDetailsBean.getIdProof().getBytes());
		applicantDetails.setFgAdhar(applicantDetailsBean.getFgAdhar().getBytes());
		applicantDetails.setFgPan(applicantDetailsBean.getFgPan().getBytes());
		applicantDetails.setFgCancelledCheque(applicantDetailsBean.getFgCancelledCheque().getBytes());
		applicantDetails.setSgAdhar(applicantDetailsBean.getSgAdhar().getBytes());
		applicantDetails.setSgPan(applicantDetailsBean.getSgPan().getBytes());
		applicantDetails.setSgCancelledCheque(applicantDetailsBean.getSgCancelledCheque().getBytes());

		// Set File Name
		applicantDetails.setIdProofName(applicantDetailsBean.getIdProof().getOriginalFilename());
		applicantDetails.setFgAdharName(applicantDetailsBean.getFgAdhar().getOriginalFilename());
		applicantDetails.setFgPanName(applicantDetailsBean.getFgPan().getOriginalFilename());
		applicantDetails.setFgCancelledChequeName(applicantDetailsBean.getFgCancelledCheque().getOriginalFilename());
		applicantDetails.setSgAdharName(applicantDetailsBean.getSgAdhar().getOriginalFilename());
		applicantDetails.setSgPanName(applicantDetailsBean.getSgPan().getOriginalFilename());
		applicantDetails.setSgCancelledChequeName(applicantDetailsBean.getSgCancelledCheque().getOriginalFilename());
		applicantDetails.setCreationDate(new Timestamp(System.currentTimeMillis()));
		applicantDetails.setStatus(Constant.PENDING.getValue());
		applicantDetails.setCreatedBy(Common.getPrincipal());
		ApplicantDetails applicantDetailSave = applicantRepository.saveAndFlush(applicantDetails);
		CustLoanDetails custLoanDetails = new CustLoanDetails();
		custLoanDetails.setApplicantDetails(applicantDetailSave);
		double loanAmount = Double.parseDouble(applicantDetailsBean.getLoanAmount());
		double interestRate = Double.parseDouble(applicantDetailsBean.getInterestRate());
		double loanTenure = Double.parseDouble(applicantDetailsBean.getLoanTenure());
		
		custLoanDetails.setInterest(String.format("%f",Common.calcTotalEmi(loanAmount, interestRate, loanTenure).getTotalInterest()));
		custLoanDetails.setInterestRate(applicantDetailsBean.getInterestRate());
		custLoanDetails.setLoanAmount(applicantDetailsBean.getLoanAmount());
		custLoanDetails.setLoanTenure(applicantDetailsBean.getLoanTenure());
		custLoanDetails.setProcessingFee(applicantDetailsBean.getProcessingFee());
		custLoanDetails.setStatus(Constant.PENDING.getValue());
		customerLoanRepository.saveAndFlush(custLoanDetails);

		message = "Applicant Details saved Successfully";

		model.addAttribute("role", "ADMIN");
		
		return "addApplicant";
	}

	@GetMapping("/downloadfile")
	public void downloadFile(@Param("id") Integer id, @Param("type") String type, Model model,
			HttpServletResponse response) throws IOException {
		Optional<ApplicantDetails> appliDetails = applicantRepository.findById(id);
		if (appliDetails != null) {
			ApplicantDetails applicantDetails = appliDetails.get();
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = null;
			ServletOutputStream outputStream = null;
			if (Constant.Id_Proof.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getIdProofName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getIdProof());
			} else if (Constant.Fg_Adhar.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getFgAdharName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getFgAdhar());
			} else if (Constant.Fg_Pan.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getFgPanName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getFgPan());
			} else if (Constant.Fg_Cancelled_Cheque.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getFgCancelledChequeName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getFgCancelledCheque());
			} else if (Constant.Sg_Adhar.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getSgAdharName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getSgAdhar());
			} else if (Constant.Sg_Pan.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getSgPanName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getSgPan());
			} else if (Constant.Sg_Cancelled_Cheque.getValue().equals(type)) {
				headerValue = "attachment; filename = " + applicantDetails.getSgCancelledChequeName();
				response.setHeader(headerKey, headerValue);
				outputStream = response.getOutputStream();
				outputStream.write(applicantDetails.getSgCancelledCheque());
			}
			response.setHeader(headerKey, headerValue);
			outputStream.close();
		}
	}

//	@PostMapping(value = "/checker",  params="action=approve")

	@RequestMapping(value = "/checker", method = RequestMethod.POST)
	public String checker(Model model, @Param("id") Integer id, @Param("action") String action) throws IOException {
		String message = null;
		Optional<ApplicantDetails> appliDetails = applicantRepository.findById(id);
		if (appliDetails != null) {
			ApplicantDetails applicantDetails = appliDetails.get();
			if(Constant.APPROVED.getValue().equals(action)) {
				applicantDetails.setStatus(Constant.APPROVED.getValue());
				message = "Applicant Details " + Constant.APPROVED.getValue() + " Successfully";
			} else {
				applicantDetails.setStatus(Constant.REJECT.getValue());
				message = "Applicant Details " + Constant.REJECT.getValue() + " Successfully";
			}
			ApplicantDetails applicant = applicantRepository.saveAndFlush(applicantDetails);

			List<ApplicantDetails> applicantDetailLits = applicantRepository.getPendingApplicant();
			model.addAttribute("role", "ADMIN");
			model.addAttribute("applicantDetails", applicantDetailLits);
		}
		model.addAttribute("emsg", message);
		return "kyc";
	}

	@GetMapping("/pendingLoan")
	public String getPendingLoan(Model model, HttpSession session) {
		Common.getPrincipal();
		List<CustLoanDetails> custLoanDetailList = customerLoanRepository.findAll();
		model.addAttribute("custLoanDetailList", custLoanDetailList);
		model.addAttribute("role", "ADMIN");
		return "viewPendingLoan";
	}

	@GetMapping("/appliedLoan")
	public String getAppliedLoan(Model model, HttpSession session) {
		Common.getPrincipal();
		List<CustLoanDetails> custLoanDetailList = customerLoanRepository.findAll();
		model.addAttribute("custLoanDetailList", custLoanDetailList);
		model.addAttribute("role", "ADMIN");
		return "viewAppliedLoan";
	}

}
