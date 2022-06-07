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
            updatable = false
    )
    private LocalDateTime doctorApproveDate;

    private boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "olay", nullable = false)
    private Olaylar olay;

    @Column(name = "numara", nullable = false)
    @Size(min = 6, max = 8, message = "Kayit numarasi 6 ila 8 hane arasinda olmalidir!")
    private String numara;

    @Enumerated(EnumType.STRING)
    @Column(name = "onay_durumu", nullable = false)
    private OnayDurumlari onayDurumu;

    @Column(name = "gorulme_durumu", nullable = false)
    private boolean gorulmeDurumu;


    @Column(name = "view_date")
    private LocalDateTime viewDate;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "nurse_name")
    private String nurseName;

    @Column(name = "order_created_date")
    private LocalDateTime orderCreatedDate;

    @Column(name = "patient_no")
    private String patientNo;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_birthdate")
    private LocalDate patientBirthdate;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "floor_detail")
    private String floorDetail;

}
