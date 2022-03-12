package com.hbacakk.kriptoparalar.model;

import com.google.gson.annotations.SerializedName;

public class ParaBirimi {
    @SerializedName("currency")
    String paraBirimi;
    @SerializedName("price")
    String fiyat;

    @Override
    public String toString() {
        return "ParaBirimi{" +
                "paraBirimi='" + paraBirimi + '\'' +
                ", fiyat='" + fiyat + '\'' +
                '}';
    }
}
