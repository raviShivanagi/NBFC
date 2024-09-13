package net.javaguides.springbootsecurity.commons;

public class EmailTemplate {
	
	public static String loanApproveTemplate = "Dear %s,\r\n" + 
			"\r\n" + 
			"Your %s Rs. loan has been approved\r\n" + 
			"\r\n" + 
			"Regards,\r\n" + 
			"NBFC";
	public static String loanApproveSubject = "Loan Disbursement";

}
