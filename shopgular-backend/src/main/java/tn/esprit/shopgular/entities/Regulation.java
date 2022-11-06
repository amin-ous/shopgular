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
public class Regulation implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amountPaid;

	private Double amountRemaining;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	private Boolean paid;

	@JsonIgnore
	@ManyToOne
	private Invoice invoice;

	public Regulation(Double amountPaid, Double amountRemaining) {
		this.amountPaid = amountPaid;
		this.amountRemaining = amountRemaining;
	}

	public Regulation(Long id, Double amountPaid, Double amountRemaining) {
		this.id = id;
		this.amountPaid = amountPaid;
		this.amountRemaining = amountRemaining;
	}

}
