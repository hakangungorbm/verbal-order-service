package tr.com.medlineadana.verbalorder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.medlineadana.verbalorder.enums.Olaylar;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Springdoc / OpenAPI 3 uyumlu

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Tum olay kayitlarini sunan DTO (Pageable)")
public class OlayKayitResponse {

    @Schema(description = "Kayıt ID")
    private Long id;

    @Schema(
            description = "Doktorun SMS mesajı üzerinden işlem yaptığı tarih",
            example = "14.12.2025 16:30"
    )
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime doctorApproveDate;

    @Schema(description = "Order, tetkik veya panik değer şeklinde olay tipi")
    private Olaylar olay;

    @Schema(description = "Order, tetkik veya panik değer numarası", example = "20251214")
    private String numara;

    @Schema(description = "Doktorun ekrandan seçtiği onay durumu")
    private OnayDurumlari onayDurumu;

    @Schema(description = "hemsirenin ekrandan goruldu onayi verme durumu")
    private Boolean gorulmeDurumu;

    @Schema(description = "hemsirenin ekranda goruldu yaptigi zamani")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime viewDate;

    @Schema(description = "ordera muhatap doktor adi")
    private String doctorName;

    @Schema(description = "order olusturan hemsire adi soyadi")
    private String nurseName;

    @Schema(description = "hemsirenin order olusturdugu zaman")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime orderCreatedDate;

    @Schema(description = "hasta numarasi")
    private String patientNo;

    @Schema(description = "hasta adi soyadi")
    private String patientName;

    @Schema(description = "hasta dogum tarihi")
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate patientBirthdate;

    @Schema(description = "Hasta Katı")
    private Integer yatakgrubuKodu;

    @Schema(description = "Hasta Yattığı kat ve durumu")
    private String yatakgrubuAdi;
}