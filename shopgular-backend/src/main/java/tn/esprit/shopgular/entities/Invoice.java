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
public class Invoice implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double netPrice;

	private Double deduction;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	private Date lastModificationDate;

	private Boolean archived;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<InvoiceItem> items;

	@JsonIgnore
	@ManyToOne
	private Supplier supplier;

	@JsonIgnore
	@OneToMany(mappedBy = "invoice")
	private Set<Regulation> regulations;

	public Invoice(Set<InvoiceItem> items) {
		this.items = items;
	}

	public Invoice(Long id, Set<InvoiceItem> items) {
		this.id = id;
		this.items = items;
	}

}
