package net.frozenblock.wild.mod.registry;

import net.frozenblock.wild.mod.WildMod;
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
    public static final RegistryObject<Item> MANGROVE_PROPAGULE_ITEM = ITEMS.register("mangrove_propagule", () -> new BlockItem(MANGROVE_PROPAGULE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Block> MANGROVE_LOG = BLOCKS.register("mangrove_log", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_LOG_ITEM = ITEMS.register("mangrove_log", () -> new BlockItem(MANGROVE_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD = BLOCKS.register("stripped_mangrove_wood", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> STRIPPED_MANGROVE_WOOD_ITEM = ITEMS.register("stripped_mangrove_wood", () -> new BlockItem(STRIPPED_MANGROVE_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG = BLOCKS.register("stripped_mangrove_log", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> STRIPPED_MANGROVE_LOG_ITEM = ITEMS.register("stripped_mangrove_log", () -> new BlockItem(STRIPPED_MANGROVE_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_WOOD = BLOCKS.register("mangrove_wood", () -> new RotatedPillarBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_WOOD_ITEM = ITEMS.register("mangrove_wood", () -> new BlockItem(MANGROVE_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_LEAVES = BLOCKS.register("mangrove_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.AZALEA).strength(0.2f).noOcclusion().isViewBlocking(MangroveWoods::never)));
    public static final RegistryObject<Item> MANGROVE_LEAVES_ITEM = ITEMS.register("mangrove_leaves", () -> new BlockItem(MANGROVE_LEAVES.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Block> MANGROVE_SLAB = BLOCKS.register("mangrove_slab", () -> new SlabBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_SLAB_ITEM = ITEMS.register("mangrove_slab", () -> new BlockItem(MANGROVE_SLAB.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_FENCE = BLOCKS.register("mangrove_fence", () -> new FenceBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_FENCE_ITEM = ITEMS.register("mangrove_fence", () -> new BlockItem(MANGROVE_FENCE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_STAIRS = BLOCKS.register("mangrove_stairs", () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(), MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_STAIRS_ITEM = ITEMS.register("mangrove_stairs", () -> new BlockItem(MANGROVE_STAIRS.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MANGROVE_BUTTON = BLOCKS.register("mangrove_button", () -> new WoodButtonBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_BUTTON_ITEM = ITEMS.register("mangrove_button", () -> new BlockItem(MANGROVE_BUTTON.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static final RegistryObject<Block> MANGROVE_PRESSURE_PLATE = BLOCKS.register("mangrove_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_PRESSURE_PLATE_ITEM = ITEMS.register("mangrove_pressure_plate", () -> new BlockItem(MANGROVE_PRESSURE_PLATE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static final RegistryObject<Block> MANGROVE_DOOR = BLOCKS.register("mangrove_door", () -> new DoorBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_DOOR_ITEM = ITEMS.register("mangrove_door", () -> new BlockItem(MANGROVE_DOOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static final RegistryObject<Block> MANGROVE_TRAPDOOR = BLOCKS.register("mangrove_trapdoor", () -> new TrapDoorBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_TRAPDOOR_ITEM = ITEMS.register("mangrove_trapdoor", () -> new BlockItem(MANGROVE_TRAPDOOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static final RegistryObject<Block> MANGROVE_FENCE_GATE = BLOCKS.register("mangrove_fence_gate", () -> new FenceGateBlock(MANGROVE_SETTINGS));
    public static final RegistryObject<Item> MANGROVE_FENCE_GATE_ITEM = ITEMS.register("mangrove_fence_gate", () -> new BlockItem(MANGROVE_FENCE_GATE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static final RegistryObject<Block> MANGROVE_ROOTS = BLOCKS.register("mangrove_roots", () -> new Block(MANGROVE_SETTINGS.noOcclusion().isViewBlocking(MangroveWoods::never)));
    public static final RegistryObject<Item> MANGROVE_ROOTS_ITEM = ITEMS.register("mangrove_roots", () -> new BlockItem(MANGROVE_ROOTS.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));




    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static final void registerAll() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
