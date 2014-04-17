package noppes.scare;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Scare.MODID, version = Scare.VERSION)
public class Scare
{
    public static final String MODID = "scare";
    public static final String VERSION = "1.7.2";
    public static FMLEventChannel Channel;

    @SidedProxy(clientSide = "noppes.scare.client.ClientProxy", serverSide = "noppes.scare.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event){
        Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("ScareMod");
    	proxy.load();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event){
    	event.registerServerCommand(new CommandScare());
    }
}
