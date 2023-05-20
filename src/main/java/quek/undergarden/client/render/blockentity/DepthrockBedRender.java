package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.registry.UGBlockEntities;

public class DepthrockBedRender implements BlockEntityRenderer<DepthrockBedBlockEntity> {

	private final ModelPart headPiece;
	private final ModelPart footPiece;

	public DepthrockBedRender(BlockEntityRendererProvider.Context renderContext) {
		this.headPiece = renderContext.bakeLayer(UGModelLayers.DEPTHROCK_BED_HEAD);
		this.footPiece = renderContext.bakeLayer(UGModelLayers.DEPTHROCK_BED_FOOT);
	}

	public static LayerDefinition createHeadLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F), PartPose.ZERO);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	public static LayerDefinition createFootLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F), PartPose.ZERO);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void render(DepthrockBedBlockEntity bed, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Level level = bed.getLevel();
		if (level != null) {
			BlockState blockstate = bed.getBlockState();
			DoubleBlockCombiner.NeighborCombineResult<? extends DepthrockBedBlockEntity> neighborCombineResult = DoubleBlockCombiner.combineWithNeigbour(UGBlockEntities.DEPTHROCK_BED.get(), BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, level, bed.getBlockPos(), (levelAccessor, pos) -> false);
			int i = neighborCombineResult.<Int2IntFunction>apply(new BrightnessCombiner<>()).get(combinedLight);
			this.renderPiece(poseStack, buffer, blockstate.getValue(BedBlock.PART) == BedPart.HEAD ? this.headPiece : this.footPiece, blockstate.getValue(BedBlock.FACING), i, combinedOverlay, false);
		} else {
			this.renderPiece(poseStack, buffer, this.headPiece, Direction.SOUTH, combinedLight, combinedOverlay, false);
			this.renderPiece(poseStack, buffer, this.footPiece, Direction.SOUTH, combinedLight, combinedOverlay, true);
		}
	}

	private void renderPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, ModelPart bedPart, Direction direction, int combinedLight, int combinedOverlay, boolean isBedFoot) {
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.5625D, isBedFoot ? -1.0D : 0.0D);
		poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
		poseStack.translate(0.5D, 0.5D, 0.5D);
		poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
		poseStack.translate(-0.5D, -0.5D, -0.5D);
		VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entitySolid(new ResourceLocation(Undergarden.MODID, "textures/block/depthrock_bed.png")));
		bedPart.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);
		poseStack.popPose();
	}
}