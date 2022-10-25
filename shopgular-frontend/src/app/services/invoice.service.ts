import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Invoice } from '../models/invoice.model';

@Injectable({
  providedIn: 'root'
})

export class InvoiceService {

  readonly API_URL = 'http://localhost:8089/invoice';

  constructor(private httpClient: HttpClient) { }

  addInvoice(invoice: Invoice) {
    return this.httpClient.post(`${this.API_URL}/add-invoice`, invoice);
  }

  getAllInvoices() {
    return this.httpClient.get(`${this.API_URL}/get-all-invoices`);
  }

  getInvoiceItems(invoiceId: number) {
    return this.httpClient.get(`${this.API_URL}/get-invoice-items/${invoiceId}`);
  }

  cancelInvoice(invoiceId: number) {
    return this.httpClient.put(`${this.API_URL}/cancel-invoice/${invoiceId}`, null);
  }

  deleteInvoice(invoiceId: number) {
    return this.httpClient.delete(`${this.API_URL}/delete-invoice/${invoiceId}`);
  }

}
