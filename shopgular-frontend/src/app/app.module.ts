import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { ActivitySectorComponent } from './components/activity-sector/activity-sector.component';
import { InvoiceComponent } from './components/invoice/invoice.component';
import { InvoiceItemComponent } from './components/invoice-item/invoice-item.component';
import { OperatorComponent } from './components/operator/operator.component';
import { ProductComponent } from './components/product/product.component';
import { RegulationComponent } from './components/regulation/regulation.component';
import { StockComponent } from './components/stock/stock.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ActivitySectorComponent,
    InvoiceComponent,
    InvoiceItemComponent,
    OperatorComponent,
    ProductComponent,
    RegulationComponent,
    StockComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModalModule,
    NgbModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
