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
public class Product implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String wording;

	private Double listPrice;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

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

	public Product(String code, String wording, Double listPrice) {
		this.code = code;
		this.wording = wording;
		this.listPrice = listPrice;
	}

	public Product(Long id, String code, String wording, Double listPrice) {
		this.id = id;
		this.code = code;
		this.wording = wording;
		this.listPrice = listPrice;
	}

}
