package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface StockServiceInt {

	Stock addStock(StockModel stockModel);

	List<Stock> getAllStocks();

	Stock getStock(Long stockId);

	String getStockStatus();

	Stock updateStock(StockModel stockModel);

	void deleteStock(Long stockId);

}
