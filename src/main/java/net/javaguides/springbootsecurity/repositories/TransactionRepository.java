package net.javaguides.springbootsecurity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.javaguides.springbootsecurity.entities.Transaction;

/**
 * @author Mrunal Borge
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("select t from Transaction t where t.txnDate= :txnDate")
	List<Transaction> getDailyTransactions(@Param("txnDate") java.sql.Date txnDate);

	@Query("select t from Transaction t where t.txnDate between :from and :to")
	List<Transaction> getMonthlyTransactions(@Param("from") java.sql.Date txnDate, @Param("to") java.sql.Date to);
	
	@Query("select COUNT(a) from Transaction a ")
	double getTotalTxnCount();
	
	@Query("select COUNT(a) from Transaction a where a.status = 'Successful'")
	double getTxnSuccessCount();
	
	@Query("select COUNT(a) from Transaction a where a.status = 'Failed'")
	double getTxnFailedCount();
	
	@Query("select COUNT(a) from Transaction a where a.status = 'Pending'")
	double getTxnPendingCount();

}
