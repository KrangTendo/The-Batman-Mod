package net.westankrang.batmanmod.client.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.registry.RegistryWrapper;
import net.westankrang.batmanmod.main.BatmanItems;

import java.util.concurrent.CompletableFuture;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;

public class BatmanModEnglishLangProvider extends FabricLanguageProvider {

    private final FabricDataOutput dataGenerator;

    public BatmanModEnglishLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, "en_us");
        this.dataGenerator = dataGenerator;
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        // Item Group
        translationBuilder.add("batman_mod.item_group", "Bat tools");

        // Items
        translationBuilder.add(BatmanItems.BATARANG, "Batarang");
        translationBuilder.add(BatmanItems.BAT_KNUCKLES, "Bat Knuckles");

        BatmanItems.COLORS.forEach(color -> {
            translationBuilder.add(BatmanItems.BATMAN_HELMETS.get(color),
                    capitalize(color) + " Batman Helmet");
            translationBuilder.add(BatmanItems.BATMAN_CHESTS.get(color),
                    capitalize(color) + " Batman Chestplate");
            translationBuilder.add(BatmanItems.BATMAN_LEGS.get(color),
                    capitalize(color) + " Batman Leggings");
            translationBuilder.add(BatmanItems.BATMAN_BOOTS.get(color),
                    capitalize(color) + " Batman Boots");
        });

        try {
            dataGenerator.getModContainer().findPath("assets/batman/lang/en_us.existing.json")
                    .ifPresent(path -> {
                        try {
                            translationBuilder.add(path);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to merge existing language file!", e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException("Failed to check for existing language file!", e);
        }

    }
}
