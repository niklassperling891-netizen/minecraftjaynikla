package de.mobbreedingindicator.mixin;

import de.mobbreedingindicator.MobBreedingIndicatorClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityOutlineMixin {
    @Inject(method = "isGlowing", at = @At("HEAD"), cancellable = true)
    private void mbi$glowBreedableMobs(CallbackInfoReturnable<Boolean> cir) {
        if (!MobBreedingIndicatorClient.enabled) return;
        Entity self = (Entity)(Object)this;
        if (self instanceof AnimalEntity animal && animal.isAlive()) {
            // Keep existing vanilla glowing intact; show outline for breeding-capable animals.
            if (animal.getBreedingAge() != 0 || animal.isBaby()) cir.setReturnValue(true);
            else cir.setReturnValue(true);
        } else if (self instanceof MobEntity mob && mob.isAlive() && mob instanceof PassiveEntity) {
            cir.setReturnValue(true);
        }
    }
}
