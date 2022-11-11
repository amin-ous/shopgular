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
public class Stock implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String wording;

	private Integer currentQuantity;

	private Integer minimumQuantity;

	@JsonIgnore
	@OneToMany(mappedBy = "stock")
	private Set<Product> products;

	public Stock(String wording, Integer currentQuantity, Integer minimumQuantity) {
		this.wording = wording;
		this.currentQuantity = currentQuantity;
		this.minimumQuantity = minimumQuantity;
	}

	public Stock(Long id, String wording, Integer currentQuantity, Integer minimumQuantity) {
		this.id = id;
		this.wording = wording;
		this.currentQuantity = currentQuantity;
		this.minimumQuantity = minimumQuantity;
	}

}
