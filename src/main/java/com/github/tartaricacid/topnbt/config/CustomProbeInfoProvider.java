package com.github.tartaricacid.topnbt.config;

import com.github.tartaricacid.topnbt.config.pojo.*;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomProbeInfoProvider implements IProbeInfoProvider {
    private ProbeInfoProvider provider;

    public CustomProbeInfoProvider(ProbeInfoProvider provider) {
        this.provider = provider;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        if (blockState.getBlock() == Block.getBlockFromName(provider.getId()) && blockState.getBlock().getMetaFromState(blockState) == provider.getMeta()) {
            Content content = provider.getContent();
            for (Normal normal : content.getNormal()) {
                addNormalInfo(normal, probeInfo, world, data);
            }
            for (Extended extended : content.getExtended()) {
                addExtendedInfo(extended, probeInfo, world, data);
            }
            for (Debug debug : content.getDebug()) {
                addDebugInfo(debug, probeInfo, world, data);
            }
        }
    }

    @Override
    public String getID() {
        return provider.getId();
    }

    private void addNormalInfo(Normal normal, IProbeInfo probeInfo, World world, IProbeHitData data) {
        if (normal.getType().equals("text")) {
            probeInfo.text(parseString(world, data, normal.getString()));
        }
    }

    private void addExtendedInfo(Extended extended, IProbeInfo probeInfo, World world, IProbeHitData data) {
        if (extended.getType().equals("text")) {
            probeInfo.text(parseString(world, data, extended.getString()));
        }
    }

    private void addDebugInfo(Debug debug, IProbeInfo probeInfo, World world, IProbeHitData data) {
        if (debug.getType().equals("text")) {
            probeInfo.text(parseString(world, data, debug.getString()));
        }
    }

    /**
     * Convert a placeholder into a string;
     * Placeholder style: ${info.getPos.x}
     */
    private String parseString(World world, IProbeHitData data, String string) {
        Matcher m = Pattern.compile("\\$\\{(.*?)\\}").matcher(string);
        while (m.find()) {
            String str = m.group(1);
            List<String> nbt = Arrays.asList(str.split("\\."));
            String result = getNBTFromBlock(world, data, nbt);
            if (result != null) {
                string = string.replace(String.format("${%s}", str), result);
            }
        }
        return string;
    }

    /**
     * Convert List<String> to string from NBT;
     */
    private String getNBTFromBlock(World world, IProbeHitData data, List<String> nbt) {
        // Check is null?
        if (nbt == null) {
            return null;
        }

        // Start get tileEntity
        TileEntity tileEntity = world.getTileEntity(data.getPos());
        if (tileEntity != null) {
            // Get NBT id
            int id = tileEntity.getTileData().getTag(nbt.get(0)).getId();

            // Start to judge
            switch (id) {
                case 1: // Byte
                    Integer b = tileEntity.getTileData().getByte(nbt.get(0)) & 0xff;
                    return b.toString();
                case 2: // Short
                    Short s = tileEntity.getTileData().getShort(nbt.get(0));
                    return s.toString();
                case 3: // Integer
                    Integer i = tileEntity.getTileData().getInteger(nbt.get(0));
                    return i.toString();
                case 4: // Long
                    Long l = tileEntity.getTileData().getLong(nbt.get(0));
                    return l.toString();
                case 5: // Float
                    Float f = tileEntity.getTileData().getFloat(nbt.get(0));
                    return f.toString();
                case 6: // Double
                    Double d = tileEntity.getTileData().getDouble(nbt.get(0));
                    return d.toString();
                case 7: // List<Byte>
                    if (nbt.size() > 1) {
                        return getNBTFromBlock(world, data, nbt.subList(1, nbt.size()));
                    } else {
                        return null;
                    }
                case 8: // String
                    return tileEntity.getTileData().getString(nbt.get(0));
                case 9: // List<>
                    if (nbt.size() > 1) {
                        return getNBTFromBlock(world, data, nbt.subList(1, nbt.size()));
                    } else {
                        return null;
                    }
                case 10: // NBT
                    if (nbt.size() > 1) {
                        return getNBTFromBlock(world, data, nbt.subList(1, nbt.size()));
                    } else {
                        return null;
                    }
                case 11: // List<Integer>
                    if (nbt.size() > 1) {
                        return getNBTFromBlock(world, data, nbt.subList(1, nbt.size()));
                    } else {
                        return null;
                    }
                case 12: // List<Long>
                    if (nbt.size() > 1) {
                        return getNBTFromBlock(world, data, nbt.subList(1, nbt.size()));
                    } else {
                        return null;
                    }
            }
        }
        return null;
    }
}
