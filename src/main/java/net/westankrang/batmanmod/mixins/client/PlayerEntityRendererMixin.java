package net.westankrang.batmanmod.mixins.client;

import net.westankrang.batmanmod.client.models.wearable.BatmanArmorModel;
import net.westankrang.batmanmod.client.renderers.BatmanArmorFeatureRenderer;
import net.westankrang.batmanmod.main.items.BatmanArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Arm;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends
        LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    @Shadow
    protected abstract void setModelPose(AbstractClientPlayerEntity player);

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx,
                                     PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void batman$addCustomArmorLayers(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        PlayerEntityRenderer renderer = (PlayerEntityRenderer) (Object) this;
        this.addFeature(new BatmanArmorFeatureRenderer<>(renderer, ctx.getModelLoader()));
    }


    @Inject(method = "renderArm", at = @At("HEAD"), cancellable = true)
    private void batman$renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        if (!(player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof BatmanArmorItem item)) return;
        ci.cancel();


        PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = this.getModel();
        this.setModelPose(player);
        playerEntityModel.handSwingProgress = 0.0f;
        playerEntityModel.sneaking = false;
        playerEntityModel.leaningPitch = 0.0f;
        playerEntityModel.setAngles(player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        arm.pitch = 0.0f;

        BatmanArmorModel spacesuitModel = new BatmanArmorModel(BatmanArmorModel.getTexturedModelData().createModel());

        boolean rightHanded = player.getMainArm() == Arm.RIGHT;

        if (rightHanded) {
            spacesuitModel.RightArm.copyTransform(arm);
            spacesuitModel.RightArm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(BatmanArmorFeatureRenderer.getTexture(item.color))), light, OverlayTexture.DEFAULT_UV);
        } else {
            spacesuitModel.LeftArm.copyTransform(arm);
            spacesuitModel.LeftArm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(BatmanArmorFeatureRenderer.getTexture(item.color))), light, OverlayTexture.DEFAULT_UV);
        }
    }

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;setModelPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void batman$hideBodyWhenWearingArmor(
            AbstractClientPlayerEntity player,
            float f, float g,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci) {

        PlayerEntityModel<AbstractClientPlayerEntity> model = this.getModel();

        if ((player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof BatmanArmorItem)) {
            model.head.visible = true;
            model.hat.visible = false;
        }


        model.body.visible = false;
        model.jacket.visible = false;

        model.leftArm.visible = false;
        model.rightArm.visible = false;
        model.leftSleeve.visible = false;
        model.rightSleeve.visible = false;

        model.leftLeg.visible = false;
        model.rightLeg.visible = false;
        model.leftPants.visible = false;
        model.rightPants.visible = false;
    }
}
