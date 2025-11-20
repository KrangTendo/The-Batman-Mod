package net.westankrang.batmanmod.client.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import net.westankrang.batmanmod.BatmanMod;

@Environment(EnvType.CLIENT)
public class BatarangRenderer extends ProjectileEntityRenderer<PersistentProjectileEntity> {

    private static final Identifier TEXTURE =
            new Identifier(BatmanMod.MOD_ID, "textures/item/batarang.png");

    public BatarangRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(PersistentProjectileEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
                MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90f
        ));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(
                MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())
        ));


        float spin = (entity.age + tickDelta) * 40f;
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(spin));

        float shake = (float) entity.shake - tickDelta;
        if (shake > 0f) {
            float shakeRot = -MathHelper.sin(shake * 3f) * shake;
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(shakeRot));
        }

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45f));
        matrices.scale(0.05625f, 0.05625f, 0.05625f);
        matrices.translate(-4f, 0f, 0f);

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(TEXTURE));
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f mat = entry.getPositionMatrix();
        Matrix3f normal = entry.getNormalMatrix();

        this.vertex(mat, normal, vc, -7, -2, -2, 0f,        0.15625f, -1, 0, 0, light);


        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(PersistentProjectileEntity entity) {
        return TEXTURE;
    }

    public void vertex(Matrix4f pos, Matrix3f normalMatrix, VertexConsumer vc,
                       int x, int y, int z, float u, float v,
                       int normalX, int normalZ, int normalY, int light) {

        vc.vertex(pos, (float)x, (float)y, (float)z)
                .color(255, 255, 255, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, (float)normalX, (float)normalY, (float)normalZ)
                .next();
    }
}
