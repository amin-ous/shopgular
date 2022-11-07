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
public class ProductCategory implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String wording;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private Set<Product> products;

	public ProductCategory(String code, String wording) {
		this.code = code;
		this.wording = wording;
	}

	public ProductCategory(Long id, String code, String wording) {
		this.id = id;
		this.code = code;
		this.wording = wording;
	}

}
