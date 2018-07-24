
package com.github.tartaricacid.topnbt.config.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProbeInfoProvider {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("meta")
    @Expose
    private Integer meta;
    @SerializedName("border_color")
    @Expose
    private String borderColor;
    @SerializedName("spacing")
    @Expose
    private Integer spacing;
    @SerializedName("content")
    @Expose
    private Content content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Integer getSpacing() {
        return spacing;
    }

    public void setSpacing(Integer spacing) {
        this.spacing = spacing;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
