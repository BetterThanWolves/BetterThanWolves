// FCMOD

package net.minecraft.src;

public class FCBlockDetectorLogicGlowing extends FCBlockDetectorLogic
{
    private final static float m_fLitFaceThickness = 0.01F;
	
    public FCBlockDetectorLogicGlowing( int iBlockID )
    {
        super( iBlockID );  
        
        setLightValue( 1F );     
        
        setUnlocalizedName( "fcBlockDetectorLogicGlowing" );        
        
        setCreativeTab( CreativeTabs.tabRedstone );
    }
    
    //------------- FCBlockDetectorLogic ------------//

	@Override
	protected void RemoveSelf( World world, int i, int j, int k )
	{
		// this function exists as the regular block shouldn't notify the client when it is removed, but the glowing variety should 
		
    	world.setBlock( i, j, k, 0, 0, 2 );        	
	}
    
    //------------- Class Specific Methods ------------//

	//----------- Client Side Functionality -----------//
}