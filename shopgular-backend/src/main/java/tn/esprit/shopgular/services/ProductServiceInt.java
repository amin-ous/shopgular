package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface ProductServiceInt {

	Product addProduct(ProductModel productModel);

	List<Product> getAllProducts();

	Product getProduct(Long productId);

	double getPeriodicProductRevenue(Long productId, Date startDate, Date endDate);

	Product updateProduct(ProductModel productModel);

	void assignProductToStock(Long productId, Long stockId);

	void deleteProduct(Long productId);

}
