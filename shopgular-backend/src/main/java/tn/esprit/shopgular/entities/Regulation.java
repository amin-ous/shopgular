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
public class Regulation implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private Double amountPaid;

	@NonNull
	private Double amountRemaining;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@NonNull
	private Boolean paid;

	@JsonIgnore
	@ManyToOne
	private Invoice invoice;

}
