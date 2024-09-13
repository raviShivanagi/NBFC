package net.javaguides.springbootsecurity.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springbootsecurity.bean.CustLoanDetailsBean;
import net.javaguides.springbootsecurity.bean.TransactionBean;
import net.javaguides.springbootsecurity.commons.PDFGenerator;
import net.javaguides.springbootsecurity.entities.CustLoanDetails;
import net.javaguides.springbootsecurity.entities.Message;
import net.javaguides.springbootsecurity.entities.Transaction;
import net.javaguides.springbootsecurity.repositories.ApplicantRepository;
import net.javaguides.springbootsecurity.repositories.CustomerLoanRepository;
import net.javaguides.springbootsecurity.repositories.MessageRepository;
import net.javaguides.springbootsecurity.repositories.TransactionRepository;

/**
 * @author Mrunal Borge
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Autowired
	private CustomerLoanRepository customerLoanRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PDFGenerator pdfGenerator;

	@Value("${table.customerLoanReportColumns}")
	private String columns;

	 private static final DecimalFormat df = new DecimalFormat("0");
	@GetMapping("/home")
	public String home(Model model, HttpSession session)
	{
		Integer totalCustCount = applicantRepository.countById();
		Integer profit = customerLoanRepository.getProfit();
		Integer pendingApprovalCount = applicantRepository.getPendingApplicantCount();
		double totalTxnCount = transactionRepository.getTotalTxnCount();
		double successTxnCount = transactionRepository.getTxnSuccessCount();
		double failedTxnCount = transactionRepository.getTxnFailedCount();
		double pendingTxnCount = transactionRepository.getTxnPendingCount();
		model.addAttribute("totalCustCount", totalCustCount);
		double successPercentage = getPercentage(successTxnCount, totalTxnCount);
		double failurePercentage = getPercentage(failedTxnCount, totalTxnCount);
		double pendingPercentage = getPercentage(pendingTxnCount, totalTxnCount);
		model.addAttribute("pendingApprovalCount", pendingApprovalCount);
		model.addAttribute("profit", profit);
		model.addAttribute("totalTxnCount", totalTxnCount);
		model.addAttribute("successPercentage", df.format(successPercentage));
		model.addAttribute("failurePercentage", df.format(failurePercentage));
		model.addAttribute("msgs", messageRepository.findAll());
		model.addAttribute("role", "ADMIN");;
		
		return "userhome";
	}
	
	@PostMapping("/messages")
	public String saveMessage(Message message)
	{
		messageRepository.save(message);
		return "redirect:/home";
	}

	@GetMapping("/downloadfile")
	public void downloadFile(Model model,
			HttpServletResponse response) throws IOException {
		List<CustLoanDetails> custLoanDetails = customerLoanRepository.getCustomerLoanDetails();
		List<CustLoanDetailsBean> custLoanDetailsBeanList = new ArrayList<CustLoanDetailsBean>(); 
		for(CustLoanDetails custLoanDetail : custLoanDetails) {
			CustLoanDetailsBean c = new CustLoanDetailsBean();
			BeanUtils.copyProperties(custLoanDetail, c);
			c.setApplicantFirstName(custLoanDetail.getApplicantDetails().getApplicantFirstName());
			c.setApplicantMidName(custLoanDetail.getApplicantDetails().getApplicantMidName());
			c.setApplicantLastName(custLoanDetail.getApplicantDetails().getApplicantLastName());
			custLoanDetailsBeanList.add(c);
			
		}

		List<String> columnNames = Arrays.asList("id","applicantFirstName","applicantMidName","applicantLastName","loanAmount","interest","loanTenure","interestRate","processingFee");

			String fileName = pdfGenerator.generatePdfReport(custLoanDetailsBeanList, columnNames,"Customer Loan Details", columns);
			Path path = Paths.get(fileName);
		    byte[] data = Files.readAllBytes(path);
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = null;
			ServletOutputStream outputStream = null;
			headerValue = "attachment; filename = " + path.getFileName();
			response.setHeader(headerKey, headerValue);
			outputStream = response.getOutputStream();
			//need to pass byte array
			outputStream.write(data);
			response.setHeader(headerKey, headerValue);
			outputStream.close();
		}
	
	double getPercentage (double obtainValue, double totalValue) {
		double hundred = 100.0;
		return (obtainValue / totalValue) * hundred;
	} 
}
