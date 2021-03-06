// FCMOD

package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockWoodMouldingAndDecorative extends FCBlockMouldingAndDecorative
{
    public final static int m_iOakTableTopTextureID = 93;
    public final static int m_iOakTableLegTextureID = 94;
    
	protected FCBlockWoodMouldingAndDecorative( int iBlockID, String sTextureName, String sColumnSideTextureName, int iMatchingCornerBlockID, String name )
	{
		super( iBlockID,  FCBetterThanWolves.fcMaterialPlanks, 
			sTextureName, sColumnSideTextureName, iMatchingCornerBlockID, 
			2F, 5F, Block.soundWoodFootstep, name );		
    	
        SetAxesEffectiveOn( true );        
        
        SetBuoyancy( 1F );
    	SetFurnaceBurnTime( FCEnumFurnaceBurnTime.PLANKS_OAK.m_iBurnTime / 4 );
        
        SetFireProperties( 5, 20 );
	}
	
	@Override
    public int idDropped( int iMetadata, Random random, int iFortuneModifier )
    {
		if ( IsDecorative( iMetadata ) )
		{
			return FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID;
		}
		
		return FCBetterThanWolves.fcBlockWoodMouldingItemStubID;
    }
	
	@Override
    public int damageDropped( int iMetadata )
    {
		int iWoodType;
		
		if ( blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID )
		{
			iWoodType = 0;
		}
		else if ( blockID == FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID )
		{
			iWoodType = 1;
		}
		else if ( blockID == FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID )
		{
			iWoodType = 2;
		}
		else if ( blockID == FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockID )
		{
			iWoodType = 3;
		}
		else  // blood
		{
			iWoodType = 4;
		}
		
    	if ( !IsDecorative( iMetadata ) )
    	{
    		return iWoodType;
    	}
    	
		int iBlockType;
		
	    if ( iMetadata == m_iSubtypeColumn )
	    {
	    	iBlockType =  FCItemBlockWoodMouldingDecorativeStub.m_iTypeColumn;
	    }
	    else if ( iMetadata == m_iSubtypePedestalUp || iMetadata == m_iSubtypePedestalDown )
	    {
	    	iBlockType = FCItemBlockWoodMouldingDecorativeStub.m_iTypePedestal;
	    }
	    else // table
	    {
	    	iBlockType = FCItemBlockWoodMouldingDecorativeStub.m_iTypeTable;
	    }
	    
	    return FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType( iWoodType, iBlockType );		
    }
	
	@Override
    public int GetItemIDDroppedOnSaw( World world, int i, int j, int k )
    {
    	if ( IsDecorative( world, i, j, k ) )
    	{
    		return super.GetItemIDDroppedOnSaw( world, i, j, k );
    	}
    	
    	return FCBetterThanWolves.fcBlockWoodCornerItemStubID;
	}
    
	@Override
    public int GetItemCountDroppedOnSaw( World world, int i, int j, int k )
    {
    	if ( IsDecorative( world, i, j, k ) )
    	{
    		return super.GetItemCountDroppedOnSaw( world, i, j, k );
    	}
    	
    	return 2;
    }
    
	@Override
    public int GetItemDamageDroppedOnSaw( World world, int i, int j, int k )
    {
		int iMetadata = world.getBlockMetadata( i, j, k );
		
    	if ( IsDecorative( iMetadata ) )
    	{
    		return super.GetItemDamageDroppedOnSaw( world, i, j, k );
    	}
    	
		return damageDropped( iMetadata );
    }
	
	@Override
    public boolean DoesTableHaveLeg( IBlockAccess blockAccess, int i, int j, int k )
    {
    	if ( blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID )
    	{
        	int iBlockBelowID = blockAccess.getBlockId( i, j - 1, k );
        	
    		if ( iBlockBelowID == Block.fence.blockID )
    		{
    			return true;
    		}
    	}
    	
    	return super.DoesTableHaveLeg( blockAccess, i, j, k );
    }
	
    @Override
    public int GetHarvestToolLevel( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return 2; // iron or better
    }
    
    @Override
    public void OnBlockDestroyedWithImproperTool( World world, EntityPlayer player, int i, int j, int k, int iMetadata )
    {
        world.playAuxSFX( FCBetterThanWolves.m_iWoodBlockDestroyedAuxFXID, i, j, k, 0 );

		int iNumDropped =  GetNumSawDustDroppedForType( iMetadata );
		
		DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcItemSawDust.itemID, iNumDropped, 0, 1F );
    }
		
	@Override
    public boolean canDropFromExplosion( Explosion explosion )
    {
        return false;
    }
    
	@Override
    public void onBlockDestroyedByExplosion( World world, int i, int j, int k, Explosion explosion )
    {
		float fChanceOfPileDrop = 1.0F;
		
		if ( explosion != null )
		{
			fChanceOfPileDrop = 1.0F / explosion.explosionSize;
		}
		
		int iNumDropped =  GetNumSawDustDroppedForType( world.getBlockMetadata( i, j, k ) );
		
		DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcItemSawDust.itemID, iNumDropped, 0, fChanceOfPileDrop );
    }
	
    //------------- Class Specific Methods ------------//
	
	public int GetNumSawDustDroppedForType( int iMetadata )
	{
    	if ( IsDecorative( iMetadata ) )
    	{
    		return 2;
    	}
    	
		return 1; // moulding
	}
	
	//----------- Client Side Functionality -----------//
}
