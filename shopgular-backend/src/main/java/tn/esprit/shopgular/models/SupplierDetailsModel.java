package tn.esprit.shopgular.models;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDetailsModel {

	private Long id;

	private String email;

	private String address;

	private String serialNumber;

	private Date collaborationDate;

}
