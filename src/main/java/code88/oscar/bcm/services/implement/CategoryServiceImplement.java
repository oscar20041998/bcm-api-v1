package code88.oscar.bcm.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.CategoryModel;
import code88.oscar.bcm.repository.CategoryRepository;
import code88.oscar.bcm.request.SaveCategoryRequest;
import code88.oscar.bcm.services.CategoryService;

/**
 * @FileName: CategoryServiceImplement.java
 * @since: 24/10/2020
 */
@Service
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommonMethod commonMethod;

    @Override
    public CategoryModel saveCategory(SaveCategoryRequest request) {
	CategoryModel model = mappingModel(request);
	return categoryRepository.save(model);
    }

    @Override
    public List<CategoryModel> getListCategory() {
	return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(String categoryId) {
	categoryRepository.deleteById(categoryId);
    }

    /**
     * @Function: mappingModel(...)
     * @param: SaveCategoryRequest - body
     * */
    CategoryModel mappingModel(SaveCategoryRequest request) {
	CategoryModel model = new CategoryModel();
	model.setIdCategory(request.getCategoryId() == null || request.getCategoryId().isEmpty()
		? "CTG" + commonMethod.convertDateTimeNowToString()
		: request.getCategoryId());
	model.setCategoryName(request.getCategoryName());
	return model;
    }
}
