package net.westankrang.batmanmod.main.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BatmanArmorItem extends RenderableArmorItem {
    public String color = "black";

    public BatmanArmorItem(ArmorMaterial material, Type type, Settings settings, boolean hasCustomRendering, String color) {
        super(material, type, settings, hasCustomRendering);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public ItemStack getDefaultStack() {
        if (this.type != Type.CHESTPLATE) {
            return super.getDefaultStack();
        }
        ItemStack stack = new ItemStack(this);
        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.type != Type.CHESTPLATE) return;
        NbtCompound compound = stack.getOrCreateNbt();

        if (world == null || world.getServer() == null) return;

        if (world.getServer().getTicks() % 20 != 0) {
            return;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.literal("Color:" + color).formatted(Formatting.BLUE));
    }
}
