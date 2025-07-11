package ink.myumoon.eirinyagokoropharmacy.event;

import ink.myumoon.eirinyagokoropharmacy.EirinYagokoroPharmacy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = "eirinyagokoropharmacy")
public class EventUltramarine {
    public static final ResourceLocation ULTRAMARINE_EFFECT_DEATH = ResourceLocation.fromNamespaceAndPath("eirinyagokoropharmacy","ultramarine_effect_death");

    @SubscribeEvent
    public static void OnEventUltramarine(LivingDeathEvent event){
        if (event.getEntity() instanceof Player player){
            boolean hasTotom = player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultInstance());
            if (!player.getPersistentData().contains("inUndead")  && !hasTotom && player.hasEffect(EirinYagokoroPharmacy.ULTRAMARINE_EFFECT)){
                player.getAttributes().getInstance(Attributes.MAX_HEALTH).removeModifier(ULTRAMARINE_EFFECT_DEATH);
                event.setCanceled(true);

                if (!player.level().isClientSide() && player.getMaxHealth() > 8.0F){
                    AttributeModifier healthModifier = new AttributeModifier(ULTRAMARINE_EFFECT_DEATH,-8, AttributeModifier.Operation.ADD_VALUE);
                    AttributeMap attributes = player.getAttributes();
                    attributes.getInstance(Attributes.MAX_HEALTH).addPermanentModifier(healthModifier);
                }

                player.setHealth(player.getMaxHealth());
                player.removeEffect(EirinYagokoroPharmacy.ULTRAMARINE_EFFECT);
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,200,1));
                player.level().playSound(null, player.blockPosition(), SoundEvents.LARGE_AMETHYST_BUD_BREAK, SoundSource.PLAYERS,1.0F,1.0F);
                //改成山羊角-歌颂
            }else if(hasTotom){
                player.addEffect(new MobEffectInstance(EirinYagokoroPharmacy.ULTRAMARINE_EFFECT,6000));
            }
        }
    }
}
