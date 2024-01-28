package youren.touhou.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import youren.touhou.EirinYagokorosPharmacy;

public class ArrowHitSelfEvent {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (entity instanceof PlayerEntity) {
                if (source.getSource() instanceof ArrowEntity arrow) {
                    if (arrow.getOwner() == entity) {
                        ((PlayerEntity) entity).giveItemStack(new ItemStack(EirinYagokorosPharmacy.ITEM_FLESH));
                    }
                }
            }
            return true;
        });
    }
}