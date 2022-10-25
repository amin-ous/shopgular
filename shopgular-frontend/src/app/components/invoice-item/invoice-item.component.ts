import { Component, OnInit } from '@angular/core';
import { InvoiceItem } from 'src/app/models/invoice-item.model';
import { InvoiceService } from 'src/app/services/invoice.service';

@Component({
  selector: 'app-invoice-item',
  templateUrl: './invoice-item.component.html',
  styleUrls: ['./invoice-item.component.css']
})

export class InvoiceItemComponent implements OnInit {

  invoiceItems!: InvoiceItem[];

  constructor(private invoiceService: InvoiceService) { }

  ngOnInit(): void {
    this.getAllInvoiceItems();
  }

  getAllInvoiceItems(): void {
    this.invoiceService.getInvoiceItems(1).subscribe((data: any) => this.invoiceItems = data);
  }

}
