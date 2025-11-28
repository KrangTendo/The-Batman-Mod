package net.westankrang.batmanmod.client.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.westankrang.batmanmod.BatmanMod;
import net.westankrang.batmanmod.client.models.wearable.BatmanArmorModel;
import net.westankrang.batmanmod.main.items.BatmanArmorItem;

@Environment(value = EnvType.CLIENT)
public class BatmanArmorFeatureRenderer<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms>
        extends
        FeatureRenderer<T, M> {

    public String color = "black";
    private final Identifier texture;
    private final BatmanArmorModel model;

    public BatmanArmorFeatureRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.model = new BatmanArmorModel(BatmanArmorModel.getTexturedModelData().createModel());
        this.texture = BatmanMod.id("textures/entity/wearables/batman_armor/" + color + ".png");
    }

    public static Identifier getTexture(String color) {
        return BatmanMod.id("textures/entity/wearables/batman_armor/" + color + ".png");
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity,
                       float f, float g, float h, float j, float k, float l) {

        if (!(livingEntity instanceof AbstractClientPlayerEntity || livingEntity instanceof ArmorStandEntity))
            return;

        matrixStack.push();
        matrixStack.translate(0, 0, 0);

        for (BodyParts part : BodyParts.values()) {
            ItemStack stack = getModelForSlot(livingEntity, part);
            if (stack.getItem() instanceof BatmanArmorItem item) {
                this.color = item.getColor();
                enablePart(model, part);

                if (part == BodyParts.CHEST && livingEntity instanceof AbstractClientPlayerEntity player) {
                    model.renderCapeRotation(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(getTexture(color))), player, i, h);
                }

            } else {
                disablePart(model, part);
            }
        }


        this.model.Head.copyTransform(((BipedEntityModel) getContextModel()).head);
        this.model.Body.copyTransform(((BipedEntityModel) getContextModel()).body);
        this.model.LeftArm.copyTransform(((BipedEntityModel) getContextModel()).leftArm);
        this.model.RightArm.copyTransform(((BipedEntityModel) getContextModel()).rightArm);
        this.model.LeftLeg.copyTransform(((BipedEntityModel) getContextModel()).leftLeg);
        this.model.RightLeg.copyTransform(((BipedEntityModel) getContextModel()).rightLeg);

        this.model.setAngles(livingEntity, f, g, j, k, l);

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCullZOffset(getTexture(color)));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1f);

        matrixStack.pop();
    }

    public static void enablePart(BatmanArmorModel model, BodyParts part) {
        switch (part) {
            case HEAD:
                model.Head.visible = true;
                break;
            case CHEST:
                model.Body.visible = true;
                model.LeftArm.visible = true;
                model.RightArm.visible = true;
                break;
            case LEGS, FEET:
                model.LeftLeg.visible = true;
                model.RightLeg.visible = true;
                break;
        }
    }

    public static void disablePart(BatmanArmorModel model, BodyParts part) {
        switch (part) {
            case HEAD:
                model.Head.visible = false;
                break;
            case CHEST:
                model.Body.visible = false;
                model.LeftArm.visible = false;
                model.RightArm.visible = false;
                break;
            case LEGS, FEET:
                model.LeftLeg.visible = false;
                model.RightLeg.visible = false;
                break;
        }
    }

    public static ItemStack getModelForSlot(LivingEntity entity, BodyParts parts) {
        return switch(parts) {
            default -> entity.getEquippedStack(EquipmentSlot.HEAD);
            case CHEST -> entity.getEquippedStack(EquipmentSlot.CHEST);
            case LEGS -> entity.getEquippedStack(EquipmentSlot.LEGS);
            case FEET -> entity.getEquippedStack(EquipmentSlot.FEET);
        };
    }


    public enum BodyParts {
        HEAD,
        CHEST,
        LEGS,
        FEET
    }
}