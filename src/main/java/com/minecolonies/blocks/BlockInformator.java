package com.minecolonies.blocks;

import com.minecolonies.MineColonies;
import com.minecolonies.lib.Constants;
import com.minecolonies.util.CreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class BlockInformator extends Block implements IColoniesBlock, ITileEntityProvider
{
    protected int workingRange;

    @SideOnly(Side.CLIENT)
    private IIcon[] icons = new IIcon[6];// 0 = top, 1 = bot, 2-5 = sides;

    public BlockInformator(Material material)
    {
        super(material);
        setCreativeTab(CreativeTab.mineColoniesTab);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
    {
        if(world.isRemote)
            return true;
        entityPlayer.openGui(MineColonies.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(Constants.MODID.toLowerCase() + ":" + getName() + "top");
        icons[1] = iconRegister.registerIcon(Constants.MODID.toLowerCase() + ":" + getName() + "bot");
        for(int i = 2; i <= 5; i++)
        {
            icons[i] = iconRegister.registerIcon(Constants.MODID.toLowerCase() + ":" + "sideChest");
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icons[side];
    }
}