package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwiblingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.GwiblingEyesLayer;
import quek.undergarden.entity.animal.Gwibling;

public class GwiblingRender extends MobRenderer<Gwibling, GwiblingModel<Gwibling>> {

	public GwiblingRender(EntityRendererProvider.Context context) {
		super(context, new GwiblingModel<>(context.bakeLayer(UGModelLayers.GWIBLING)), 0.3F);
		this.addLayer(new GwiblingEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Gwibling entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/gwibling.png");
	}

	@Override
	protected void setupRotations(Gwibling entity, PoseStack stack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entity, stack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		stack.mulPose(Axis.YP.rotationDegrees(f));
		if (!entity.isInWater()) {
			stack.translate(0.1F, 0.1F, -0.1F);
			stack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}