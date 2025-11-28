package net.westankrang.batmanmod.client.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.westankrang.batmanmod.main.BatmanItems;

@Environment(EnvType.CLIENT)
public class BatarangRenderer extends EntityRenderer<PersistentProjectileEntity> {

    private final ItemRenderer itemRenderer;
    private static final ItemStack BATARANG_STACK = new ItemStack(BatmanItems.BATARANG);

    public BatarangRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(PersistentProjectileEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();
        matrices.translate(0, 0.1F, 0);

        float rotationYaw = MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90f;
        float rotationPitch = MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())- 90f;

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationYaw));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotationPitch));


        BakedModel model = itemRenderer.getModel(BATARANG_STACK, entity.getWorld(), null, 0);

        itemRenderer.renderItem(
                BATARANG_STACK,
                ModelTransformationMode.GROUND,
                false,
                matrices,
                vertexConsumers,
                light,
                OverlayTexture.DEFAULT_UV,
                model
        );

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(PersistentProjectileEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}

