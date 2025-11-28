package net.westankrang.batmanmod.main;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.westankrang.batmanmod.BatmanMod;

public class BatmanItemGroups {
    public static final ItemGroup BATARANG_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(BatmanMod.MOD_ID, "batarang"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("batman_mod.item_group"))
                    .icon(() -> new ItemStack(BatmanItems.BATARANG))
                    .entries((displayContext, entries) -> {
                        Registries.ITEM.stream()
                                .filter(item -> Registries.ITEM.getId(item).getNamespace().equals(BatmanMod.MOD_ID))
                                .forEach(entries::add);
                    })
                    .build()
    );


    public static void registerItemGroups() {
        BatmanMod.LOGGER.info("Registering Item Groups for " + BatmanMod.MOD_ID);
    }
}
