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
@ApiModel(value = "Monad veri tabanlarindaki bir olayin islem listesini dönen DTO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OlayIslemleriResponse {

    @ApiModelProperty(value = "Islem Tarihi")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate islemTarihi;

    @ApiModelProperty(value = "Islem adi")
    private String islemAdi;

    @ApiModelProperty(value = "Sonuc")
    private String sonuc;

    @ApiModelProperty(value = "Frekans Deger")
    private String frekansDeger;

}