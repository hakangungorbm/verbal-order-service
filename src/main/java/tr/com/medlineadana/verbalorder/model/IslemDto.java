package tr.com.medlineadana.verbalorder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Olay no bilinen bir olayin islem listesini doner")
public class IslemDto {

    @ApiModelProperty(value = "Islem Tarihi")
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate islemTarihi;

    @ApiModelProperty(value = "Islem adi")
    private String islemAdi;

    @ApiModelProperty(value = "Sonuc")
    private String sonuc;

    @ApiModelProperty(value = "Frekans Deger")
    private String frekansDeger;

}