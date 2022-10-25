import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Invoice } from 'src/app/models/invoice.model';
import { Product } from 'src/app/models/product.model';
import { InvoiceItem } from 'src/app/models/invoice-item.model';
import { InvoiceService } from 'src/app/services/invoice.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})

export class InvoiceComponent implements OnInit {

  invoice!: Invoice;
  products!: Product[];
  invoices!: Invoice[];
  invoiceItems: InvoiceItem[] = [];
  invoiceItem: InvoiceItem = new InvoiceItem();

  constructor(private invoiceService: InvoiceService, private productService: ProductService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllProducts();
    this.getAllInvoices();
  }

  getAllProducts(): void {
    this.productService.getAllProducts().subscribe((data: any) => this.products = data);
  }

  getAllInvoices(): void {
    this.invoiceService.getAllInvoices().subscribe((data: any) => this.invoices = data);
  }

  addInvoice(invoice: Invoice): void {
    this.invoiceItems.push(this.invoiceItem);
    this.invoice.items = this.invoiceItems;
    this.invoiceService.addInvoice(invoice).subscribe(() => this.getAllInvoices());
  }

  cancelInvoice(invoiceId: number): void {
    this.invoiceService.cancelInvoice(invoiceId).subscribe(() => this.getAllInvoices());
  }

  deleteInvoice(invoiceId: number): void {
    this.invoiceService.deleteInvoice(invoiceId).subscribe(() => this.getAllInvoices());
  }

  openModal(content: TemplateRef<Invoice>, action: Invoice | null): void {
    action ? this.invoice = action : this.invoice = new Invoice();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result;
  }

}
