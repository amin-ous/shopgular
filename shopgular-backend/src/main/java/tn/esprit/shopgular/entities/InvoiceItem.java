package tn.esprit.shopgular.entities;

import com.fasterxml.jackson.annotation.*;
import java.io.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class InvoiceItem implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private Double salePrice;

	@NonNull
	private Double discount;

	@NonNull
	private Integer percentDecrease;

	@NonNull
	private Integer quantity;

	@NonNull
	@ManyToOne
	private Product product;

	@JsonIgnore
	@ManyToOne
	private Invoice invoice;

}
