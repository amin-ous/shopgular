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
public class Invoice implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private Double netPrice;

	@NonNull
	private Double deduction;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date lastModificationDate;

	@NonNull
	private Boolean archived;

	@NonNull
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<InvoiceItem> items;

	@JsonIgnore
	@ManyToOne
	private Supplier supplier;

	@JsonIgnore
	@OneToMany(mappedBy = "invoice")
	private Set<Regulation> regulations;

}
