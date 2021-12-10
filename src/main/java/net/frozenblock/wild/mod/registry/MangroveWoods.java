package net.frozenblock.wild.mod.registry;

import net.frozenblock.wild.mod.WildMod;
import net.frozenblock.wild.mod.blocks.MangroveLeavesBlock;
import net.frozenblock.wild.mod.blocks.MangrovePropaguleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MangroveWoods {

    public static final BlockBehaviour.Properties MANGROVE_SETTINGS = BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WildMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WildMod.MOD_ID);

    public static final RegistryObject<Block> MANGROVE_PLANKS = BLOCKS.register("mangrove_planks", () -> new Block(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_PLANKS_ITEM = ITEMS.register("mangrove_planks", () -> new BlockItem(MANGROVE_PLANKS.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_PROPAGULE = BLOCKS.register("mangrove_propagule", () -> new MangrovePropaguleBlock(MANGROVE_SETTINGS.noOcclusion().isViewBlocking(MangroveWoods::never)));
    public static final RegistryObject<Item> MANGROVE_PROPAGULE_ITEM = ITEMS.register("mangrove_propagule", () -> new BlockItem(MANGROVE_PROPAGULE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_LOG = BLOCKS.register("mangrove_log", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_LOG_ITEM = ITEMS.register("mangrove_log", () -> new BlockItem(MANGROVE_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD = BLOCKS.register("stripped_mangrove_wood", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> STRIPPED_MANGROVE_WOOD_ITEM = ITEMS.register("stripped_mangrove_wood", () -> new BlockItem(STRIPPED_MANGROVE_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG = BLOCKS.register("stripped_mangrove_log", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> STRIPPED_MANGROVE_LOG_ITEM = ITEMS.register("stripped_mangrove_log", () -> new BlockItem(STRIPPED_MANGROVE_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_WOOD = BLOCKS.register("mangrove_wood", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_WOOD_ITEM = ITEMS.register("mangrove_wood", () -> new BlockItem(MANGROVE_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_LEAVES = BLOCKS.register("mangrove_leaves", () -> new MangroveLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.AZALEA).strength(0.2f).noOcclusion().isViewBlocking(MangroveWoods::never)));
    public static final RegistryObject<Item> MANGROVE_LEAVES_ITEM = ITEMS.register("mangrove_leaves", () -> new BlockItem(MANGROVE_LEAVES.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));



    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static final void registerAll() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
