package tn.esprit.shopgular.entities;

import com.fasterxml.jackson.annotation.*;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String wording;

	@Enumerated(EnumType.STRING)
	private SupplierCategory category;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SupplierDetails details;

	@JsonIgnore
	@ManyToMany
	private Set<ActivitySector> activitySectors;

	@JsonIgnore
	@OneToMany(mappedBy = "supplier")
	private Set<Invoice> invoices;

	public Supplier(String code, String wording, SupplierCategory category, SupplierDetails details) {
		this.code = code;
		this.wording = wording;
		this.category = category;
		this.details = details;
	}

	public Supplier(Long id, String code, String wording, SupplierCategory category, SupplierDetails details) {
		this.id = id;
		this.code = code;
		this.wording = wording;
		this.category = category;
		this.details = details;
	}

}
