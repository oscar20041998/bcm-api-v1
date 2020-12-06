package code88.oscar.bcm.services;

import java.sql.Date;
import java.util.List;

import code88.oscar.bcm.request.SaveTransactionRequest;
import code88.oscar.bcm.viewObjects.TransactionVO;

/**
 * @FileName: TransactionService.java
 * @since: 07/11/2020
 * */
public interface TransactionService {

    List<TransactionVO> getAllTransactions();
        
    List<TransactionVO> getTransactionsToday();

    List<TransactionVO> getTransactionsYesterday();
    
    List<TransactionVO> getTransactionsByDate(Date date);

    String saveTransaction(SaveTransactionRequest request);
    
    String updateStatusTransaction(Date date);
}
