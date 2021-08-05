package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MogModel;
import quek.undergarden.client.render.layer.MogEyesLayer;
import quek.undergarden.entity.MogEntity;

@OnlyIn(Dist.CLIENT)
public class MogRender extends MobRenderer<MogEntity, MogModel<MogEntity>> {

    public MogRender(EntityRendererManager rendererManager) {
        super(rendererManager, new MogModel<>(), 0.5F);
        this.addLayer(new MogEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MogEntity entity) {
        if(entity.hasMoss()) {
            return new ResourceLocation(Undergarden.MODID, "textures/entity/mog.png");
        }
        return new ResourceLocation(Undergarden.MODID, "textures/entity/mog_naked.png");
    }
}
