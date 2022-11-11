package tn.esprit.shopgular.entities;

import com.fasterxml.jackson.annotation.*;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operator implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String surname;

	private String prename;

	private String oldPassword;

	private String currentPassword;

	@JsonIgnore
	@OneToMany
	private Set<Invoice> invoices;

	public Operator(String surname, String prename, String currentPassword) {
		this.surname = surname;
		this.prename = prename;
		this.currentPassword = currentPassword;
	}

	public Operator(Long id, String surname, String prename, String oldPassword, String currentPassword) {
		this.id = id;
		this.surname = surname;
		this.prename = prename;
		this.oldPassword = oldPassword;
		this.currentPassword = currentPassword;
	}

}
