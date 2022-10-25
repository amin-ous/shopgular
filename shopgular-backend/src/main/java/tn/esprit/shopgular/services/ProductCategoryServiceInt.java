package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface ProductCategoryServiceInt {

	ProductCategory addProductCategory(ProductCategoryModel productCategoryModel);

	List<ProductCategory> getAllProductCategories();

	ProductCategory getProductCategory(Long productCategoryId);

	ProductCategory updateProductCategory(ProductCategoryModel productCategoryModel);

	void deleteProductCategory(Long productCategoryId);

}
