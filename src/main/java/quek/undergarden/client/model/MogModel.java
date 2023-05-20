package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.animal.Mog;

public class MogModel<E extends Mog> extends AgeableListModel<E> {

	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart backLegRight;
	private final ModelPart backLegLeft;
	private final ModelPart head;
	private final ModelPart body;

	public MogModel(ModelPart root) {
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.frontLegRight = root.getChild("frontLegRight");
		this.backLegRight = root.getChild("backLegRight");
		this.backLegLeft = root.getChild("backLegLeft");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(32, 36).mirror().addBox(0.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 20.0F, -2.0F));

		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(20, 36).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 20.0F, -2.0F));

		PartDefinition backLegRight = partdefinition.addOrReplaceChild("backLegRight", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 20.0F, 2.0F));

		PartDefinition backLegLeft = partdefinition.addOrReplaceChild("backLegLeft", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(4.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 20.0F, 2.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 26).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, -6.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, -21.0F, -4.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -17.0F, -6.0F, 12.0F, 14.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 26).addBox(-4.0F, -19.0F, -3.0F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.head, this.body, this.frontLegLeft, this.frontLegRight, this.backLegLeft, this.backLegRight);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		this.body.zRot = 0.1F * Mth.sin(limbSwing * 2.0F) * 4.0F * limbSwingAmount;

		this.frontLegLeft.xRot = Mth.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
		this.frontLegRight.xRot = Mth.cos(limbSwing * 2.0F + (float) Math.PI) * 4.0F * limbSwingAmount;
		this.backLegLeft.xRot = Mth.cos(limbSwing * 2.0F + (float) Math.PI) * 4.0F * limbSwingAmount;
		this.backLegRight.xRot = Mth.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
	}
}