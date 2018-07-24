
package com.github.tartaricacid.topnbt.config.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Debug {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("string")
    @Expose
    private String string;
    @SerializedName("percent")
    @Expose
    private String percent;
    @SerializedName("progress_style")
    @Expose
    private ProgressStyle progressStyle;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public ProgressStyle getProgressStyle() {
        return progressStyle;
    }

    public void setProgressStyle(ProgressStyle progressStyle) {
        this.progressStyle = progressStyle;
    }

}
