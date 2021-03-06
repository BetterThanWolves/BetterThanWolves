// FCMOD

package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockArcaneVessel extends FCBlockVessel
{
    public FCBlockArcaneVessel( int iBlockID )
    {
        super( iBlockID, Material.iron );
        
        setHardness( 3.5F );
        setResistance( 10F );
    	SetPicksEffectiveOn( true );
        
        setStepSound( soundMetalFootstep );
        
        setUnlocalizedName( "fcBlockArcaneVessel" );        
    }

	@Override
    public TileEntity createNewTileEntity( World world )
    {
        return new FCTileEntityArcaneVessel();
    }
    
	@Override
    public void breakBlock( World world, int i, int j, int k, int iBlockID, int iMetadata )
    {
        TileEntity tileEnt = world.getBlockTileEntity( i, j, k );
        
        if ( tileEnt != null && ( tileEnt instanceof FCTileEntityArcaneVessel ) )
        {
            FCTileEntityArcaneVessel vesselEnt = (FCTileEntityArcaneVessel)tileEnt;
            
            vesselEnt.EjectContentsOnBlockBreak();
        }

        super.breakBlock( world, i, j, k, iBlockID, iMetadata );
    }

	@Override
    public void onEntityCollidedWithBlock( World world, int i, int j, int k, Entity entity )
    {
		// don't collect items on the client, as it's dependent on the state of the inventory
		
		if ( !world.isRemote )
		{
			if ( entity instanceof EntityXPOrb )
			{
				OnEntityXPOrbCollidedWithBlock( world, i, j, k, (EntityXPOrb)entity );			
			}
		}		
    }
	
    private void OnEntityXPOrbCollidedWithBlock( World world, int i, int j, int k, EntityXPOrb entityXPOrb )
    {
        if ( entityXPOrb.isDead )
        {
        	return;
        }
        
        if ( GetMechanicallyPoweredFlag( world, i, j, k ) )
        {
        	// tilted blocks can't collect
        	
        	return;
        }
        
        // check if item is within the collection zone
    	
        final float fVesselHeight = 1F;
        
    	AxisAlignedBB collectionZone = AxisAlignedBB.getAABBPool().getAABB( (float)i, (float)j + fVesselHeight, (float)k, 
				(float)(i + 1), (float)j + fVesselHeight + 0.05F, (float)(k + 1) );
    	
    	if ( entityXPOrb.boundingBox.intersectsWith( collectionZone ) )
    	{    	
        	boolean bSwallowed = false;
        	
        	TileEntity tileEnt = world.getBlockTileEntity( i, j, k );
        	
        	if ( tileEnt != null && ( tileEnt instanceof FCTileEntityArcaneVessel ) )
        	{
	            FCTileEntityArcaneVessel vesselTileEnt = (FCTileEntityArcaneVessel)tileEnt;
	
	        	if ( vesselTileEnt.AttemptToSwallowXPOrb( world, i, j, k, entityXPOrb ) )
	        	{
			        world.playAuxSFX( FCBetterThanWolves.m_iItemCollectionPopSoundAuxFXID, i, j, k, 0 );							        
	        	}
        	}
    	}
    }
    
    //------------- Class Specific Methods -------------//
}