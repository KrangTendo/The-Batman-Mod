package net.westankrang.batmanmod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.westankrang.batmanmod.main.BatmanEntities;
import net.westankrang.batmanmod.main.BatmanItemGroups;
import net.westankrang.batmanmod.main.BatmanItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatmanMod implements ModInitializer {

    public static final String MOD_ID = "batman";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BatmanItems.registerModItems();
        BatmanItemGroups.registerItemGroups();
        BatmanEntities.registerEntities();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
