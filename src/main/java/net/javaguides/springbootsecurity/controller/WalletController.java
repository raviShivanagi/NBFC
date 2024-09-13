package net.javaguides.springbootsecurity.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.javaguides.springbootsecurity.commons.Constant;
import net.javaguides.springbootsecurity.commons.EmailTemplate;
import net.javaguides.springbootsecurity.commons.EmailUtil;
import net.javaguides.springbootsecurity.entities.CustLoanDetails;
import net.javaguides.springbootsecurity.repositories.CustomerLoanRepository;

/**
 * @author Mrunal Borge
 *
 */
@Controller
@RequestMapping("wallet")
public class WalletController {

	@Value("${emailFrom}")
	private String emailFrom;

	@Value("${emailPassword}")
	private String emailpassword;

	@Autowired
	private CustomerLoanRepository customerLoanRepository;

	@GetMapping("/getWallet")
	public String getCustomer(Model model, HttpSession session) {
		List<CustLoanDetails> CustLoanDetails = customerLoanRepository.getPendinCustomer();
		model.addAttribute("role", "ADMIN");
		model.addAttribute("custLoanDetails", CustLoanDetails);
		return "wallet";
	}

	@RequestMapping(value = "/walletFunds", method = RequestMethod.POST)
	public String checker(Model model, @Param("id") Integer id) throws IOException {
		String message = null;
		Optional<CustLoanDetails> custLoanDetails = customerLoanRepository.findById(id);
		if (custLoanDetails != null) {
			CustLoanDetails custLoanDetail = custLoanDetails.get();
			custLoanDetail.setStatus(Constant.APPROVED.getValue());
				message = "Fund Transfered Successfully";
				customerLoanRepository.saveAndFlush(custLoanDetail);
				String emailBody = String.format(EmailTemplate.loanApproveTemplate, custLoanDetail.getApplicantDetails().getApplicantFirstName(), custLoanDetail.getLoanAmount());
				EmailUtil.sendEmail(custLoanDetail.getApplicantDetails().getEmail(), EmailTemplate.loanApproveSubject, emailBody, emailFrom, emailpassword);
		}
		List<CustLoanDetails> custLoanDetailList = customerLoanRepository.getPendinCustomer();
		model.addAttribute("role", "ADMIN");
		model.addAttribute("custLoanDetails", custLoanDetailList);
		model.addAttribute("emsg", message);
		return "wallet";
	}

}
