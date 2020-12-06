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
import code88.oscar.bcm.model.CategoryModel;
import code88.oscar.bcm.request.SaveCategoryRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category/api")
public class CategoryController {

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private CommonMethod commonMethod;

    @RequestMapping(value = "/get-categories", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryModel>> getListCategory() {
	List<CategoryModel> listCategory = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ALL_CATEGORY);
	try {
	    listCategory = categoryService.getListCategory();
	    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_CATEGORY_SUCCESS);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<List<CategoryModel>>(listCategory, HttpStatus.OK);
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ALL_CATEGORY_FAILED);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	}
	return new ResponseEntity<List<CategoryModel>>(listCategory, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/save-category/{accountIdValid}", method = RequestMethod.POST)
    public ResponseEntity<CategoryModel> saveCategory(@RequestBody SaveCategoryRequest request,
	    @PathVariable("accountIdValid") String accountIdValid) {
	CategoryModel model = new CategoryModel();
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.SAVE_CATEGORY_SUCCESS);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		model = categoryService.saveCategory(request);
		commonMethod.insertSystemLog(userName, ActionCommon.ADD_CATEGORY + "/ " + ActionCommon.UPDATE_CATEGORY
			+ " " + request.getCategoryName(), StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_CATEGORY_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<CategoryModel>(model, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.ADD_CATEGORY + "/ " + ActionCommon.UPDATE_CATEGORY
			+ " " + request.getCategoryName(), StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_CATEGORY_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<CategoryModel>(model, HttpStatus.UNAUTHORIZED);
	    }

	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.ADD_CATEGORY + "/ " + ActionCommon.UPDATE_CATEGORY + " "
		    + request.getCategoryName() + " " + ex.getMessage(), StatusCommon.FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.SAVE_CATEGORY_FAILED);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	}
	return new ResponseEntity<CategoryModel>(model, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/delete-category/{categoryId}/{accountIdValid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") String categoryId,
	    @PathVariable("accountIdValid") String accountIdValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ALL_CATEGORY);
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		categoryService.deleteCategoryById(categoryId);
		commonMethod.insertSystemLog(userName, ActionCommon.DELETE_CATEGORY + " " + categoryId,
			StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_CATEGORY_SUCCESS);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DELETE_ACCOUNT_SUCCESS, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.DELETE_CATEGORY + " " + categoryId,
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_CATEGORY_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DELETE_CATEGORY_FAILED, HttpStatus.UNAUTHORIZED);
	    }

	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.DELETE_CATEGORY + " " + categoryId,
			StatusCommon.FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.DELETE_CATEGORY_FAILED);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(MessageCommon.DELETE_CATEGORY_FAILED, HttpStatus.OK);
    }
}
