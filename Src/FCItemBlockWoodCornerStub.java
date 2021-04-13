// FCMOD

package net.minecraft.src;

public class FCItemBlockWoodCornerStub extends ItemBlock
{
    public FCItemBlockWoodCornerStub( int iItemID )
    {
        super( iItemID );
        
        setHasSubtypes( true );        
    }
    
    @Override
    public int GetBlockIDToPlace( int iItemDamage, int iFacing, float fClickX, float fClickY, float fClickZ )
    {
    	switch ( iItemDamage )
    	{
    		case 0: // oak
    			
    			return FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID;
    			
    		case 1: // spruce
    			
    			return FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID;
    			
    		case 2: // birch
    			
    			return FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID;
    			
    		case 3: // jungle
    			
    			return FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID;
    			
    		default: // blood
    			
    			return FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID;    			
    	}
    }
    
    @Override
    public int getMetadata( int iItemDamage )
    {
        return 1;
    }
    
    @Override
    public String getUnlocalizedName( ItemStack itemstack )
    {
    	if( itemstack.getItemDamage() == 0 )
    	{
            return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append("oak").toString();
    	}
    	else if( itemstack.getItemDamage() == 1 )
    	{
            return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append("spruce").toString();
    	}
    	else if( itemstack.getItemDamage() == 2 )
    	{
            return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append("birch").toString();
    	}
    	else if( itemstack.getItemDamage() == 3 )
    	{
            return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append("jungle").toString();
    	}
    	else // 4
    	{
            return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append("blood").toString();
    	}
    }
    
    @Override
    public int GetFurnaceBurnTime( int iItemDamage )
    {
		return FCBlockPlanks.GetFurnaceBurnTimeByWoodType( iItemDamage ) / 8;
    }
}

