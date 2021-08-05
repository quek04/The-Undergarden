package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.entity.ScintlingEntity;

public class ScintlingGlowLayer<T extends ScintlingEntity, M extends ScintlingModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/scintling_glow.png"));

    public ScintlingGlowLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return TEXTURE;
    }

}
