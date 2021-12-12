package net.frozenblock.wild.mod;

import net.frozenblock.wild.mod.registry.MangroveWoods;
import net.frozenblock.wild.mod.registry.RegisterBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("twm")
public class WildMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "twm";

    public WildMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::WildModClient);

        MinecraftForge.EVENT_BUS.register(this);

        RegisterBlocks.registerAll();
    }

    private void WildModClient(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.SCULK_VEIN.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.SCULK_SHRIEKER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MangroveWoods.MANGROVE_PROPAGULE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MangroveWoods.MANGROVE_LEAVES.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MangroveWoods.MANGROVE_ROOTS.get(), RenderType.cutout());
        });
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerBlockColors(ColorHandlerEvent.Block event) {
            event.getBlockColors().register((p_92626_, p_92627_, p_92628_, p_92629_) -> {
                return p_92627_ != null && p_92628_ != null ? BiomeColors.getAverageFoliageColor(p_92627_, p_92628_) : FoliageColor.getDefaultColor();
            }, MangroveWoods.MANGROVE_LEAVES.get());
        }

        @SubscribeEvent
        public static void registerItemColors(ColorHandlerEvent.Item event) {
            event.getItemColors().register((p_92687_, p_92688_) -> {
                return FoliageColor.getDefaultColor();
            }, MangroveWoods.MANGROVE_LEAVES.get());
        }
    }
}
