package noppes.scare;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class Server {


	public static void sendData(EntityPlayer player, Object... obs) {
        PacketDispatcher.sendPacketToPlayer(getPacket(obs),(Player) player);
	}
	
	public static DataOutputStream getDataOutputStream(ByteArrayOutputStream stream) throws IOException{
        return new DataOutputStream(new GZIPOutputStream(stream));
	}

	
	public static Packet250CustomPayload getPacket(Object... obs){
		try {
		    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream out = getDataOutputStream(bytes);
			for(Object ob : obs){
				if(ob == null)
					continue;
				if(ob instanceof Map){
					Map<String,Integer> map = (Map<String, Integer>) ob;
					for(String key : map.keySet()){
						int value = map.get(key);
						out.writeInt(value);
						out.writeUTF(key);
					}
				}
				else if(ob instanceof Enum)
					out.writeInt(((Enum<?>) ob).ordinal());
				else if(ob instanceof Double)
					out.writeDouble((Double) ob);
				else if(ob instanceof Float)
					out.writeFloat((Float) ob);
				else if(ob instanceof Integer)
					out.writeInt((Integer) ob);
				else if(ob instanceof String)
					out.writeUTF((String) ob);
				else if(ob instanceof NBTTagCompound)
					CompressedStreamTools.write((NBTTagCompound) ob, out);
				else if(ob instanceof MerchantRecipeList)
					((MerchantRecipeList)ob).writeRecipiesToStream(out);
				
			}
			out.close();
			bytes.close();
	        return new Packet250CustomPayload("Scare",bytes.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
