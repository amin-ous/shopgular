package tn.esprit.shopgular.models;

import lombok.*;
import tn.esprit.shopgular.entities.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SupplierModel {

	private long id;

	@NonNull
	private String code;

	@NonNull
	private String wording;

	@NonNull
	private SupplierCategory category;

	@NonNull
	private SupplierDetailsModel details;

}
