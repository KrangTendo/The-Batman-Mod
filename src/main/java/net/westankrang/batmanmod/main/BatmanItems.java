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
import net.westankrang.batmanmod.main.items.BatKnucklesItem;
import net.westankrang.batmanmod.main.items.BatarangItem;
import net.westankrang.batmanmod.main.items.BatmanArmorItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatmanItems {
    public static final Item BATARANG = registerItem("batarang", new BatarangItem(new FabricItemSettings().maxDamage(250)));

    public static final Item BAT_KNUCKLES = registerItem("bat_knuckles", new BatKnucklesItem(new FabricItemSettings().maxDamage(250)));

    //Armor
    public static final List<String> COLORS = List.of(
            "black",
            "blue",
            "grey",
            "red"
    );

    public static final Map<String, Item> BATMAN_HELMETS = new HashMap<>();
    public static final Map<String, Item> BATMAN_CHESTS = new HashMap<>();
    public static final Map<String, Item> BATMAN_LEGS = new HashMap<>();
    public static final Map<String, Item> BATMAN_BOOTS = new HashMap<>();

    static {
        for (String color : COLORS) {

            BATMAN_HELMETS.put(color, registerArmor(
                    "batman_armor_helmet_" + color,
                    ArmorItem.Type.HELMET,
                    color
            ));

            BATMAN_CHESTS.put(color, registerArmor(
                    "batman_armor_chestplate_" + color,
                    ArmorItem.Type.CHESTPLATE,
                    color
            ));

            BATMAN_LEGS.put(color, registerArmor(
                    "batman_armor_leggings_" + color,
                    ArmorItem.Type.LEGGINGS,
                    color
            ));

            BATMAN_BOOTS.put(color, registerArmor(
                    "batman_armor_boots_" + color,
                    ArmorItem.Type.BOOTS,
                    color
            ));
        }
    }

    // Reusable registration helper
    private static Item registerArmor(String name, ArmorItem.Type type, String color) {
        return registerItem(
                name,
                new BatmanArmorItem(
                        ArmorMaterials.IRON,
                        type,
                        new FabricItemSettings().maxDamage(240),
                        true,
                        color
                )
        );
    }



    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(BATARANG);
        entries.add(BAT_KNUCKLES);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BatmanMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BatmanMod.LOGGER.info("Registering Mod Items for " + BatmanMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(BatmanItems::addItemsToCombatTabItemGroup);
    }
}
