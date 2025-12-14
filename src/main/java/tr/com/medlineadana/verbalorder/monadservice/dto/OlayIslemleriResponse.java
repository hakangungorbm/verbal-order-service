package tr.com.medlineadana.verbalorder.monadservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Monad veri tabanlarindaki bir olayin islem listesini dönen DTO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OlayIslemleriResponse {

    @Schema(description = "Islem Tarihi")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate islemTarihi;

    @Schema(description = "Islem adi")
    private String islemAdi;

    @Schema(description = "Sonuc")
    private String sonuc;

    @Schema(description = "Frekans Deger")
    private String frekansDeger;

}