package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/stock")
@Api(tags = "Stock Management")
@CrossOrigin(origins = "http://localhost:4200")
public class StockRestController {

	@Autowired
	StockServiceInt stockServiceInt;

	@ResponseBody
	@PostMapping("/add-stock")
	public Stock addStock(@RequestBody StockModel stockModel) {
		return stockServiceInt.addStock(stockModel);
	}

	@ResponseBody
	@GetMapping("/get-all-stocks")
	public List<Stock> getAllStocks() {
		return stockServiceInt.getAllStocks();
	}

	@ResponseBody
	@GetMapping("/get-stock/{stock-id}")
	public Stock getStock(@PathVariable("stock-id") Long stockId) {
		return stockServiceInt.getStock(stockId);
	}

	@ResponseBody
	@GetMapping("/get-stock-status")
	public void getStockStatus() {
		stockServiceInt.getStockStatus();
	}

	@PutMapping("/update-stock")
	@ResponseBody
	public Stock updateStock(@RequestBody StockModel stockModel) {
		return stockServiceInt.updateStock(stockModel);
	}

	@DeleteMapping("/delete-stock/{stock-id}")
	@ResponseBody
	public void deleteStock(@PathVariable("stock-id") Long stockId) {
		stockServiceInt.deleteStock(stockId);
	}

}
