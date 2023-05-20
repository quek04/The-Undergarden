package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.StonebornEyesLayer;
import quek.undergarden.entity.stoneborn.Stoneborn;

public class StonebornRender extends MobRenderer<Stoneborn, StonebornModel<Stoneborn>> {

	public StonebornRender(EntityRendererProvider.Context renderContext) {
		super(renderContext, new StonebornModel<>(renderContext.bakeLayer(UGModelLayers.STONEBORN)), 0.6F);
		this.addLayer(new StonebornEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Stoneborn entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/stoneborn.png");
	}

	@Override
	protected boolean isShaking(Stoneborn stoneborn) {
		return !stoneborn.inUndergarden();
	}
}