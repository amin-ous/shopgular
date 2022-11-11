package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface ProductCategoryServiceInt {

	ProductCategory addProductCategory(ProductCategory productCategory);

	List<ProductCategory> getAllProductCategories();

	ProductCategory getProductCategory(Long productCategoryId);

	ProductCategory updateProductCategory(ProductCategory productCategory);

	void deleteProductCategory(Long productCategoryId);

}
