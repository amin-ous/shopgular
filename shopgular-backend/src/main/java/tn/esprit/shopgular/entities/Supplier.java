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
@RequiredArgsConstructor
public class Supplier implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String code;

	@NonNull
	private String wording;

	@NonNull
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

}
