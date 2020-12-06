package code88.oscar.bcm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code88.oscar.bcm.common.ActionCommon;
import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.ProductModel;
import code88.oscar.bcm.request.SaveProductRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.ProductService;
import code88.oscar.bcm.viewObjects.ProductVO;

/**
 * @FileName: ProductController.java
 * @since: 25/10/2020
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product/api")
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private CommonMethod commonMethod;

    @RequestMapping(value = "/get-all-products/{accountIdValid}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductVO>> getAllProducts(@PathVariable("accountIdValid") String accountIdValid) {
	List<ProductVO> listProduct = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ALL_PRODUCT);
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    boolean isStaff = accountUserService.isStaffRole(accountIdValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		listProduct = productService.getListProduct();
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_PRODUCTS, StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_PRODUCTS, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.VISIT_PRODUCTS, StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/save-product/{accountIdValid}", method = RequestMethod.POST)
    public ResponseEntity<String> saveProduct(@PathVariable("accountIdValid") String accountIdValid,
	    @RequestBody SaveProductRequest request) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_SAVE_PRODUCT);
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		productService.saveProduct(request);
		commonMethod.insertSystemLog(userName,
			ActionCommon.ADD_PRODUCT + "/ " + ActionCommon.UPDATE_PRODUCT + " " + request.getProductId(),
			StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_PRODUCT_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.SAVE_PRODUCT_SUCCESS, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName,
			ActionCommon.ADD_PRODUCT + "/ " + ActionCommon.UPDATE_PRODUCT + " " + request.getProductId(),
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_PRODUCT_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.NOT_HAVE_PERMISSION, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName,
		    ActionCommon.ADD_PRODUCT + "/ " + ActionCommon.UPDATE_PRODUCT + " " + request.getProductId(),
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.SAVE_PRODUCT_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/delete-product/{productId}/{accountIdValid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable("accountIdValid") String accountIdValid,
	    @PathVariable("productId") String productId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_DELETE_PRODUCT);
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    if (isAdmin == true) {
		productService.delteProductById(productId);
		commonMethod.insertSystemLog(userName, ActionCommon.DELETE_PRODUCT + productId, StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.DELETE_PRODUCT_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DELETE_PRODUCT_SUCCESS, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.DELETE_PRODUCT + productId, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.DELETE_PRODUCT_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.NOT_HAVE_PERMISSION, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.DELETE_PRODUCT + productId, StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.DELETE_PRODUCT_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/get-products-by-category/{accountIdValid}/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductVO>> getProductByCategory(@PathVariable("accountIdValid") String accountIdValid,
	    @PathVariable("categoryId") String categoryId) {
	List<ProductVO> listProduct = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ALL_PRODUCT);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    boolean isStaff = accountUserService.isStaffRole(accountIdValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		listProduct = productService.getProductByCategoryId(categoryId);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/get-products-by-name/{accountIdValid}/{productName}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductVO>> getProductByName(@PathVariable("accountIdValid") String accountIdValid,
	    @PathVariable("productName") String productName) {
	List<ProductVO> listProduct = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ALL_PRODUCT);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    boolean isStaff = accountUserService.isStaffRole(accountIdValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		listProduct = productService.getProductByProductName(productName);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_PRODUCT_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<List<ProductVO>>(listProduct, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/get-product-by-id/{productId}/{accountIdValid}", method = RequestMethod.GET)
    public ResponseEntity<ProductModel> getProductById(@PathVariable("accountIdValid") String accountIdValid,
	    @PathVariable("productId") String productId) {
	ProductModel model = new ProductModel();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_PRODUCT_BY_ID);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		model = productService.getProductById(productId);
		LOGGER.log(Level.INFO, MessageCommon.GET_PRODUCT_BY_ID_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<ProductModel>(model, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_PRODUCT_BY_ID_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<ProductModel>(model, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.DELETE_PRODUCT_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<ProductModel>(model, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
