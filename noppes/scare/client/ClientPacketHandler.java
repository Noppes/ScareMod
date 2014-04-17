package noppes.scare.client;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.EnumOptions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements Runnable, IPacketHandler{
	private float master, players;
	public static boolean playing = false;


	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player p) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(!playing){
			master = mc.gameSettings.getOptionFloatValue(EnumOptions.SOUND);
		}
		mc.gameSettings.setOptionFloatValue(EnumOptions.SOUND, 1);
		playing = true;
		player.playSound("scare:spooky", 1, 1);
		//player.worldObj.playSoundAtEntity(player, "scare:scare.spooky",  1, 1);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Minecraft mc = Minecraft.getMinecraft();
		mc.gameSettings.setOptionFloatValue(EnumOptions.SOUND, master);
		playing = false;
	}
}
