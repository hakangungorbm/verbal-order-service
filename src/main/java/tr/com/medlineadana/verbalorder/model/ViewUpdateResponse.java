package tr.com.medlineadana.verbalorder.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Onay islemi sonrası dönen DTO")
public class ViewUpdateResponse {

    @ApiModelProperty(value = "Kaydin numarasi")
    private String numara;

    @ApiModelProperty(value = "Gorulme Durumu")
    private Boolean gorulmeDurumu;

    @ApiModelProperty(value = "Code")
    private String code;
}
