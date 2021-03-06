// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockComparator extends BlockComparator
{
    public FCBlockComparator(int par1, boolean par2)
    {
        super(par1, par2);
        this.isBlockContainer = false;
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    @Override
    protected int func_94481_j_(int par1)
    {
        return 0;
    }

    @Override
    public int getRenderType()
    {
        return 0;
    }


    protected boolean func_96470_c(int par1)
    {
        return false;
    }

    @Override
    protected int func_94480_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 0;
    }

    @Override
    public boolean func_94490_c(int par1)
    {
        return false;
    }

    @Override
    protected boolean func_94478_d(World par1World, int par2, int par3, int par4, int par5)
    {
    	return false;
    }

    @Override
    protected int getInputStrength(World par1World, int par2, int par3, int par4, int par5)
    {
    	return 0;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        return false;
    }

    @Override
    protected void func_94479_f(World par1World, int par2, int par3, int par4, int par5)
    {
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    }

    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	return false;
    }

    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return null;
    }
    
	//----------- Client Side Functionality -----------//
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        blockIcon = par1IconRegister.registerIcon( "fcBlockStub" );
    }

    @Override
    public Icon getIcon(int par1, int par2)
    {
    	return blockIcon;
    }

    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return 0;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
}
