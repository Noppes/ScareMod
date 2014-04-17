package noppes.scare.client;

import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler {

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSzzoundLoad(SoundLoadEvent event) {
		System.out.println("SOUND<<<<<<<<<<<<<<<<<<<<<");
		addSound("scare:spooky.ogg", event);
	}
	private void addSound(String sound, SoundLoadEvent event){
		try {
			event.manager.soundPoolSounds.addSound(sound);
		} catch (Exception e) {
			System.err.println("Failed to register one or more sounds.");
		}
	}
}