package net.westankrang.batmanmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.westankrang.batmanmod.BatmanMod;
import net.westankrang.batmanmod.client.renderers.BatarangRenderer;
import net.westankrang.batmanmod.main.BatmanEntities;

public class BatmanModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BatmanEntities.BATARANG_PROJECTILE, BatarangRenderer::new);
    }
}
