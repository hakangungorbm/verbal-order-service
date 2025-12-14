package tr.com.medlineadana.verbalorder.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
@Schema(description ="Gorulme Durumu onayi icin gelen istekleri tasir")
public class ViewUpdateRequest {

    @Schema(description = "Gorulme onayi verilen kaydin Id si", name = "id", type = "long")
    private Long id;

    @Schema(description = "Olay tipi - order, tetkik, panikdeger olabilir", name = "olay", type = "string")
    private String olay;

    @Schema(description = "Order, tetkik, panikdeger numarasi", name = "numara", type = "string")
    @Size(min = 6, max = 8, message = "Kayit numarasi 6 haneli olmalidir!")
    private String numara;

}