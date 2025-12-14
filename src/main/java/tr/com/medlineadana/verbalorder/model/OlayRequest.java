package tr.com.medlineadana.verbalorder.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description ="Sozel Order, tetkik veya panikdeger islemlerin onay durumlarini tasir")
public class OlayRequest {
    @Schema(description = "Olay tipi - order veya  tetkik olabilir", name = "type", type = "string")
    @NotNull(message = "Olay verisi bos olamaz!")
    private String type;

    @Schema(description = "Order numarasi", name = "number", type = "string")
    @Size(min = 6, max = 8, message = "Kayit numarasi 6 haneli olmalidir!")
    private String number;

    @Schema(description = "Onay cevabi - Onaylıyorum - Red Ediyorum", name = "cevap", type = "string")
    @NotNull(message = "Seceneklerden birisi secilmis olmali! Bos olamaz")
    private String cevap;
}