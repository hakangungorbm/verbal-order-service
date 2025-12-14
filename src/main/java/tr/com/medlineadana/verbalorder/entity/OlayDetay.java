package tr.com.medlineadana.verbalorder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.medlineadana.verbalorder.enums.Olaylar;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="olay_detay")
public class OlayDetay {
    private static final long serialVersionUID = 8970281518406996101L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "islem_tarihi")
    private LocalDate islemTarihi;

    @Column(name = "islem_adi")
    private String islemAdi;

    @Column(name = "sonuc")
    private String sonuc;

    @Column(name = "frekans_deger")
    private String frekansDeger;

    private boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "olay", nullable = false)
    private Olaylar olay;

    @Column(name = "numara", nullable = false)
    @Size(min = 6, max = 9, message = "Olay numarasi 6 ila 9 hane arasinda olmalidir!")
    private String numara;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
