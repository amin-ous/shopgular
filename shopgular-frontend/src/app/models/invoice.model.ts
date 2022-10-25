import { InvoiceItem } from "./invoice-item.model";

export class Invoice {
    id!: number;
    netPrice!: number;
    deduction!: number;
    creationDate!: Date;
    lastModificationDate!: Date;
    archived!: boolean;
    items!: InvoiceItem[];
}
