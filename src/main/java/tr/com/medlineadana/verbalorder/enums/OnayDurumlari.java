package tr.com.medlineadana.verbalorder.enums;

public enum OnayDurumlari {
    ONAYLANDI("onayliyorum"),

    GORULDU("goruldu"),
    RED_EDILDI("reddediyorum"),
    TABURCU("taburcu");

    public String onayCevabi;

    OnayDurumlari (String onayCevabi) {
        this.onayCevabi = onayCevabi;
    }
}
