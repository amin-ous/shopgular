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
public class Product implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String code;

	@NonNull
	private String wording;

	@NonNull
	private Double listPrice;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date lastModificationDate;

	@JsonIgnore
	@ManyToOne
	private ProductCategory category;

	@JsonIgnore
	@ManyToOne
	private Stock stock;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<InvoiceItem> items;

}
