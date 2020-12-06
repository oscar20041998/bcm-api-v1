package code88.oscar.bcm.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.TransactionModel;

/**
 * @FileName: TransactionRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, String>{

    public static final String sql_getAllTransaction = ""
    	+ " SELECT *"
    	+ " FROM transaction "
    	+ " ORDER BY create_date DESC";
    
    public static final String sql_getALlTransactionsToday = ""
	    	+ " SELECT *"
	    	+ " FROM transaction "
	    	+ " WHERE "
	    	+ " 	DATE(create_date) = CURDATE() "
	    	+ " ORDER BY create_date DESC ";
    
    public static final String sql_getALlTransactionsYesterday = ""
    	+ " SELECT *"
    	+ " FROM transaction "
    	+ " WHERE "
    	+ " 	create_date BETWEEN SUBDATE(CURDATE(),1) AND DATE(NOW()) "
    	+ " ORDER BY create_date DESC ";
    
    public static final String sql_getALlTransactionsByDate = ""
	    	+ " SELECT *"
	    	+ " FROM transaction"
	    	+ " WHERE "
	    	+ " 	DATE(create_date) = DATE(:pDate) "
	    	+ " ORDER BY create_date DESC ";
    
    @Query(value = sql_getAllTransaction, nativeQuery = true)
    List<TransactionModel> getAllTransaction();
    
    @Query(value = sql_getALlTransactionsToday, nativeQuery = true)
    List<TransactionModel> getAllTransactionToday();
    
    @Query(value = sql_getALlTransactionsYesterday, nativeQuery = true)
    List<TransactionModel> getAllTransactionYesterday();
    
    @Query(value = sql_getALlTransactionsByDate, nativeQuery = true)
    List<TransactionModel> getAllTransactionByDate(@Param("pDate") Date date);
    
}
