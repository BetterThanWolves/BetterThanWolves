// FCMOD

package net.minecraft.src;
//import net.minecraft.client.Minecraft;

public abstract class FCAddOn
{
	protected FCAddOn()
	{
		FCAddOnHandler.AddMod( this );
	}
	
	public void PreInitialize()
	{
	}
	
	abstract public void Initialize();
	
	public void PostInitialize()
	{
	}
	
	public void OnLanguageLoaded( StringTranslate translator )
	{
	}	
	
	/**
	 * Prefix for custom addon-specific .lang files
	 * Returns null if addon doesn't support such files
	 */
	public String GetLanguageFilePrefix()
	{
		return null;
	}
	
    /*
     * Returns true if the packet has been processed, false otherwise
     */    
    public boolean ServerCustomPacketReceived( NetServerHandler handler, Packet250CustomPayload packet )
    {
    	return false;
    }
    
	//----------- Client Side Functionality -----------//
}
