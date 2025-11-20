package net.westankrang.batmanmod.main;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.westankrang.batmanmod.BatmanMod;
import net.westankrang.batmanmod.main.entities.BatarangProjectileEntity;

public class BatmanEntities {
    public static final EntityType<BatarangProjectileEntity> BATARANG_PROJECTILE =
            Registry.register(Registries.ENTITY_TYPE,
                    new Identifier(BatmanMod.MOD_ID, "batarang_projectile"),
                    FabricEntityTypeBuilder.<BatarangProjectileEntity>create(
                                    SpawnGroup.MISC,
                                    BatarangProjectileEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );


    public static void registerEntities() {
    }
}

