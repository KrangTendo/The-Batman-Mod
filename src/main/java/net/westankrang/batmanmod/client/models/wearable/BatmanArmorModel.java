// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.westankrang.batmanmod.client.models.wearable;

import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class BatmanArmorModel extends EntityModel<LivingEntity> {
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart cape;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;

	public BatmanArmorModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.cape = root.getChild("cape");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(24, 0).cuboid(4.0F, -13.0F, 0.0F, 0.0F, 5.0F, 3.0F, new Dilation(0.0F))
		.uv(24, 0).mirrored().cuboid(-4.0F, -13.0F, 0.0F, 0.0F, 5.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cape = modelPartData.addChild("cape", ModelPartBuilder.create().uv(55, 16).cuboid(-4.5F, 0.3F, 0.1F, 9.0F, 22.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.3F, 2.0F));

		ModelPartData RightArm = modelPartData.addChild("RightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData LeftArm = modelPartData.addChild("LeftArm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData RightLeg = modelPartData.addChild("RightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

		ModelPartData LeftLeg = modelPartData.addChild("LeftLeg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		Body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		RightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		LeftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		RightLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		LeftLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	public void renderCape(MatrixStack stack, VertexConsumer consumer, AbstractClientPlayerEntity player, int light, float tickDelta) {
		stack.translate(0, 0, (player.isSneaking() ? -0.125 : -0.2));
		this.cape.render(stack, consumer, light, OverlayTexture.DEFAULT_UV);
	}

	public void renderCapeRotation(MatrixStack stack, VertexConsumer consumer, AbstractClientPlayerEntity player, int light, float tickDelta) {
		stack.push();
		stack.translate(0.0F, 0.0F, 0.125F);
		double d = MathHelper.lerp(tickDelta, player.prevCapeX, player.capeX) - MathHelper.lerp((double)tickDelta, player.prevX, player.getX());
		double e = MathHelper.lerp(tickDelta, player.prevCapeY, player.capeY) - MathHelper.lerp((double)tickDelta, player.prevY, player.getY());
		double m = MathHelper.lerp(tickDelta, player.prevCapeZ, player.capeZ) - MathHelper.lerp((double)tickDelta, player.prevZ, player.getZ());
		float n = MathHelper.lerpAngleDegrees(tickDelta, player.prevBodyYaw, player.bodyYaw);
		double o = (double)MathHelper.sin(n * ((float)Math.PI / 180F));
		double p = (double)(-MathHelper.cos(n * ((float)Math.PI / 180F)));
		float q = (float)e * 10.0F;
		q = MathHelper.clamp(q, -6.0F, 32.0F);
		float r = (float)(d * o + m * p) * 100.0F;
		r = MathHelper.clamp(r, 0.0F, 150.0F);
		float s = (float)(d * p - m * o) * 100.0F;
		s = MathHelper.clamp(s, -20.0F, 20.0F);
		if (r < 0.0F) {
			r = 0.0F;
		}

		float t = MathHelper.lerp(tickDelta, player.prevStrideDistance, player.strideDistance);
		q += MathHelper.sin(MathHelper.lerp(tickDelta, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;
		if (player.isInSneakingPose()) {
			q += 25.0F;
		}

		stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(6.0F + r / 2.0F + q));
		stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(s / 2.0F));
		stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - s / 2.0F));
		this.renderCape(stack, consumer, player, light, tickDelta);
		stack.pop();
	}
}