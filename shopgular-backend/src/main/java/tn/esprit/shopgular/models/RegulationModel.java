package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegulationModel {

	private Long id;

	private Double amountPaid;

	private Double amountRemaining;

}
