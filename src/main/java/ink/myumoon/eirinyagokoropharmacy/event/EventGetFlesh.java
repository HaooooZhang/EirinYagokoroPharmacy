package ink.myumoon.eirinyagokoropharmacy.event;

import ink.myumoon.eirinyagokoropharmacy.EirinYagokoroPharmacy;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = "eirinyagokoropharmacy")
public class EventGetFlesh {
    @SubscribeEvent
    public static void OnEventHurt(LivingDamageEvent.Post event){
        if (event.getEntity() instanceof Player player){
            if (event.getSource().getDirectEntity() instanceof AbstractArrow arrow){
                Entity shooter = arrow.getOwner();
                if (shooter != null && shooter.equals(player) && player.hasEffect(MobEffects.BAD_OMEN)){
                    player.spawnAtLocation(EirinYagokoroPharmacy.ITEM_FLESH);
                }
            }
        }
    }
}
