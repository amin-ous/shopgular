import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent implements OnInit {

  product!: Product;
  products!: Product[];

  constructor(private productService: ProductService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts(): void {
    this.productService.getAllProducts().subscribe((data: any) => this.products = data);
  }

  addProduct(product: Product): void {
    this.productService.addProduct(product).subscribe(() => this.getAllProducts());
  }

  updateProduct(product: Product): void {
    this.productService.updateProduct(product).subscribe();
  }

  deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe(() => this.getAllProducts());
  }

  openModal(content: TemplateRef<Product>, action: Product | null): void {
    action ? this.product = action : this.product = new Product();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result;
  }

}
