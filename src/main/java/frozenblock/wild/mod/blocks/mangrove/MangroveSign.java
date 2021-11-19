package frozenblock.wild.mod.blocks.mangrove;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.SignType;
import net.minecraft.util.math.BlockPos;

public class MangroveSign extends SignBlock implements BlockEntityProvider {
    public MangroveSign(Settings settings, SignType signType) {
        super(settings, signType);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SignBlockEntity(pos, state);
    }
}
