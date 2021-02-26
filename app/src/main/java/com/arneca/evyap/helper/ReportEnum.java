package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

public enum ReportEnum {
    preStrackAmount("Önceki Fire Miktarı1"),
    currStrackAmount("Anlık Makine Firesi1"),
    prePorduct("Önceki Vardiya Üretim1"),
    currPorduct("Anlık Net Üretim2"),
    vardiyaOEE("Vardiya OE3E"),
    product("Çalışılan Ürün4"),
    currentStop("Mevcut Duru34ş"),
    totalStop("Duruş Süres54i");
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
