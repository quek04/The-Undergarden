package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotDwellerModel;
import quek.undergarden.client.render.layer.RotDwellerEyesLayer;
import quek.undergarden.entity.rotspawn.RotDwellerEntity;

@OnlyIn(Dist.CLIENT)
public class RotDwellerRender extends MobRenderer<RotDwellerEntity, RotDwellerModel<RotDwellerEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Undergarden.MODID, "textures/entity/rotdweller.png");

    public RotDwellerRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RotDwellerModel<>(), .7F);
        this.addLayer(new RotDwellerEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RotDwellerEntity entity) {
        return TEXTURE;
    }
}
