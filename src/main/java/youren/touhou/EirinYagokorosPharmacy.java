package youren.touhou;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import youren.touhou.effect.EffectUltramarine;
import youren.touhou.effect.EffectUndead;
import youren.touhou.event.ArrowHitSelfEvent;
import youren.touhou.event.UndeadListener;

import static youren.touhou.event.DeathEvent.onPlayerDeath;
import static youren.touhou.event.EventUltramarineDeath.onPlayerDeathB;

public class EirinYagokorosPharmacy implements ModInitializer {

    public static final StatusEffect UNDEAD_EFFECT = new EffectUndead();
    public static final StatusEffect ULTRAMARINE_EFFECT = new EffectUltramarine();
    public static final Potion HOURAI_POTION = new Potion("hourai_potion", new StatusEffectInstance(UNDEAD_EFFECT, Integer.MAX_VALUE));

    public static final Potion ULTRAMARINE_POTION = new Potion("ulramarine_potion", new StatusEffectInstance(ULTRAMARINE_EFFECT, 4800));
    public static final Item ITEM_FLESH = new Item(new FabricItemSettings().maxCount(1));

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.add(ITEM_FLESH));
        Registry.register(Registries.STATUS_EFFECT, new Identifier("yagokoropharmacy", "undead_effect"), UNDEAD_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("yagokoropharmacy", "ulramarine_effect"), ULTRAMARINE_EFFECT);
        Registry.register(Registries.POTION, new Identifier("yagokoropharmacy", "undead_effect"), HOURAI_POTION);
        Registry.register(Registries.POTION, new Identifier("yagokoropharmacy", "ulramarine_effect"), ULTRAMARINE_POTION);
        Registry.register(Registries.ITEM, new Identifier("yagokoropharmacy", "flesh"), ITEM_FLESH);
        ArrowHitSelfEvent.register();

        ServerTickEvents.END_SERVER_TICK.register(new UndeadListener());

        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
            if (entity instanceof ServerPlayerEntity) {
                return onPlayerDeath((ServerPlayerEntity) entity, damageSource, damageAmount);
            }
            return true;
        });

        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
            if (entity instanceof ServerPlayerEntity) {
                return onPlayerDeathB((ServerPlayerEntity) entity, damageSource, damageAmount);
            }
            return true;
        });

    }
}