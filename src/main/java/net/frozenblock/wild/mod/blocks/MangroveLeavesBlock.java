package net.frozenblock.wild.mod.blocks;

import net.frozenblock.wild.mod.registry.MangroveWoods;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MangroveLeavesBlock extends LeavesBlock {
    public MangroveLeavesBlock(Properties p_54422_) {
        super(p_54422_);
    }
}
