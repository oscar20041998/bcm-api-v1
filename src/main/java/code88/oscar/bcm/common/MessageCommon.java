package code88.oscar.bcm.common;

import org.springframework.stereotype.Component;

@Component
public class MessageCommon {

    public final static String LINE = "--------------------------------------------------------------------------------------------------";

    // VALIDATION USER
    public final static String USER_VALID = "THIS USER IS VALID TO ACCESS";

    public final static String USER_INVALID = "THIS USER IS NOT VALID TO ACCESS";

    public final static String START_VALID_ACCOUNT = "WE STARTED TO VALID ACCOUNT";

    // STATUS LOG IN
    public final static String LOGIN_SUCCES = "LOG IN SUCCESS";

    public final static String LOGIN_FAILED = "LOG IN FAILED";

    public final static String LOGOUT_SUCCESS = "LOG OUT SUCCESS";

    public final static String NOT_HAVE_PERMISSION = "DON'T HAVE PERMISSION";
    
    public final static String DUPLICATE_LOGIN = "DUPLICATED LOG IN";

    // FOR USER
    public final static String CREATE_USER_SUCCESS = "CREATE USER SUCCESSFULLY !!! ";

    public final static String CREATE_USER_FAILED = "CREATE USER FAILED. TRY THIS LATER OR CONTACT TO YOUR ADMINISTRATOR ";

    public final static String START_CREATE_USER = "WE STARTED TO CREATE NEW USER";

    public final static String UPDATE_USER_SUCCESS = "UPDATE INFORMATION OF USER SUCCESS";

    public final static String UPDATE_USER_FAILED = "UPDATE INFORMATION OF USER IS FAILED";

    public final static String START_UPDATE_USER = "WE STARTED TO UPDATE USER";

    public final static String START_DELETE_USER = "WE STARTED TO GET USER";

    public final static String DELETE_USER_SUCCESS = "DELETE USER SUCCESS";

    public final static String DELETE_USER_FAILED = "DELETE USER IS FAILED";

    public final static String START_USER_ACCOUNT = "WE STARTED TO DELETE USER";

    public final static String START_GET_LIST_USER = "WE STARTED TO GET ALL USER";

    public final static String GET_ALL_USER_SUCCESS = "GET ALL USER SUCCESS";

    public final static String GET_ALL_USER_FAILED = "GET ALL USER IS NOT SUCCESS";

    public final static String START_GET_USER = "WE STARTED TO GET USER";

    public final static String GET_USER_SUCCESS = "GET USER SUCCESS";

    public final static String GET_USER_FAILED = "GET USER IS NOT SUCCESS";

    // FOR ACCOUNT
    public final static String CREATE_ACCOUNT_SUCCESS = "CREATE ACCOUNT FOR USER SUCCESSFULLY !!! ";

    public final static String CREATE_ACCOUNT_FAILED = "CREATE USER FAILED. TRY THIS LATER OR CONTACT TO YOUR ADMINISTRATOR ";

    public final static String START_CREATE_ACCOUNT = "WE STARTED TO CREATE NEW ACCOUNT";

    public final static String UPDATE_ACCOUNT_SUCCESS = "UPDATE INFORMATION OF ACCOUNT SUCCESS";

    public final static String UPDATE_ACCOUNT_FAILED = "UPDATE INFORMATION OF ACCOUNT IS FAILED";

    public final static String START_ACCOUNT_USER = "WE STARTED TO UPDATE ACCOUNT";

    public final static String DELETE_ACCOUNT_SUCCESS = "DELETE ACCOUNT SUCCESS";

    public final static String DELETE_ACCOUNT_FAILED = "DELETE ACCOUNT IS FAILED";

    public final static String START_DELETE_ACCOUNT = "WE STARTED TO DELETE ACCOUNT";

    public final static String START_GET_LIST_ACCOUNT = "WE STARTED TO GET LIST ACCOUNT";

    public final static String GET_LIST_ACCOUNT_SUCCESS = "GET LIST ACCOUNT SUCCESS";

    public final static String GET_LIST_ACCOUNT_FAILED = "GET LIST ACCOUNT IS FAILED";
    
    public final static String START_GET_LIST_ACCOUNT_NEARLY = "WE STARTED TO GET LIST ACCOUNT LOGIN NEARLY";

