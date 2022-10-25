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
public class Stock implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String wording;

	@NonNull
	private Integer currentQuantity;

	@NonNull
	private Integer minimumQuantity;

	@JsonIgnore
	@OneToMany(mappedBy = "stock")
	private Set<Product> products;

}
