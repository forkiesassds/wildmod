package net.frozenblock.wild.mod.registry;

import net.frozenblock.wild.mod.WildMod;
import net.frozenblock.wild.mod.blocks.SculkCatalystBlock;
import net.frozenblock.wild.mod.blocks.SculkShriekerBlock;
import net.frozenblock.wild.mod.blocks.SculkVeinBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
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
import org.intellij.lang.annotations.Identifier;


public class RegisterBlocks {

    public static final BlockBehaviour.Properties MUD_BRICK_SETTINGS = BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_BRICKS);
    public static final BlockBehaviour.Properties SCULK_SETTINGS = BlockBehaviour.Properties.of(Material.SCULK).sound(SoundType.SCULK_SENSOR);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WildMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WildMod.MOD_ID);

    public static final RegistryObject<Block> MUD_BLOCK = BLOCKS.register("mud_block", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.TERRACOTTA_GRAY).strength(0.2F, 0.2f).sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Item> MUD_BLOCK_ITEM = ITEMS.register("mud_block", () -> new BlockItem(MUD_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MUD_BRICKS = BLOCKS.register("mud_bricks", () -> new Block(MUD_BRICK_SETTINGS));
    public static final RegistryObject<Item> MUD_BRICKS_ITEM = ITEMS.register("mud_bricks", () -> new BlockItem(MUD_BRICKS.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MUD_BRICKS_WALL = BLOCKS.register("mud_brick_wall", () -> new WallBlock(MUD_BRICK_SETTINGS));
    public static final RegistryObject<Item> MUD_BRICKS_WALL_ITEM = ITEMS.register("mud_brick_wall", () -> new BlockItem(MUD_BRICKS_WALL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MUD_BRICKS_SLAB = BLOCKS.register("mud_brick_slab", () -> new SlabBlock(MUD_BRICK_SETTINGS));
    public static final RegistryObject<Item> MUD_BRICKS_SLAB_ITEM = ITEMS.register("mud_brick_slab", () -> new BlockItem(MUD_BRICKS_SLAB.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> MUD_BRICKS_STAIRS = BLOCKS.register("mud_brick_stairs", () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(), MUD_BRICK_SETTINGS));
    public static final RegistryObject<Item> MUD_BRICKS_STAIRS_ITEM = ITEMS.register("mud_brick_stairs", () -> new BlockItem(MUD_BRICKS_STAIRS.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> SCULK = BLOCKS.register("sculk", () -> new Block(SCULK_SETTINGS));
    public static final RegistryObject<Item> SCULK_ITEM = ITEMS.register("sculk", () -> new BlockItem(SCULK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> SCULK_VEIN = BLOCKS.register("sculk_vein", () -> new SculkVeinBlock(SCULK_SETTINGS.noOcclusion().isViewBlocking(RegisterBlocks::never)));
    public static final RegistryObject<Item> SCULK_VEIN_ITEM = ITEMS.register("sculk_vein", () -> new BlockItem(SCULK_VEIN.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> SCULK_CATALYST = BLOCKS.register("sculk_catalyst", () -> new SculkCatalystBlock(SCULK_SETTINGS));
    public static final RegistryObject<Item> SCULK_CATALYST_ITEM = ITEMS.register("sculk_catalyst", () -> new BlockItem(SCULK_CATALYST.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> SCULK_SHRIEKER = BLOCKS.register("sculk_shrieker", () -> new SculkShriekerBlock(SCULK_SETTINGS.noOcclusion().isViewBlocking(RegisterBlocks::never)));
    public static final RegistryObject<Item> SCULK_SHRIEKER_ITEM = ITEMS.register("sculk_shrieker", () -> new BlockItem(SCULK_SHRIEKER.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> DEEPSLATE_FRAME = BLOCKS.register("deepslate_frame", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Item> DEEPSLATE_FRAME_ITEM = ITEMS.register("deepslate_frame", () -> new BlockItem(DEEPSLATE_FRAME.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static final void registerAll() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MangroveWoods.registerAll();
    }

}
