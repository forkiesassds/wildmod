package frozenblock.wild.mod.blocks.mangrove;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class MangroveLeaves extends LeavesBlock {
    public MangroveLeaves() {
        super(FabricBlockSettings
                .of(Material.LEAVES)
                .sounds(BlockSoundGroup.AZALEA)
                .nonOpaque()
                .strength(0.2f)
        );
    }
}

