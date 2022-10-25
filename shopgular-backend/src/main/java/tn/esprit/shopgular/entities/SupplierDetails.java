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
public class SupplierDetails implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String email;

	@NonNull
	private String address;

	@NonNull
	private String serialNumber;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date collaborationDate;

	@JsonIgnore
	@OneToOne(mappedBy = "details")
	private Supplier supplier;

}
