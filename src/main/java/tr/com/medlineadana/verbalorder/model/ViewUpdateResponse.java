package tr.com.medlineadana.verbalorder.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Onay islemi sonrası dönen DTO")
public class ViewUpdateResponse {

    @Schema(description = "Kaydin numarasi")
    private String numara;

    @Schema(description = "Gorulme Durumu")
    private Boolean gorulmeDurumu;

    @Schema(description = "Code")
    private String code;
}
