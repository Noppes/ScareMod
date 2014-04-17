package noppes.scare.client;

import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class ClientPacketHandler implements Runnable{
	private float master, players;
	public static boolean playing = false;
	@SubscribeEvent
	public void onPacketData(ClientCustomPacketEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		ByteBufInputStream buf = new ByteBufInputStream(event.packet.payload());
		try {
			if(!playing){
				master = mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
				players = mc.gameSettings.getSoundLevel(SoundCategory.PLAYERS);
			}
			mc.gameSettings.setSoundLevel(SoundCategory.MASTER, 1);
			mc.gameSettings.setSoundLevel(SoundCategory.PLAYERS, 1);
			playing = true;
			player.playSound("scare:scare.spooky", 1, 1);
			//player.worldObj.playSoundAtEntity(player, sound,  1, 1);
			Thread thread = new Thread(this);
			thread.start();
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		mc.gameSettings.setSoundLevel(SoundCategory.MASTER, master);
		mc.gameSettings.setSoundLevel(SoundCategory.PLAYERS, players);
		playing = false;
	}
}
