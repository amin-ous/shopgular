package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StockModel {

	private long id;

	@NonNull
	private String wording;

	@NonNull
	private Integer currentQuantity;

	@NonNull
	private Integer minimumQuantity;

}
