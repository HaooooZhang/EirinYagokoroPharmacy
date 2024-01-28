package youren.touhou.event;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import youren.touhou.EirinYagokorosPharmacy;

public class DeathEvent {

    public static boolean onPlayerDeath(ServerPlayerEntity player, DamageSource damageSource, float damageAmount) {
        StatusEffectInstance effectInstance = player.getStatusEffect(EirinYagokorosPharmacy.UNDEAD_EFFECT);
        boolean hasUndeadEffect = effectInstance != null;
        boolean hasTotem = player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultStack());

        if (hasUndeadEffect && !hasTotem) {
                player.setHealth(player.getHealth() + 0.01F);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
                return false;
        }
        return true;
    }

}
