package nisobapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentsItem{
	private String documentType;
	private String documentURL;
	private String documentId;
	private boolean UAEPASSVerifiedFile;
}