package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface ProductServiceInt {

	Product addProduct(Product product);

	List<Product> getAllProducts();

	Product getProduct(Long productId);

	double getPeriodicProductRevenue(Long productId, Date startDate, Date endDate);

	Product updateProduct(Product product);

	void assignProductToStock(Long productId, Long stockId);

	void deleteProduct(Long productId);

}
