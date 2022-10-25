import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Stock } from 'src/app/models/stock.model';
import { StockService } from 'src/app/services/stock.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})

export class StockComponent implements OnInit {

  stock!: Stock;
  stocks!: Stock[];

  constructor(private stockService: StockService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllStocks();
  }

  getAllStocks(): void {
    this.stockService.getAllStocks().subscribe((data: any) => this.stocks = data);
  }

  addStock(stock: Stock): void {
    this.stockService.addStock(stock).subscribe(() => this.getAllStocks());
  }

  updateStock(stock: Stock): void {
    this.stockService.updateStock(stock).subscribe();
  }

  deleteStock(stockId: number): void {
    this.stockService.deleteStock(stockId).subscribe(() => this.getAllStocks());
  }

  openModal(content: TemplateRef<Stock>, action: Stock | null): void {
    action ? this.stock = action : this.stock = new Stock();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result;
  }

}