    public final static String GET_LIST_ACCOUNT_NEARLY_SUCCESS = "GET LIST ACCOUNT LOGIN NEARLY SUCCESS";

    public final static String GET_LIST_ACCOUNT_NEARLY_FAILED = "GET LIST ACCOUNT LOGIN NEARLY IS FAILED";

    public final static String START_GET_ACCOUNT_BY_ACCOUNT_ID = "WE STARTED TO GET ACCOUNT BY ACCOUNT ID";

    public final static String GET_ACCOUNT_BY_ACCOUNT_ID_SUCCESS = "GET ACCOUNT BY ACCOUNT ID SUCCESS";

    public final static String GET_ACCOUNT_BY_ACCOUNT_ID_FAILED = "GET ACCOUNT ID BY ACCOUNT ID FAILED";

    // FOR SYSTEM LOG
    public static final String START_GET_PERSONAL_LOG = "WE STATED TO GET LIST PERSONAL LOG";

    public static final String GET_PERSONAL_LOG_SUCCESS = "LIST PERSONAL LOG HAS BEEN GOTTEN SUCCESS";

    public static final String GET_PERSONAL_LOG_FAILED = "GET LIST OF PERSONAL LOG FAILED";

    public static final String START_GET_ACCOUNT_USER_LOG = "WE STATED TO GET LIST ACCOUNT USER LOG";

    public static final String GET_ACCOUNT_USER_LOG_SUCCESS = "LIST ACCOUNT USER LOG HAS BEEN GOTTEN SUCCESS";

    public static final String GET_ACCOUNT_USER_LOG_FAILED = "GET LIST OF ACCOUNT USER LOG FAILED";

    public static final String START_GET_USER_LOG = "WE STATED TO GET LIST USER LOG";

    public static final String GET_USER_LOG_SUCCESS = "LIST USER LOG HAS BEEN GOTTEN SUCCESS";

    public static final String GET_USER_LOG_FAILED = "GET LIST OF USER LOG FAILED";

    public static final String START_GET_PRODUCT_LOG = "WE STATED TO GET LIST PRODUCT LOG";

    public static final String GET_PRODUCT_LOG_SUCCESS = "LIST PRODUCT LOG HAS BEEN GOTTEN SUCCESS";

    public static final String GET_PRODUCT_LOG_FAILED = "GET PRODUCT OF SYSTEM LOG FAILED";

    // FOR PROFILE USER
    public static final String START_GET_PROFILE = "WE STATED TO GET PROFILE USER";

    public static final String GET_PROFILE_SUCCESS = "PROFILE USER HAS BEEN GOTTEN SUCCESS";

    public static final String GET_PROFILE_FAILED = "PROFILE USER HAS BEEN GOTTEN FAILED";

    // CHANGE PASSWORD
    public static final String START_CHANGE_PASSWORD = "WE STARTED TO CHANGE PASSWORD";

    public static final String CHANGE_PASSWORD_SUCCESS = "CHANGE PASSWORD SUCCESS";

    public static final String CHANGE_PASSWORD_FAILED = "CHANGE PASSWORD FAILED";

    // FOR CATEGORY
    public static final String START_GET_ALL_CATEGORY = "WE STARTED TO GET ALL CATEGORY";

    public static final String GET_ALL_CATEGORY_SUCCESS = "GET ALL CATEGORY SUCCESS";

    public static final String GET_ALL_CATEGORY_FAILED = "GET ALL CATEGORY FAILED";

    public static final String START_SAVE_CATEGORY = "WE STARTED TO SAVE CATEGORY";

    public static final String SAVE_CATEGORY_SUCCESS = "SAVE CATEGORY SUCCESS";

    public static final String SAVE_CATEGORY_FAILED = "SAVE CATEGORY FAILED";

    public static final String START_DELETE_CATEGORY = "WE STARTED TO DELETE CATEGORY";

    public static final String DELETE_CATEGORY_SUCCESS = "DELETE CATEGORY SUCCESS";

    public static final String DELETE_CATEGORY_FAILED = "DELETE CATEGORY FAILED";

