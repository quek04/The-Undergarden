package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.RotlingEyesLayer;
import quek.undergarden.entity.rotspawn.Rotling;

public class RotlingRender extends MobRenderer<Rotling, RotlingModel<Rotling>> {

	public RotlingRender(EntityRendererProvider.Context renderContext) {
		super(renderContext, new RotlingModel<>(renderContext.bakeLayer(UGModelLayers.ROTLING)), 0.6F);
		this.addLayer(new RotlingEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Rotling entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/rotling.png");
	}
}