package net.westankrang.batmanmod.main;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public class BatmanToolMaterials implements ToolMaterial {

    //BATARANG(1,6500,1,4,1 );

    private final int mininglevel;
    private final int ItemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private Supplier<Ingredient> repairIngredient = null;

    BatmanToolMaterials(int mininglevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability) {
        this.mininglevel = mininglevel;
        ItemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.ItemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.mininglevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
