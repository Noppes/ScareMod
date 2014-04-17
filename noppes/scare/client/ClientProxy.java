package noppes.scare.client;

import net.minecraftforge.common.MinecraftForge;
import noppes.scare.CommonProxy;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{

	@Override
	public void load(){
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}
}
