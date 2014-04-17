package noppes.scare;

import java.util.List;
import java.util.Random;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;

public class CommandScare extends CommandBase{
	private Random random = new Random();
	@Override
	public String getCommandName() {
		return "scare";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/scare <player> [1-3]";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length < 1)
			return;
		EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(var2[0]);
		if(player == null){
			var1.sendChatToPlayer(ChatMessageComponent.createFromText("Unknown user"));
			return;
		}
		Server.sendData(player);
	}
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }
	
}
