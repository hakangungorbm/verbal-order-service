package tr.com.medlineadana.verbalorder.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OlayPageableSearchRequest {
    private Long id;
    private String type;
    private String number;
    private String cevap;
    private LocalDateTime doctorApproveDate;
    private Boolean gorulmeDurumu;
    private String doctorName;
    private String nurseName;
    private String patientNo;
    private String patientName;
    private LocalDate patientBirthdate;
    private Integer yatakgrubuKodu;
    private String yatakgrubuAdi;
    private PageSortInfo page;
}
