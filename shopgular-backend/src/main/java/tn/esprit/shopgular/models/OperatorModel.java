package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class OperatorModel {

	private Long id;

	private String surname;

	private String prename;

	private String oldPassword;

	private String currentPassword;

}
