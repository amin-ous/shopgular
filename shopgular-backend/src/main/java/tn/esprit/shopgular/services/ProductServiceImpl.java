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
public class ProductServiceImpl implements ProductServiceInt {

	@Autowired
	InvoiceItemRepository invoiceItemRepository;
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	StockRepository stockRepository;

	@Override
	public Product addProduct(ProductModel productModel) {
		Product product = new Product(productModel.getCode(), productModel.getWording(), productModel.getListPrice(), new Date(), new Date());
		productRepository.save(product);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public double getPeriodicProductRevenue(Long productId, Date startDate, Date endDate) {
		return invoiceItemRepository.getPeriodicProductRevenue(productId, startDate, endDate);
	}

	@Override
	public Product updateProduct(ProductModel productModel) {
		Product product = getProduct(productModel.getId());
		product.setCode(Optional.ofNullable(productModel.getCode()).orElse(product.getCode()));
		product.setWording(Optional.ofNullable(productModel.getWording()).orElse(product.getWording()));
		product.setListPrice(Optional.ofNullable(productModel.getListPrice()).orElse(product.getListPrice()));
		product.setLastModificationDate(new Date());
		productRepository.save(product);
		return product;
	}

	@Override
	public void assignProductToStock(Long productId, Long stockId) {
		Stock stock = stockRepository.getById(stockId);
		Product product = getProduct(productId);
		product.setStock(stock);
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

}
