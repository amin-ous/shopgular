package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryServiceInt {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public ProductCategory addProductCategory(ProductCategory productCategory) {
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
	public ProductCategory updateProductCategory(ProductCategory productCategory) {
		ProductCategory targetedProductCategory = getProductCategory(productCategory.getId());
		targetedProductCategory.setCode(Optional.ofNullable(productCategory.getCode()).orElse(targetedProductCategory.getCode()));
		targetedProductCategory.setWording(Optional.ofNullable(productCategory.getWording()).orElse(targetedProductCategory.getWording()));
		return targetedProductCategory;
	}

	@Override
	public void deleteProductCategory(Long productCategoryId) {
		productCategoryRepository.deleteById(productCategoryId);
	}

}
