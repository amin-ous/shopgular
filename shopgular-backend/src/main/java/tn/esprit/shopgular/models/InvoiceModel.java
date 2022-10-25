package tn.esprit.shopgular.models;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceModel {

	private Set<InvoiceItemModel> items;

}
