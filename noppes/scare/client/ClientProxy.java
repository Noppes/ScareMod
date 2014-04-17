package noppes.scare.client;

import cpw.mods.fml.common.FMLCommonHandler;
import noppes.scare.CommonProxy;
import noppes.scare.Scare;

public class ClientProxy extends CommonProxy{

	@Override
	public void load(){
		Scare.Channel.register(new ClientPacketHandler());
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
	}
}
