package tr.com.medlineadana.verbalorder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class OlayDetayResponse {
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate islemTarihi;
    private List<IslemDto> list;
    private String code;
    private Boolean sonucVarmi;
}
