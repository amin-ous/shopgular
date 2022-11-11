package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockModel {

	private Long id;

	private String wording;

	private Integer currentQuantity;

	private Integer minimumQuantity;

}
