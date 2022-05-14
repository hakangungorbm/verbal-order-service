package tr.com.medlineadana.verbalorder.enums;

public enum OnayDurumlari {
    ONAYLANDI("onayliyorum"),
    RED_EDILDI("reddediyorum");

    public String onayCevabi;

    OnayDurumlari (String onayCevabi) {
        this.onayCevabi = onayCevabi;
    }
}
