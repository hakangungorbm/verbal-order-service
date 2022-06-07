package tr.com.medlineadana.verbalorder.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel(value="Sozel Order, tetkik veya panikdeger islemlerin onay durumlarini tasir")
public class OlayRequest {
    @ApiModelProperty(value = "Olay tipi - order veya  tetkik olabilir", name = "type", dataType = "String")
    @NotNull(message = "Olay verisi bos olamaz!")
    private String type;

    @ApiModelProperty(value = "Order numarasi", name = "number", dataType = "String")
    @Size(min = 6, max = 8, message = "Kayit numarasi 6 haneli olmalidir!")
    private String number;

    @ApiModelProperty(value = "Onay cevabi - Onaylıyorum - Red Ediyorum", name = "cevap", dataType = "String")
    @NotNull(message = "Seceneklerden birisi secilmis olmali! Bos olamaz")
    private String cevap;
}