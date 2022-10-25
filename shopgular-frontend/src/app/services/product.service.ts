import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})

export class ProductService {

  readonly API_URL = 'http://localhost:8089/product';

  constructor(private httpClient: HttpClient) { }

  addProduct(product: Product) {
    return this.httpClient.post(`${this.API_URL}/add-product`, product);
  }

  getAllProducts() {
    return this.httpClient.get(`${this.API_URL}/get-all-products`);
  }

  updateProduct(product: Product) {
    return this.httpClient.put(`${this.API_URL}/update-product`, product);
  }

  deleteProduct(productId: number) {
    return this.httpClient.delete(`${this.API_URL}/delete-product/${productId}`);
  }

}
