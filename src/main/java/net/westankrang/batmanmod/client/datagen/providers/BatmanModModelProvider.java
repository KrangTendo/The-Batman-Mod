package net.westankrang.batmanmod.client.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.westankrang.batmanmod.BatmanMod;
import net.westankrang.batmanmod.main.BatmanItems;

public class BatmanModModelProvider extends FabricModelProvider {
    public BatmanModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(BatmanItems.BATARANG, Models.HANDHELD_ROD);
        itemModelGenerator.register(BatmanItems.BAT_KNUCKLES, Models.HANDHELD_ROD);
    }
}
