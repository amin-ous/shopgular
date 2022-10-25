import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Regulation } from '../models/regulation.model';

@Injectable({
  providedIn: 'root'
})

export class RegulationService {

  readonly API_URL = 'http://localhost:8089/regulation';

  constructor(private httpClient: HttpClient) { }

  addRegulation(regulation: Regulation) {
    return this.httpClient.post(`${this.API_URL}/add-regulation`, regulation);
  }

  getAllRegulations() {
    return this.httpClient.get(`${this.API_URL}/get-all-regulations`);
  }

  updateRegulation(regulation: Regulation) {
    return this.httpClient.put(`${this.API_URL}/update-regulation`, regulation);
  }

  deleteRegulation(regulationId: number) {
    return this.httpClient.delete(`${this.API_URL}/delete-regulation/${regulationId}`);
  }

}
