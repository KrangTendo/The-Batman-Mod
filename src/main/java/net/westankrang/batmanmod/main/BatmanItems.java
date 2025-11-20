package net.westankrang.batmanmod.main;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.westankrang.batmanmod.BatmanMod;
import net.westankrang.batmanmod.main.items.BatarangItem;
import net.westankrang.batmanmod.main.items.BatmanArmorItem;

public class BatmanItems {
    public static final Item BATARANG = registerItem("batarang", new BatarangItem(new FabricItemSettings().maxDamage(250)));

    public static final Item BATMAN_ARMOR_HELMET = registerItem("batman_armor_helmet", new BatmanArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET,
            new FabricItemSettings().maxDamage(240), true, "black"));

    public static final Item BATMAN_ARMOR_CHESTPLATE = registerItem("batman_armor_chestplate",new BatmanArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE,
            new FabricItemSettings().maxDamage(240), true, "black"));

    public static final Item BATMAN_ARMOR_LEGGINGS = registerItem("batman_armor_leggings", new BatmanArmorItem(ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS,
            new FabricItemSettings().maxDamage(240), true, "black"));

    public static final Item BATMAN_ARMOR_BOOTS = registerItem("batman_armor_boots", new BatmanArmorItem(ArmorMaterials.IRON, ArmorItem.Type.BOOTS,
            new FabricItemSettings().maxDamage(240), true, "black"));

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(BATARANG);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BatmanMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BatmanMod.LOGGER.info("Registering Mod Items for " + BatmanMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(BatmanItems::addItemsToCombatTabItemGroup);
    }
}
