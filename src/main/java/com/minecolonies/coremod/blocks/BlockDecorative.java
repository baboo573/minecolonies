package com.minecolonies.coremod.blocks;

import com.minecolonies.api.util.constant.Constants;
import com.minecolonies.coremod.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockDecorative extends Block
{
    public static final PropertyEnum<BlockDecorative.EnumType> VARIANT = PropertyEnum.<BlockDecorative.EnumType>create("variant", BlockDecorative.EnumType.class);

    /**
     * The hardness this block has.
     */
    private static final float BLOCK_HARDNESS = 0.0F;

    /**
     * This blocks name.
     */
    private static final String BLOCK_NAME = "blockDecorative";

    /**
     * The resistance this block has.
     */
    private static final float RESISTANCE = 1F;


    private void initBlock()
    {
        setRegistryName(BLOCK_NAME);
        setUnlocalizedName(String.format("%s.%s", Constants.MOD_ID.toLowerCase(), BLOCK_NAME));
        setCreativeTab(ModCreativeTabs.MINECOLONIES);
        GameRegistry.register((new com.minecolonies.coremod.blocks.BlockDecorative()).setRegistryName(this.getRegistryName()));
        setHardness(BLOCK_HARDNESS);
        setResistance(RESISTANCE);
    }

    public BlockDecorative()
    {
        super(Material.WOOD);
        initBlock();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(final IBlockState state)
    {
        return true;
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return ((BlockDecorative.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, ModCreativeTabs tab, List<ItemStack> list)
    {
        for (com.minecolonies.coremod.blocks.BlockDecorative.EnumType blockdecorative$enumtype : com.minecolonies.coremod.blocks.BlockDecorative.EnumType.values())
        {
            list.add(new ItemStack(itemIn, 1, blockdecorative$enumtype.getMetadata()));
        }
    }

    public enum EnumType implements IStringSerializable
    {
        TIMBER(0, "Timber", MapColor.WOOD),
        CROSSED(1, "Crossed", MapColor.OBSIDIAN);

        private static final com.minecolonies.coremod.blocks.BlockDecorative.EnumType[] META_LOOKUP = new com.minecolonies.coremod.blocks.BlockDecorative.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * The color which represents this entry on a map.
         */
        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static com.minecolonies.coremod.blocks.BlockDecorative.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (com.minecolonies.coremod.blocks.BlockDecorative.EnumType blockdecorative$enumtype : values())
            {
                META_LOOKUP[blockdecorative$enumtype.getMetadata()] = blockdecorative$enumtype;
            }
        }
    }
}