    // FOR PRODUCT
    public static final String START_GET_ALL_PRODUCT = "WE STARTED TO GET ALL PRODUCT";

    public static final String GET_ALL_PRODUCT_SUCCESS = "GET ALL PRODUCT SUCCESS";

    public static final String GET_ALL_PRODUCT_FAILED = "GET ALL PROUCT FAILED";

    public static final String START_SAVE_PRODUCT = "WE STARTED TO SAVE PRODUCT";

    public static final String SAVE_PRODUCT_SUCCESS = "SAVE PRODUCT SUCCESS";

    public static final String SAVE_PRODUCT_FAILED = "SAVE PRODUCT FAILED";

    public static final String START_DELETE_PRODUCT = "WE STARTED TO DELETE PRODUCT";

    public static final String DELETE_PRODUCT_SUCCESS = "DELETE PRODUCT SUCCESS";

    public static final String DELETE_PRODUCT_FAILED = "DELETE PRODUCT FAILED";

    public static final String START_GET_PRODUCT_BY_ID = "WE STARTED TO GET PRODUCT BY ID";

    public static final String GET_PRODUCT_BY_ID_SUCCESS = "GET PRODUCT BY ID SUCCESS";

    public static final String GET_PRODUCT_BY_ID_FAILED = "GET PRODUCT BY ID FAILED";

    // FOR POSITION
    public static final String START_GET_ALL_POSITION = "WE STARTED TO GET ALL POSITION";

    public static final String GET_ALL_POSITION_SUCCESS = "GET ALL POSITION SUCCESS";

    public static final String GET_ALL_POSITION_FAILED = "GET ALL POSITION FAILED";

    // FOR ORDER PRODUCT
    public static final String START_SAVE_ORDER = "WE STARTED TO SAVE ORDER PRODUCT";

    public static final String SAVE_ORDER_SUCCESS = "SAVE ORDER PRODUCT SUCCESS";

    public static final String SAVE_ORDER_FAILED = "SAVE ORDER PRODUCT FAILED";
    
    public static final String START_DELETE_ORDER = "WE START DELTE ORDER PRODUCT";

    public static final String DELETE_ORDER_SUCCESS = "DELETE ORDER PRODUCT SUCCESS";

    public static final String DELETE_ORDER_FAILED = "DELETE ORDER PRODUCT FAILED";

    public static final String START_GET_ORDER_PRODUCT_BY_TABLE = "WE START GET ORDER PRODUCT BY TABLE";

    public static final String GET_ORDER_PRODUCT_BY_TABLE_SUCCESS = "GET ORDER PRODUCT BY TABLE SUCCESS";

    public static final String GET_ORDER_PRODUCT_BY_TABLE_FAILED = "GET ORDER PRODUCT BY TABLE FAILED";

    public static final String START_GET_ORDER_PRODUCT_PENDING = "WE START GET ORDER PRODUCT PENDING";

    public static final String GET_ORDER_PRODUCT_PENDING_SUCCESS = "GET ORDER PRODUCT PENDING SUCCESS";

    public static final String GET_ORDER_PRODUCT_PENDING_FAILED = "GET ORDER PRODUCT PENDING FAILED";
    
    public static final String START_UPDATE_ORDER_PRODUCT_PENDING = "WE START UPDATE ORDER PRODUCT PENDING";

    public static final String UPDATE_PRODUCT_PENDING_SUCCESS = "UPDATE ORDER PRODUCT PENDING SUCCESS";

    public static final String UPDATE_ORDER_PRODUCT_PENDING_FAILED = "UPDATE ORDER PRODUCT PENDING FAILED";
    

    // FOR TRANSACTION
    public static final String START_SAVE_TRANSACTION = "WE STARTED TO SAVE TRANSACTION";

    public static final String SAVE_TRANSACTION_SUCCESS = "SAVE TRANSACTION SUCCESS";

    public static final String SAVE_TRANSACTION_FAILED = "SAVE TRANSACTION FAILED";

    public static final String START_GET_TRANSACTIONS = "WE START GET TRANSACTIONS";

    public static final String GET_TRANSACTIONS_SUCCESS = "GET TRANSACTIONS SUCCESS";

    public static final String GET_TRANSACTIONS_FAILED = "GET TRANSACTIONS FAILED";

}
