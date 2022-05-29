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
@ApiModel(value = "Monad veri tabanlarindaki iliskili kaydi doner")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MonadResponse {

    @ApiModelProperty(value = "Kayit tarihi")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime kayitTarihi;

    @ApiModelProperty(value = "Hasta No")
    private String hastaNo;

    @ApiModelProperty(value = "Hasta dogum tarihi")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dogumTarihi;
}