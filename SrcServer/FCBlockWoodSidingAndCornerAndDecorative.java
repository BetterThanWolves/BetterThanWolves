//FCMOD

package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockWoodSidingAndCornerAndDecorative extends FCBlockSidingAndCornerAndDecorative
{
	protected FCBlockWoodSidingAndCornerAndDecorative( int iBlockID, String sTextureName, String name )
	{
		super( iBlockID, FCBetterThanWolves.fcMaterialPlanks, sTextureName, 2F, 5F, Block.soundWoodFootstep, name );
    	
        SetAxesEffectiveOn( true );
        
        SetBuoyancy( 1.0F );
        
        SetFireProperties( 5, 20 );
	}
	
	@Override
    public int idDropped( int iMetadata, Random random, int iFortuneModifier )
    {
		if ( IsDecorativeFromMetadata( iMetadata ) )
		{
			return FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID;
		}			
		else if ( GetIsCorner( iMetadata ) )
		{
			return FCBetterThanWolves.fcBlockWoodCornerItemStubID;
		}
		else
		{
			return FCBetterThanWolves.fcBlockWoodSidingItemStubID;
		}		
    }
	
	@Override
    public int damageDropped( int iMetadata )
    {
	    int iWoodType = GetWoodTypeFromBlockID();
	    
		if ( IsDecorativeFromMetadata( iMetadata ) )
		{
    		int iBlockType;
    		
    	    if ( iMetadata == m_iSubtypeBench )
    	    {
    	    	iBlockType =  FCItemBlockWoodSidingDecorativeStub.m_iTypeBench;
    	    }
    	    else // fence
    	    {
    	    	iBlockType = FCItemBlockWoodSidingDecorativeStub.m_iTypeFence;
    	    }
    	    
    	    return FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType( iWoodType, iBlockType );			
		}
		else
		{		
			return iWoodType;
		}
    }
	
	@Override
    public boolean DoesBenchHaveLeg( IBlockAccess blockAccess, int i, int j, int k )
    {
    	if ( blockID == FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID )
    	{
        	int iBlockBelowID = blockAccess.getBlockId( i, j - 1, k );
        	
    		if ( iBlockBelowID == Block.fence.blockID )
    		{
    			return true;
    		}
    	}
    	
    	return super.DoesBenchHaveLeg( blockAccess, i, j, k );
    }
	
	@Override
    public int GetItemIDDroppedOnSaw( World world, int i, int j, int k )
    {
		int iSubtype = world.getBlockMetadata( i, j, k );
		
    	if ( iSubtype == m_iSubtypeBench )
    	{
    		return super.GetItemIDDroppedOnSaw( world, i, j, k );
    	}
    	else if ( iSubtype == m_iSubtypeFence )
    	{
    		return FCBetterThanWolves.fcBlockWoodCornerItemStubID;
    	}
    	
    	if ( GetIsCorner( world, i, j, k ) )
		{
    		return FCBetterThanWolves.fcItemGear.itemID;    		
		}
    	else
    	{
    		return FCBetterThanWolves.fcBlockWoodMouldingItemStubID;
    	}
    }
    
	@Override
    public int GetItemCountDroppedOnSaw( World world, int i, int j, int k )
    {
		if ( IsDecorative( world, i, j, k ) )
    	{
			int iSubtype = world.getBlockMetadata( i, j, k );
			
			if ( iSubtype == m_iSubtypeFence ) 
			{
				return 2;
			}
			else
			{
				return super.GetItemCountDroppedOnSaw( world, i, j, k );
			}
    	}
    	
    	return 2;
    }
    
	@Override
    public int GetItemDamageDroppedOnSaw( World world, int i, int j, int k )
    {
		if ( IsDecorative( world, i, j, k ) )
    	{
			int iSubtype = world.getBlockMetadata( i, j, k );
			
			if ( iSubtype == m_iSubtypeFence ) 
			{
				return GetWoodTypeFromBlockID();
			}
			else
			{
				return super.GetItemDamageDroppedOnSaw( world, i, j, k );
			}
    	}
    	
    	if ( GetIsCorner( world, i, j, k ) )
		{
    		return 0;
		}
    	else
    	{
    		return damageDropped( world.getBlockMetadata( i, j, k ) );
    	}
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
	
	protected int GetWoodTypeFromBlockID()
	{
	    int iWoodType;
	    
		if ( blockID == FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID )
		{
			iWoodType = 0;
		}
		else if ( blockID == FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID )
		{
			iWoodType = 1;
		}
		else if ( blockID == FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID )
		{
			iWoodType = 2;
		}
		else if ( blockID == FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID )
		{
			iWoodType = 3;
		}
		else // blood
		{
			iWoodType = 4;
		}
		
		return iWoodType;		
	}
	
	public int GetNumSawDustDroppedForType( int iMetadata )
	{
    	if ( this.IsDecorativeFromMetadata( iMetadata ) ||
    		!GetIsCorner( iMetadata ) )
    	{
    		return 2;
    	}
    	
		return 1; // corner
	}
	
	//----------- Client Side Functionality -----------//
}