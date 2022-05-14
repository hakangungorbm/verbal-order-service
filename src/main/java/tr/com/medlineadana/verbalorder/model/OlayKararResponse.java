package tr.com.medlineadana.verbalorder.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Onay islemi sonrası dönen DTO")
public class OlayKararResponse {
    @ApiModelProperty(value = "Kaydin numarasi")
    private String number;
    @ApiModelProperty(value = "Onay Durumu")
    private OnayDurumlari onayDurumu;
}
