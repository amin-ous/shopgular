import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stock } from '../models/stock.model';

@Injectable({
  providedIn: 'root'
})

export class StockService {

  readonly API_URL = 'http://localhost:8089/stock';

  constructor(private httpClient: HttpClient) { }

  addStock(stock: Stock) {
    return this.httpClient.post(`${this.API_URL}/add-stock`, stock);
  }

  getAllStocks() {
    return this.httpClient.get(`${this.API_URL}/get-all-stocks`);
  }

  updateStock(stock: Stock) {
    return this.httpClient.put(`${this.API_URL}/update-stock`, stock);
  }

  deleteStock(stockId: number) {
    return this.httpClient.delete(`${this.API_URL}/delete-stock/${stockId}`);
  }

}
