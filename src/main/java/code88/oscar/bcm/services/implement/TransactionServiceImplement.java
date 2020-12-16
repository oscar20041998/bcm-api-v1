package code88.oscar.bcm.services.implement;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.TransactionModel;
import code88.oscar.bcm.repository.OrderProductRepository;
import code88.oscar.bcm.repository.PositionRepository;
import code88.oscar.bcm.repository.TransactionRepository;
import code88.oscar.bcm.request.BankInfoPaymentRequest;
import code88.oscar.bcm.request.EWalletPaymentRequest;
import code88.oscar.bcm.request.OrderDetailRequest;
import code88.oscar.bcm.request.SaveTransactionRequest;
import code88.oscar.bcm.services.OrderDetailService;
import code88.oscar.bcm.services.TransactionService;
import code88.oscar.bcm.viewObjects.TransactionVO;

/**
 * @FileName: TransactionServiceImplement.java
 * @since: 07/11/2020
 */
@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CommonMethod commonMethod;

    @Override
    public List<TransactionVO> getAllTransactions() {
	List<TransactionModel> listModel = transactionRepository.getAllTransaction();
	List<TransactionVO> listVO = mappingTransactionList(listModel);
	return listVO;
    }

    @Override
    public String saveTransaction(SaveTransactionRequest request) {
	String message = "";
	try {
	    TransactionModel transactionModel = new TransactionModel();
	    String pr = request.getTotalPrice().replace(",", "");
	    BigDecimal totalPrice = new BigDecimal(pr);
	    BankInfoPaymentRequest bankRequest = request.getBankInfoRequest();
	    EWalletPaymentRequest eWalletRequest = request.geteWalletRequest();
	    List<OrderDetailRequest> listOrder = request.getListOrder();
	    String timeNowString = commonMethod.convertDateTimeNowToString();
	    String orderId = request.getTableId().concat(".").concat(timeNowString);
	    String txnId = "No.".concat(timeNowString);

	    transactionModel.setOrderId(orderId);
	    transactionModel.setTransactionId(txnId);
	    transactionModel.setTableId(request.getTableId());
	    transactionModel.setTotalPrice(totalPrice);
	    transactionModel.setStatus(StatusCommon.RECIEVED);
	    transactionModel.setPaymentType(request.getPaymentType());

	    // Set payment info by card
	    transactionModel.setCardType(bankRequest.getCardType());
	    transactionModel.setCardNumber(bankRequest.getCardNumber());
	    transactionModel.setBankName(bankRequest.getBankName());
	    transactionModel.setExpireDate(bankRequest.getExpireDate());
	    transactionModel.setCvv(bankRequest.getCvv());

	    // Set payment info by electronic wallet
	    transactionModel.setTransactionCode(eWalletRequest.getTransactionCode());
	    transactionModel.setProviderName(eWalletRequest.getProviderName());

	    transactionModel.setCreateBy(request.getCreateBy());
	    transactionModel.setCreateDate(commonMethod.getDateTimeNow());
	    transactionRepository.save(transactionModel);
	    orderProductRepository.deleteOrderProductByTableId(request.getTableId());

	    // Save order detail
	    orderDetailService.saveOrderDetail(listOrder, request.getTableId(), orderId);

	    message = StatusCommon.SUCCESS;
	    positionRepository.closeTableById(request.getTableId());

	} catch (Exception ex) {
	    System.err.print(ex.getMessage());
	    message = StatusCommon.FAILED;
	}
	return message;
    }

    @Override
    public List<TransactionVO> getTransactionsToday() {
	List<TransactionModel> listModel = transactionRepository.getAllTransactionToday();
	List<TransactionVO> listVO = mappingTransactionList(listModel);
	return listVO;
    }

    @Override
    public List<TransactionVO> getTransactionsYesterday() {
	List<TransactionModel> listModel = transactionRepository.getAllTransactionYesterday();
	List<TransactionVO> listVO = mappingTransactionList(listModel);
	return listVO;
    }

    /**
     * @Function: mappingTransactionList(...)
     * @param: List<TransactionModel> - List
     */
    List<TransactionVO> mappingTransactionList(List<TransactionModel> listModel) {
	List<TransactionVO> listVO = new ArrayList<>();
	for (TransactionModel model : listModel) {
	    TransactionVO vo = new TransactionVO();
	    vo.setTransactionId(model.getTransactionId());
	    vo.setOrderId(model.getOrderId());
	    vo.setTableId(model.getTableId());
	    vo.setTotalPrice(commonMethod.convertCurrencyToString(model.getTotalPrice()));
	    vo.setStatus(model.getStatus());
	    vo.setPaymentType(model.getPaymentType());
	    vo.setCardType(model.getCardType());
	    if (!model.getCardNumber().isEmpty()) {
		vo.setCardNumber(commonMethod.maskCardNumber(model.getCardNumber()));
	    }
	    vo.setBankName(model.getBankName());
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

    @Override
    public String updateStatusTransaction(Date date) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<TransactionVO> getTransactionsByDate(Date date) {
	List<TransactionModel> listModel = transactionRepository.getAllTransactionByDate(date);
	List<TransactionVO> listVO = mappingTransactionList(listModel);
	return listVO;
    }
}
