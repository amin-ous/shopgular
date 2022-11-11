package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface StockServiceInt {

	Stock addStock(Stock stock);

	List<Stock> getAllStocks();

	Stock getStock(Long stockId);

	String getStockStatus();

	Stock updateStock(Stock stock);

	void deleteStock(Long stockId);

}
