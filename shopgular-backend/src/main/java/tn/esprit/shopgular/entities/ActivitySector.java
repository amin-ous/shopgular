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
public class ActivitySector implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String wording;

	@JsonIgnore
	@ManyToMany(mappedBy = "activitySectors")
	private Set<Supplier> suppliers;

	public ActivitySector(String code, String wording) {
		this.code = code;
		this.wording = wording;
	}

	public ActivitySector(Long id, String code, String wording) {
		this.id = id;
		this.code = code;
		this.wording = wording;
	}

}
