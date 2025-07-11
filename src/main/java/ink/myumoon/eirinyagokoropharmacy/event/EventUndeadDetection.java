package ink.myumoon.eirinyagokoropharmacy.event;

import ink.myumoon.eirinyagokoropharmacy.EirinYagokoroPharmacy;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = "eirinyagokoropharmacy")
public class EventUndeadDetection {
    @SubscribeEvent
    public static void UndeadDetection(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if (player.getPersistentData().contains("IsUndead")) {
            boolean isUndead = player.getPersistentData().getBoolean("IsUndead");
            if (isUndead && !player.hasEffect(EirinYagokoroPharmacy.UNDEAD_EFFECT)) {
                player.addEffect(new MobEffectInstance(EirinYagokoroPharmacy.UNDEAD_EFFECT, -1));
            }
        }
    }
}
