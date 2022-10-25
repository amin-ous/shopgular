package tn.esprit.shopgular.models;

import lombok.*;
import tn.esprit.shopgular.entities.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemModel {

	private Integer percentDecrease;

	private Integer quantity;

	private Product product;

}
