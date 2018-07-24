
package com.github.tartaricacid.topnbt.config.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("normal")
    @Expose
    private List<Normal> normal = null;
    @SerializedName("extended")
    @Expose
    private List<Extended> extended = null;
    @SerializedName("debug")
    @Expose
    private List<Debug> debug = null;

    public List<Normal> getNormal() {
        return normal;
    }

    public void setNormal(List<Normal> normal) {
        this.normal = normal;
    }

    public List<Extended> getExtended() {
        return extended;
    }

    public void setExtended(List<Extended> extended) {
        this.extended = extended;
    }

    public List<Debug> getDebug() {
        return debug;
    }

    public void setDebug(List<Debug> debug) {
        this.debug = debug;
    }

}
