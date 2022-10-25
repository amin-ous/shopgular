package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class RegulationModel {

	private long id;

	@NonNull
	private Double amountPaid;

	@NonNull
	private Double amountRemaining;

}
