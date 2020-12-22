package code88.oscar.bcm.services.implement;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.OrderProductModel;
import code88.oscar.bcm.model.TransactionModel;
import code88.oscar.bcm.repository.OrderProductRepository;
import code88.oscar.bcm.repository.PositionRepository;
import code88.oscar.bcm.repository.TransactionRepository;
import code88.oscar.bcm.request.BankInfoPaymentRequest;
import code88.oscar.bcm.request.EWalletPaymentRequest;
import code88.oscar.bcm.request.OrderDetailRequest;
import code88.oscar.bcm.request.SaveTransactionRequest;
import code88.oscar.bcm.request.TransactionDetailRequest;
import code88.oscar.bcm.services.OrderDetailService;
import code88.oscar.bcm.services.TransactionService;
import code88.oscar.bcm.services.UserService;
import code88.oscar.bcm.viewObjects.TransactionDetailVO;
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

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;

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

	    transactionModel.setEmailCustomer(request.getCustomerEmail());
	    transactionModel.setCreateBy(request.getCreateBy());
	    transactionModel.setCreateDate(commonMethod.getDateTimeNow());
	    transactionRepository.save(transactionModel);

	    // Save order detail
	    if (request.getCustomerEmail() != null && !request.getCustomerEmail().isEmpty()) {
		sendEmail(request, txnId);
	    }
	    orderDetailService.saveOrderDetail(listOrder, request.getTableId(), orderId, request.getCreateBy());
	    positionRepository.closeTableById(request.getTableId(), request.getCreateBy());
	    orderProductRepository.deleteOrderProductByTableId(request.getTableId());

	    message = StatusCommon.SUCCESS;

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

    @Override
    public TransactionDetailVO getTransactionDetail(TransactionDetailRequest request) {
	TransactionModel model = transactionRepository.getTransactionDetail(request.getTransactionId(),
		request.getOrderId(), request.getTableId());
	TransactionDetailVO vo = mappingTransactionDetail(model);
	return vo;
    }

    TransactionDetailVO mappingTransactionDetail(TransactionModel model) {
	TransactionDetailVO vo = new TransactionDetailVO();
	vo.setTransactionId(model.getTransactionId());
	vo.setOrderId(model.getOrderId());
	vo.setTableId(model.getTableId());
	vo.setTotalPrice(commonMethod.convertCurrencyToString(model.getTotalPrice()));
	vo.setStatus(model.getStatus());
	vo.setPaymentType(model.getPaymentType());
	vo.setBankName(
		model.getBankName() != null && !model.getBankName().isEmpty() ? model.getBankName() : "(Not apply)");
	vo.setCardNumber(model.getCardNumber() != null && !model.getCardNumber().isEmpty()
		? commonMethod.maskCardNumber(model.getCardNumber())
		: "(Not apply)");
	vo.setCardType(
		model.getCardType() != null && !model.getCardType().isEmpty() ? model.getCardType() : "(Not apply)");
	vo.setExpireDate(model.getExpireDate() != null && !model.getExpireDate().isEmpty() ? model.getExpireDate()
		: "(Not apply)");
	vo.setCvv(model.getCvv() != null && !model.getCvv().isEmpty() ? model.getCvv() : "(Not apply)");
	vo.setProviderName(
		model.getProviderName() != null && !model.getProviderName().isEmpty() ? model.getProviderName()
			: "(Not apply)");
	vo.setTransactionCode(
		model.getTransactionCode() != null && !model.getTransactionCode().isEmpty() ? model.getTransactionCode()
			: "(Not apply)");
	vo.setEmailCustomer(
		model.getEmailCustomer() != null && !model.getEmailCustomer().isEmpty() ? model.getEmailCustomer()
			: "(Not provide)");
	vo.setCreateBy(model.getCreateBy());
	vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	return vo;
    }

    void sendEmail(SaveTransactionRequest request, String transactionId) {
	try {
	    String subjectMail = "ELECTRONIC INVOICE FROM COFFEE SHOP - " + transactionId;
	    String sendFrom = userService.getEmailByUserId(request.getUserId());
	    List<OrderProductModel> listOrder = orderProductRepository.getListOrderByTable(request.getTableId());
	    SimpleMailMessage message = new SimpleMailMessage();
	    String content = setContentEmail(listOrder, request);
	    message.setFrom(sendFrom);
	    message.setTo(request.getCustomerEmail());
	    message.setSubject(subjectMail);
	    message.setSentDate(new Date(8));
	    message.setText(content);
	    emailSender.send(message);
	} catch (Exception ex) {
	    System.err.print(ex.getMessage());
	}
    }

    String setContentEmail(List<OrderProductModel> listOrder, SaveTransactionRequest request) {
	String content = "";
	BankInfoPaymentRequest bankRequest = request.getBankInfoRequest();
	EWalletPaymentRequest eWalletRequest = request.geteWalletRequest();

	String paymentType = request.getPaymentType();

	String listOrderString = "";
	for (OrderProductModel order : listOrder) {
	    listOrderString += listOrderString.concat("\t").concat(order.getProductName() + " x " + order.getQuantity()
		    + " : " + commonMethod.convertCurrencyToString(order.getPrice())).concat("\n");
	}
	// Set info for pamyent by card
	String bankName = bankRequest.getBankName() != null && !bankRequest.getBankName().isEmpty()
		? bankRequest.getBankName()
		: "(Not apply)";
	String cardNumber = bankRequest.getCardNumber() != null && !bankRequest.getCardNumber().isEmpty()
		? commonMethod.maskCardNumber(bankRequest.getCardNumber())
		: "(Not apply)";
	String cardType = bankRequest.getCardType() != null && !bankRequest.getCardType().isEmpty()
		? bankRequest.getCardType()
		: "(Not apply)";
	String ownerName = bankRequest.getCardOwnerName() != null && bankRequest.getCardOwnerName() != ""
		? bankRequest.getCardOwnerName()
		: "(Not apply)";
	String expireDate = bankRequest.getExpireDate() != null && bankRequest.getExpireDate() != ""
		? bankRequest.getExpireDate()
		: "(Not apply)";
	String cvv = bankRequest.getCvv() != null && bankRequest.getCvv() != "" ? bankRequest.getCvv() : "(Not apply)";

	// Set info for payment product by electronic wallet
	String provider = eWalletRequest.getProviderName() != null && eWalletRequest.getProviderName() != ""
		? eWalletRequest.getProviderName()
		: "(Not apply)";
	String transactionCode = eWalletRequest.getTransactionCode() != null
		&& eWalletRequest.getTransactionCode() != "" ? eWalletRequest.getTransactionCode() : "(Not apply)";
	String totalPrice = request.getTotalPrice() != null && request.getTotalPrice() != "" ? request.getTotalPrice()
		: "0";
	content += content
		.concat(MessageCommon.EMAIL_ORDER_DETAIL)
		.concat("\n")
		.concat(MessageCommon.LINE_EMAIL)
		.concat("\n").concat(listOrderString)
		.concat(MessageCommon.LINE_EMAIL)
		.concat("\n")
		.concat(MessageCommon.PAYMENT_TYPE + paymentType)
		.concat("\n")
		.concat(MessageCommon.LINE_EMAIL)
		.concat("\n")
		.concat(MessageCommon.BANK_NAME + bankName)
		.concat("\n")
		.concat(MessageCommon.CAR_NUMBER + cardNumber)
		.concat("\n")
		.concat(MessageCommon.CARD_TYPE + cardType)
		.concat("\n")
		.concat(MessageCommon.CARD_OWNER_NAME + ownerName)
		.concat("\n")
		.concat(MessageCommon.EXPRIE_DATE + expireDate)
		.concat("\n")
		.concat(MessageCommon.CVV + cvv)
		.concat("\n")
		.concat(MessageCommon.LINE_EMAIL)
		.concat("\n")
		.concat(MessageCommon.ELECTRONIC_WALLET + provider)
		.concat("\n")
		.concat(MessageCommon.TRANSACTION_CODE + transactionCode)
		.concat("\n")
		.concat(MessageCommon.LINE_EMAIL)
		.concat("\n")
		.concat(MessageCommon.TOTAL_PRICE + totalPrice);
	return content;
    }

    @Override
    public List<TransactionVO> searchTransactionById(String transactionId) {
	List<TransactionModel> listModel = transactionRepository.searchTransactionById(transactionId);
	List<TransactionVO> listVO = mappingTransactionList(listModel);
	return listVO;
    }
}
