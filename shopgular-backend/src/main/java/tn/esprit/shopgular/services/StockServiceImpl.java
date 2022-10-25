package tn.esprit.shopgular.services;

import java.text.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class StockServiceImpl implements StockServiceInt {

	@Autowired
	StockRepository stockRepository;

	@Override
	public Stock addStock(StockModel stockModel) {
		Stock stock = new Stock(stockModel.getWording(), stockModel.getCurrentQuantity(), stockModel.getMinimumQuantity());
		return stockRepository.save(stock);
	}

	@Override
	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	@Override
	public Stock getStock(Long stockId) {
		return stockRepository.findById(stockId).orElse(null);
	}

	@Override
	@Scheduled(fixedRate = 60000)
	public String getStockStatus() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String date = sdf.format(now);
		List<Stock> stocks = getAllStocks();
		StringBuilder stockStatusBuilder = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		for (int i = 0; i < stocks.size(); i++) {
			if (stocks.get(i).getMinimumQuantity() > stocks.get(i).getCurrentQuantity()) {
				stockStatusBuilder.append(newLine + stockStatusBuilder + date + newLine + " : The current quantity of '" +
				stocks.get(i).getWording() + "' stock is '" +
				stocks.get(i).getCurrentQuantity() + "' which is less than the minimum value '" +
				stocks.get(i).getMinimumQuantity() + "'." + newLine);
			}
		}
		return stockStatusBuilder.toString();
	}

	@Override
	public Stock updateStock(StockModel stockModel) {
		Stock stock = getStock(stockModel.getId());
		stock.setWording(Optional.ofNullable(stockModel.getWording()).orElse(stock.getWording()));
		stock.setCurrentQuantity(Optional.ofNullable(stockModel.getCurrentQuantity()).orElse(stock.getCurrentQuantity()));
		stock.setMinimumQuantity(Optional.ofNullable(stockModel.getMinimumQuantity()).orElse(stock.getMinimumQuantity()));
		stockRepository.save(stock);
		return stock;
	}

	@Override
	public void deleteStock(Long stockId) {
		stockRepository.deleteById(stockId);
	}

}
