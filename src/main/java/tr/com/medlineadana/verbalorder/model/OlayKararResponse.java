package tr.com.medlineadana.verbalorder.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Onay islemi sonrası dönen DTO")
public class OlayKararResponse {
    @Schema(description = "Kaydin numarasi")
    private String number;
    @Schema(description = "Onay Durumu")
    private OnayDurumlari onayDurumu;
}
