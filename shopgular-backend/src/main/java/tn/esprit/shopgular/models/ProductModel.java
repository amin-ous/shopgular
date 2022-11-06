package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

	private Long id;

	private String code;

	private String wording;

	private Double listPrice;

}
