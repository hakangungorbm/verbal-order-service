package tr.com.medlineadana.verbalorder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.medlineadana.verbalorder.enums.Olaylar;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Tum olay kayitlarini sunan DTO (Pageable)")
public class OlayKayitResponse {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "doktorun sms mesajı uzerinden islem yaptigi tarih")
    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    private LocalDateTime doctorApproveDate;

    @ApiModelProperty(value = "order tetkik, panikdeger seklinde olay tipi")
    private Olaylar olay;

    @ApiModelProperty(value = "order, tetkik, panikdeger numarasi")
    private String numara;

    @ApiModelProperty(value = "doktorun ekrandan sectigi cevap")
    private OnayDurumlari onayDurumu;

    @ApiModelProperty(value = "hemsirenin ekrandan goruldu onayi verme durumu")
    private Boolean gorulmeDurumu;

    @ApiModelProperty(value = "hemsirenin ekranda goruldu yaptigi zamani")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime viewDate;

    @ApiModelProperty(value = "ordera muhatap doktor adi")
    private String doctorName;

    @ApiModelProperty(value = "order olusturan hemsire adi soyadi")
    private String nurseName;

    @ApiModelProperty(value = "hemsirenin order olusturdugu zaman")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime orderCreatedDate;

    @ApiModelProperty(value = "hasta numarasi")
    private String patientNo;

    @ApiModelProperty(value = "hasta adi soyadi")
    private String patientName;

    @ApiModelProperty(value = "hasta dogum tarihi")
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate patientBirthdate;

    @ApiModelProperty(value = "Hasta Katı")
    private Integer yatakgrubuKodu;

    @ApiModelProperty(value = "Hasta Yattığı kat ve durumu")
    private String yatakgrubuAdi;
}