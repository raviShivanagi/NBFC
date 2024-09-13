package net.javaguides.springbootsecurity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.javaguides.springbootsecurity.entities.ApplicantDetails;
import net.javaguides.springbootsecurity.entities.CustLoanDetails;

/**
 * @author Mrunal Borge
 *
 */
public interface CustomerLoanRepository extends JpaRepository<CustLoanDetails, Integer> {

	@Query("select a from CustLoanDetails a where a.applicantDetails.id =:applicantId ")
	CustLoanDetails getCustLoanDetailsByApplicantId(@Param("applicantId") Integer applicantId);
	
	@Query("select a from CustLoanDetails a where a.status = 'PENDING' and a.applicantDetails.status = 'APPROVED' ")
	List<CustLoanDetails> getPendinCustomer();

	@Query("SELECT sum(a.interest) FROM CustLoanDetails a ")
	Integer getProfit();
	
	@Query("select a from CustLoanDetails a where a.status = 'APPROVED'")
	List<CustLoanDetails> getCustomerLoanDetails();
	

}
