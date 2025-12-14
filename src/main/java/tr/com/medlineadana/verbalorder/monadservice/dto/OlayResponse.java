package tr.com.medlineadana.verbalorder.monadservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Monad veri tabanlarindaki iliskili sözel order detayını döner")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OlayResponse {

    @Schema(description = "Doktor adi soyadi")
    private String tedaviDoktoru;

    @Schema(description = "Hemsire adi soyadi")
    private String kayitedenPersonel;

    @Schema(description = "Hasta adi soyadi")
    private String hastaadiSoyadi;

    @Schema(description = "Kayit tarihi")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime kayitTarihi;

    @Schema(description = "Hasta No")
    private String hastaNo;

    @Schema(description = "Hasta dogum tarihi")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dogumTarihi;

    @Schema(description = "Hasta Katı")
    private Integer yatakgrubuKodu;

    @Schema(description = "Hasta Yattığı kat ve durumu")
    private String yatakgrubuAdi;

}