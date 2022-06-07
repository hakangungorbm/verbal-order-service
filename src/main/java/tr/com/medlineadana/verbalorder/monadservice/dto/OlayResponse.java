package tr.com.medlineadana.verbalorder.monadservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Monad veri tabanlarindaki iliskili sözel order detayını döner")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OlayResponse {

    @ApiModelProperty(value = "Doktor adi soyadi")
    private String tedaviDoktoru;

    @ApiModelProperty(value = "Hemsire adi soyadi")
    private String kayitedenPersonel;

    @ApiModelProperty(value = "Hasta adi soyadi")
    private String hastaadiSoyadi;

    @ApiModelProperty(value = "Kayit tarihi")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime kayitTarihi;

    @ApiModelProperty(value = "Hasta No")
    private String hastaNo;

    @ApiModelProperty(value = "Hasta dogum tarihi")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dogumTarihi;

    @ApiModelProperty(value = "Hasta Katı")
    private Integer yatakgrubuKodu;

    @ApiModelProperty(value = "Hasta Yattığı kat ve durumu")
    private String yatakgrubuAdi;

}