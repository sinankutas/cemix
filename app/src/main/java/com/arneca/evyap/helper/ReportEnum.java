package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

public enum ReportEnum {
    preStrackAmount("Önceki Fire Miktarı"),
    currStrackAmount("Anlık Makine Firesi"),
    prePorduct("Önceki Vardiya Üretim"),
    currPorduct("Anlık Net Üretim"),
    vardiyaOEE("Vardiya OEE"),
    product("Çalışılan Ürün"),
    currentStop("Mevcut Duruş"),
    totalStop("Duruş Süresi");
    private final String text;

    /**
     * @param text
     */
    ReportEnum(final String text) {
        this.text = text;
    }

    public static ReportEnum getModuleByType(String type) {
        for (ReportEnum module : ReportEnum.values()) {
            if (module.text.equals(type)) {
                return module;
            }
        }

        return null;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
    }
