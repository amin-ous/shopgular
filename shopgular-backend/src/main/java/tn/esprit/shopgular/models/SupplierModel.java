package tn.esprit.shopgular.models;

import lombok.*;
import tn.esprit.shopgular.entities.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierModel {

	private Long id;

	private String code;

	private String wording;

	private SupplierCategory category;

	private SupplierDetailsModel details;

}
