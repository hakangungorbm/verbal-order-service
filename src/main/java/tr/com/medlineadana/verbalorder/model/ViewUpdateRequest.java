package tr.com.medlineadana.verbalorder.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
@ApiModel(value="Gorulme Durumu onayi icin gelen istekleri tasir")
public class ViewUpdateRequest {

    @ApiModelProperty(value = "Gorulme onayi verilen kaydin Id si", name = "id", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "Olay tipi - order veya  tetkik olabilir", name = "olay", dataType = "String")
    private String olay;

    @ApiModelProperty(value = "Order veya tetkik numarasi", name = "numara", dataType = "String")
    @Size(min = 6, max = 6, message = "Kayit numarasi 6 haneli olmalidir!")
    private String numara;

}