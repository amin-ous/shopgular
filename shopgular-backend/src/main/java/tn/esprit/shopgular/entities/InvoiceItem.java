package tn.esprit.shopgular.entities;

import com.fasterxml.jackson.annotation.*;
import java.io.*;
import javax.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double salePrice;

	private Double discount;

	@Range(min = 0, max = 100)
	private Integer percentDecrease;

	private Integer quantity;

	@ManyToOne
	private Product product;

	@JsonIgnore
	@ManyToOne
	private Invoice invoice;

	public InvoiceItem(@Range(min = 0, max = 100) Integer percentDecrease, Integer quantity, Product product) {
		this.percentDecrease = percentDecrease;
		this.quantity = quantity;
		this.product = product;
	}

}
