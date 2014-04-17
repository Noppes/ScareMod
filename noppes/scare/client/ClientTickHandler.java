package noppes.scare.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler{
    protected static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("scare","textures/spooky.png");

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

    protected void renderPumpkinBlur()
    {
		Minecraft mc = Minecraft.getMinecraft();

        ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)height, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)width, (double)height, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)width, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

	@Override
	public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.RENDER);
	}

	
	@Override
	public String getLabel() {
		return "Scare ClientTickHandler";
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(ClientPacketHandler.playing && type.contains(TickType.RENDER)){
			renderPumpkinBlur();
		}
	}

}
