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
public class SupplierDetails implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String address;

	private String serialNumber;

	@Temporal(TemporalType.DATE)
	private Date collaborationDate;

	@JsonIgnore
	@OneToOne(mappedBy = "details")
	private Supplier supplier;

	public SupplierDetails(String email, String address, String serialNumber) {
		this.email = email;
		this.address = address;
		this.serialNumber = serialNumber;
	}

	public SupplierDetails(Long id, String email, String address, String serialNumber) {
		this.id = id;
		this.email = email;
		this.address = address;
		this.serialNumber = serialNumber;
	}

}
