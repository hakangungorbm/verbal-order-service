package tr.com.medlineadana.verbalorder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import tr.com.medlineadana.verbalorder.enums.Olaylar;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="olay_kayit")
public class OlayKayit {
    private static final long serialVersionUID = 8970281518406996101L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @CreatedDate
    @Column(
            name = "doctor_approve_date",
            columnDefinition = "doktorun sms mesajı uzerinden islem yaptigi tarih",
            insertable = true,
            updatable = false
    )
    private LocalDateTime doctorApproveDate;

    private boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "olay", nullable = false, columnDefinition = "order veya tetkik seklinde olaylar")
    private Olaylar olay;

    @Column(name = "numara", nullable = false, length = 6, columnDefinition = "order veya tetkik numarasi")
    @Size(min = 6, max = 6, message = "Kayit numarasi 6 haneli olmalidir!")
    private String numara;

    @Enumerated(EnumType.STRING)
    @Column(name = "onay_durumu", nullable = false, columnDefinition = "doktorun ekrandan sectigi cevap")
    private OnayDurumlari onayDurumu;

    @Column(name = "gorulme_durumu", nullable = false, columnDefinition = "hemsirenin ekrandan goruldu yapma durumu")
    private boolean gorulmeDurumu;


    @Column(name = "view_date", columnDefinition = "hemsirenin ekranda goruldu yaptigi zamani")
    private LocalDateTime viewDate;

    @Column(name = "doctor_name", columnDefinition = "ordera muhatap doktor adi")
    private String doctorName;

    @Column(name = "nurse_name", columnDefinition = "order olusturan hemsire")
    private String nurseName;

    @Column(name = "order_created_date", columnDefinition = "hemsirenin order olusturdugu zaman")
    private LocalDateTime orderCreatedDate;

    @Column(name = "patient_no", columnDefinition = "hasta numarasi")
    private String patientNo;

    @Column(name = "patient_name", columnDefinition = "hasta adi soyadi")
    private String patientName;

    @Column(name = "patient_birthdate", columnDefinition = "hasta dogum tarihi")
    private LocalDate patientBirthdate;

}
