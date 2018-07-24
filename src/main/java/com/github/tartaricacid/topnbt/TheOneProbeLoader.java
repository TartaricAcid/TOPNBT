package com.github.tartaricacid.topnbt;

import com.github.tartaricacid.topnbt.config.CustomProbeInfoProvider;
import com.github.tartaricacid.topnbt.config.pojo.ProbeInfoProvider;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TheOneProbeLoader implements Function<ITheOneProbe, Void> {
    private List<ProbeInfoProvider> providerTotal = new ArrayList<>();

    @Nullable
    @Override
    public Void apply(ITheOneProbe theOneProbe) {
        providerTotal = readCustomConfig();
        for (ProbeInfoProvider provider : providerTotal) {
            if (provider != null) {
                theOneProbe.registerProvider(new CustomProbeInfoProvider(provider));
            }
        }
        return null;
    }

    private List<ProbeInfoProvider> readCustomConfig() {
        File configFile = new File(Minecraft.getMinecraft().gameDir.toString() + File.separator + "config" + File.separator + "TOPNBT.json");
        if (configFile.exists()) {
            try {
                Gson gson = new Gson();
                providerTotal = gson.fromJson(FileUtils.readFileToString(configFile, "utf-8"), new TypeToken<List<ProbeInfoProvider>>() {
                }.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return providerTotal;
    }
}
