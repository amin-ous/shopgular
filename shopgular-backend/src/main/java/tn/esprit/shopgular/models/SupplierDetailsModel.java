package tn.esprit.shopgular.models;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SupplierDetailsModel {

	@NonNull
	private String email;

	@NonNull
	private String address;

	@NonNull
	private String serialNumber;

	private Date collaborationDate;

}
