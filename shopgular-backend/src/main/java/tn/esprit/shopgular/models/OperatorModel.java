package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class OperatorModel {

	private long id;

	private String name;

	private String prename;

	private String oldPassword;

	private String newPassword;

	public OperatorModel(String name, String prename, String newPassword) {
		this.name = name;
		this.prename = prename;
		this.newPassword = newPassword;
	}

	public OperatorModel(long id, String name, String prename, String oldPassword, String newPassword) {
		this.id = id;
		this.name = name;
		this.prename = prename;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

}
