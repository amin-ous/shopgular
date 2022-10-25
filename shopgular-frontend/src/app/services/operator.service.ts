import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Operator } from '../models/operator.model';

@Injectable({
  providedIn: 'root'
})

export class OperatorService {

  readonly API_URL = 'http://localhost:8089/operator';

  constructor(private httpClient: HttpClient) { }

  addOperator(operator: Operator) {
    return this.httpClient.post(`${this.API_URL}/add-operator`, operator);
  }

  getAllOperators() {
    return this.httpClient.get(`${this.API_URL}/get-all-operators`);
  }

  updateOperator(operator: Operator) {
    return this.httpClient.put(`${this.API_URL}/update-operator`, operator);
  }

  deleteOperator(operatorId: number) {
    return this.httpClient.delete(`${this.API_URL}/delete-operator/${operatorId}`);
  }

}
