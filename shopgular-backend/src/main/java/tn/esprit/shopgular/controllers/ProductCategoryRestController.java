package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/product-category")
@Api(tags = "Product Category Management")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductCategoryRestController {

	@Autowired
	ProductCategoryServiceInt productCategoryServiceInt;

	@ResponseBody
	@PostMapping("/add-product-category")
	public ProductCategory addProductCategory(@RequestBody ProductCategoryModel productCategoryModel) {
		return productCategoryServiceInt.addProductCategory(productCategoryModel);
	}

	@ResponseBody
	@GetMapping("/get-all-product-categories")
	public List<ProductCategory> getAllProductCategories() {
		return productCategoryServiceInt.getAllProductCategories();
	}

	@ResponseBody
	@GetMapping("/get-product-category/{product-category-id}")
	public ProductCategory getProductCategory(@PathVariable("product-category-id") Long productCategoryId) {
		return productCategoryServiceInt.getProductCategory(productCategoryId);
	}

	@PutMapping("/update-product-category")
	@ResponseBody
	public ProductCategory updateProductCategory(@RequestBody ProductCategoryModel productCategoryModel) {
		return productCategoryServiceInt.updateProductCategory(productCategoryModel);
	}

	@DeleteMapping("/delete-product-category/{product-category-id}")
	@ResponseBody
	public void deleteProductCategory(@PathVariable("product-category-id") Long productCategoryId) {
		productCategoryServiceInt.deleteProductCategory(productCategoryId);
	}

}
