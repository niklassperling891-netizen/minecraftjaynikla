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
public abstract class EntityTeamColorMixin {
    @Inject(method = "getTeamColorValue", at = @At("HEAD"), cancellable = true)
    private void mbi$colorBreedingState(CallbackInfoReturnable<Integer> cir) {
        if (!MobBreedingIndicatorClient.enabled) return;
        Entity self = (Entity)(Object)this;
        if (self instanceof AnimalEntity animal && animal.isAlive()) {
            int age = animal.getBreedingAge();
            if (age < 0) cir.setReturnValue(0xE33B3B);       // baby / not ready
            else if (age > 0) cir.setReturnValue(0x2787FF);  // breeding cooldown
            else cir.setReturnValue(0x22C55E);               // ready
        } else if (self instanceof MobEntity mob && self instanceof PassiveEntity && mob.isAlive()) {
            cir.setReturnValue(0x22C55E);
        }
    }
}
