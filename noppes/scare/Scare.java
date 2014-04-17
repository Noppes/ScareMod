package noppes.scare;

import noppes.scare.client.ClientPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;


@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { "Scare" },
clientPacketHandlerSpec = @SidedPacketHandler(channels = { "Scare" }, packetHandler = ClientPacketHandler.class),
versionBounds = "[1.6.4]"
)
@Mod(modid = Scare.MODID, version = Scare.VERSION)
public class Scare
{
    public static final String MODID = "scare";
    public static final String VERSION = "1.6.4";

    @SidedProxy(clientSide = "noppes.scare.client.ClientProxy", serverSide = "noppes.scare.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLPreInitializationEvent event){
    	proxy.load();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event){
    	event.registerServerCommand(new CommandScare());
    }
}
