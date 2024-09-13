package net.javaguides.springbootsecurity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguides.springbootsecurity.entities.ApplicantDetails;

/**
 * @author Mrunal Borge
 *
 */
public interface ApplicantRepository extends JpaRepository<ApplicantDetails, Integer> {
	
	@Query("select a from ApplicantDetails a where a.status = 'PENDING' ")
	List<ApplicantDetails> getPendingApplicant();

	@Query("SELECT COUNT(a) FROM ApplicantDetails a ")
	Integer countById();
	
	@Query("select COUNT(a) from ApplicantDetails a where a.status = 'PENDING' ")
	Integer getPendingApplicantCount();

}
