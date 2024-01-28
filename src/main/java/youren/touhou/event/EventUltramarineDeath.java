package youren.touhou.event;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import youren.touhou.EirinYagokorosPharmacy;

public class EventUltramarineDeath {
    public static boolean onPlayerDeathB(ServerPlayerEntity player, DamageSource damageSource, float damageAmount) {
        StatusEffectInstance effectInstance = player.getStatusEffect(EirinYagokorosPharmacy.ULTRAMARINE_EFFECT);
        boolean hasUltramarine = effectInstance != null;
        boolean hasTotem = player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultStack());
        int canUndead = 0;


        if (hasUltramarine) {
            canUndead = 1;
        }

        if (hasUltramarine && !hasTotem) {
            if (canUndead >= 1) {
                player.setHealth(player.getHealth() + 10.0F);
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
                canUndead = 0;
                return false;
            }
        }
        return true;
    }
}
