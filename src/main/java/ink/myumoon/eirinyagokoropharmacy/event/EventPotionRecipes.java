package ink.myumoon.eirinyagokoropharmacy.event;

import ink.myumoon.eirinyagokoropharmacy.EirinYagokoroPharmacy;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = "eirinyagokoropharmacy")
public class EventPotionRecipes {
    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event){
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.GOLDEN_APPLE, EirinYagokoroPharmacy.ULTRAMARINE_POTION);
        builder.addMix(Potions.AWKWARD, Items.TORCHFLOWER,EirinYagokoroPharmacy.RESUMPTION_POTION);
        builder.addMix(EirinYagokoroPharmacy.ULTRAMARINE_POTION,EirinYagokoroPharmacy.ITEM_FLESH.get(),EirinYagokoroPharmacy.HOURAI_POTION);
    }
}
