package youren.touhou.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import youren.touhou.EirinYagokorosPharmacy;

public class ArrowHitSelfEvent {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            // 检查受到伤害的实体是否为玩家
            if (entity instanceof PlayerEntity) {
                // 检查伤害来源是否为箭
                if (source.getSource() instanceof ArrowEntity arrow) {
                    // 检查箭是否由玩家射出
                    if (arrow.getOwner() == entity) {
                        // 给玩家添加ITEM_FLESH物品
                        ((PlayerEntity) entity).giveItemStack(new ItemStack(EirinYagokorosPharmacy.ITEM_FLESH));
                    }
                }
            }
            return true; // 返回true允许伤害发生
        });
    }
}