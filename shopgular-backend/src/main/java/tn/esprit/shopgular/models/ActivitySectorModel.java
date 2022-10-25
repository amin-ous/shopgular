package tn.esprit.shopgular.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ActivitySectorModel {

	private long id;

	@NonNull
	private String code;

	@NonNull
	private String wording;

}
