package org.dslofficial.dslmod.blocks;

import net.minecraft.world.level.block.Block;

import org.dslofficial.dslmod.objects.HorizontalDirectionalBlock;

public class GoldEmeralds {
    // 금빛 에메랄드 블록
    public static class GoldEmeraldBlock extends Block {
        public GoldEmeraldBlock(Properties pProperties) {
            super(pProperties);
        }
    }

    // 금빛 에메랄드 광석
    public static class GoldEmeraldOre extends HorizontalDirectionalBlock {
        public GoldEmeraldOre(Properties pProperties) {
            super(pProperties);
        }
    }
}

