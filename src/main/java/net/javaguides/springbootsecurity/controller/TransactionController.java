package net.javaguides.springbootsecurity.controller;

import java.beans.Beans;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.javaguides.springbootsecurity.bean.TransactionBean;
import net.javaguides.springbootsecurity.commons.Common;
import net.javaguides.springbootsecurity.commons.Constant;
import net.javaguides.springbootsecurity.commons.PDFGenerator;
import net.javaguides.springbootsecurity.entities.Transaction;
import net.javaguides.springbootsecurity.repositories.TransactionRepository;

@Controller
@RequestMapping("transaction")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PDFGenerator pdfGenerator;

	@Value("${table.columnNames}")
	private String columns;

	@GetMapping("/getTransaction")
	public String getApplicant(Model model, HttpSession session) {
		Common.getPrincipal();
		List<Transaction> transactionDetails = transactionRepository.findAll();
		model.addAttribute("role", "ADMIN");
		model.addAttribute("transactionDetails", transactionDetails);
		return "transaction";
	}

	@RequestMapping(value = "/getTxn", method = RequestMethod.POST)
	public String getTransaction(Model model, @Param("searchBy") String searchBy) throws IOException {
		List<Transaction> transactionDetails = null;
		if(Constant.Daily.getValue().equals(searchBy)) {
			transactionDetails = transactionRepository.getDailyTransactions(Common.getTodaysDate());
		} else {
			transactionDetails = transactionRepository.getMonthlyTransactions(Common.getFirstDateOfMonth(), Common.getLastDateOfMonth());
		}
		model.addAttribute("role", "ADMIN");
		model.addAttribute("transactionDetails", transactionDetails);
		return "transaction";
	}

	@GetMapping("/downloadfile")
	public void downloadFile(Model model,
			HttpServletResponse response) throws IOException {
		List<Transaction> transactionDetails = transactionRepository.findAll();
		List<TransactionBean> transactionBeanList = new ArrayList<TransactionBean>(); 
		for(Transaction transaction : transactionDetails) {
			TransactionBean t = new TransactionBean();
			BeanUtils.copyProperties(transaction, t);
			t.setCustomerFirstName(transaction.getApplicantDetails().getApplicantFirstName());
			t.setCustomerMiddleName(transaction.getApplicantDetails().getApplicantMidName());
			t.setCustomerLastName(transaction.getApplicantDetails().getApplicantLastName());
			t.setLoanId(transaction.getCustLoanDetails().getId());
			transactionBeanList.add(t);
			
		}
		List<String> columnNames = Arrays.asList("transactionId","amount","customerFirstName","customerMiddleName","customerLastName","loanId","txnDate");


			String fileName = pdfGenerator.generatePdfReport(transactionBeanList, columnNames,"Transaction Details", columns);
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
	

}
