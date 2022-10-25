package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/product")
@Api(tags = "Product Management")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

	@Autowired
	ProductServiceInt productServiceInt;

	@ResponseBody
	@PostMapping("/add-product")
	public Product addProduct(@RequestBody ProductModel productModel) {
		return productServiceInt.addProduct(productModel);
	}

	@ResponseBody
	@GetMapping("/get-all-products")
	public List<Product> getAllProducts() {
		return productServiceInt.getAllProducts();
	}

	@ResponseBody
	@GetMapping("/get-product/{product-id}")
	public Product getProduct(@PathVariable("product-id") Long productId) {
		return productServiceInt.getProduct(productId);
	}

	@ResponseBody
	@GetMapping("/get-periodic-product-revenue/{product-id/{start-date}/{end-date}")
	public double getPeriodicProductRevenue(@PathVariable("product-id") Long productId,
	@PathVariable(name = "start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
	@PathVariable(name = "end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return productServiceInt.getPeriodicProductRevenue(productId, startDate, endDate);
	}

	@PutMapping("/update-product")
	@ResponseBody
	public Product updateProduct(@RequestBody ProductModel productModel) {
		return productServiceInt.updateProduct(productModel);
	}

	@PutMapping("/assign-product-to-stock/{product-id}/{stock-id}")
	@ResponseBody
	public void assignProductToStock(@PathVariable("product-id") Long productId, @PathVariable("stock-id") Long stockId) {
		productServiceInt.assignProductToStock(productId, stockId);
	}

	@DeleteMapping("/delete-product/{product-id}")
	@ResponseBody
	public void deleteProduct(@PathVariable("product-id") Long productId) {
		productServiceInt.deleteProduct(productId);
	}

}
