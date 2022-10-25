import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivitySectorComponent } from './components/activity-sector/activity-sector.component';
import { InvoiceComponent } from './components/invoice/invoice.component';
import { InvoiceItemComponent } from './components/invoice-item/invoice-item.component';
import { OperatorComponent } from './components/operator/operator.component';
import { ProductComponent } from './components/product/product.component';
import { RegulationComponent } from './components/regulation/regulation.component';
import { StockComponent } from './components/stock/stock.component';

const routes: Routes = [
  { path: 'activity-sector', component: ActivitySectorComponent },
  {
    path: 'invoice', children: [
      { path: '', component: InvoiceComponent },
      { path: ':id/items', component: InvoiceItemComponent }
    ]
  },
  { path: 'operator', component: OperatorComponent },
  { path: 'product', component: ProductComponent },
  { path: 'regulation', component: RegulationComponent },
  { path: 'stock', component: StockComponent },
  { path: '', redirectTo: 'product', pathMatch: 'full' }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
