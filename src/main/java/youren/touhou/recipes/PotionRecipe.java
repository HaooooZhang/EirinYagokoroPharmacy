package youren.touhou.recipes;

import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import youren.touhou.EirinYagokorosPharmacy;
import youren.touhou.mixin.BrewingRecipeRegistryMixin;

public class PotionRecipe {

    public static void registerPotions() {
        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.GOLDEN_APPLE, EirinYagokorosPharmacy.ULTRAMARINE_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(EirinYagokorosPharmacy.ULTRAMARINE_POTION, EirinYagokorosPharmacy.ITEM_FLESH, EirinYagokorosPharmacy.HOURAI_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.TORCHFLOWER_SEEDS, EirinYagokorosPharmacy.RESUMPTION_POTION);
    }

}
