package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryServiceInt {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public ProductCategory addProductCategory(ProductCategoryModel productCategoryModel) {
		ProductCategory productCategory = new ProductCategory(productCategoryModel.getCode(), productCategoryModel.getWording());
		productCategoryRepository.save(productCategory);
		return productCategory;
	}

	@Override
	public List<ProductCategory> getAllProductCategories() {
		return productCategoryRepository.findAll();
	}

	@Override
	public ProductCategory getProductCategory(Long productCategoryId) {
		return productCategoryRepository.findById(productCategoryId).orElse(null);
	}

	@Override
	public ProductCategory updateProductCategory(ProductCategoryModel productCategoryModel) {
		ProductCategory productCategory = getProductCategory(productCategoryModel.getId());
		productCategory.setCode(Optional.ofNullable(productCategoryModel.getCode()).orElse(productCategory.getCode()));
		productCategory.setWording(Optional.ofNullable(productCategoryModel.getWording()).orElse(productCategory.getWording()));
		return productCategory;
	}

	@Override
	public void deleteProductCategory(Long productCategoryId) {
		productCategoryRepository.deleteById(productCategoryId);
	}

}
