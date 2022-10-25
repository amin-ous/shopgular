import { Product } from "./product.model";

export class InvoiceItem {
    id!: number;
    salePrice!: number;
    discount!: number;
    percentDecrease!: number;
    quantity!: number;
    product!: Product;
}
