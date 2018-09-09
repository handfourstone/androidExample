package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {

    // 由于 JSON 中的一些字段可能不太适合直接作为 Java 字段来命名，
    // 因此，这里使用了 @SerializedName 注释的方式让 JSON 字段和
    // java 字段之间建立联系。

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
