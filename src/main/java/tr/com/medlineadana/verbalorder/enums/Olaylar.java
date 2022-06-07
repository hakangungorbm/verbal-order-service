package tr.com.medlineadana.verbalorder.enums;

import lombok.Getter;

@Getter
public enum Olaylar {
    ORDER("order"),
    TETKIK("tetkik"),

    PANIKDEGER("panikdeger");

    public String olayTipi;

    Olaylar(String olayTipi) {
        this.olayTipi = olayTipi;
    }
    //Not: Bu enum ı 2 fonksiyon ile kullanabiliriz:
    // 1. Olaylar.name() => bu ORDER seklinde enum degerinin adini verir
    // 2. Olaylar.ORDER.olayTuru() => buda "order" şeklinde enumun value sunu verir.
}
