package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

public class DefenseShieldModifier extends DurabilityShieldModifier {
    private static final ResourceLocation KEY_DEFENSESHIELD_CAP = TConstruct.getResource("DefenseShield_cap");

    @Override
    public Component getDisplayName(int level) {
        // display name without the level
        return super.getDisplayName();
    }

    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {

        addCapacity(volatileData, (int)(50 * context.getDefinition().getData().getMultiplier(ToolStats.DURABILITY)));
    }

    @Override
    public int getPriority() {
        // higher than reinforced, reinforced does not protect DefenseShield
        return 200;
    }

    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, int level) {
        // only show as fully repaired if DefenseShield is full
        return getDefenseShield(tool) < getCapacity(tool);
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, int level) {
        if (getDefenseShield(tool) > 0) {
            // just always display light blue, not much point in color changing really
            return 0x6495ed;
        }
        return -1;
    }
    @Override
    protected ResourceLocation getShieldKey() {
        return getId();
    }
    public ResourceLocation getCapacityKey() {
        return KEY_DEFENSESHIELD_CAP;
    }

    public int getCapacity(IModDataView volatileData) {
        return volatileData.getInt(getCapacityKey());
    }

    public int getCapacity(IToolStackView tool) {
        return getCapacity(tool.getVolatileData());
    }

    @Override
    protected int getShieldCapacity(IToolStackView tool, int level) {
        return getCapacity(tool);
    }

    public void setCapacity(ModDataNBT volatileData, int amount) {
        volatileData.putInt(KEY_DEFENSESHIELD_CAP, amount);
    }

    public void addCapacity(ModDataNBT volatileData, int amount) {
        setCapacity(volatileData, getCapacity(volatileData) + amount);
    }

    public int getDefenseShield(IToolStackView tool) {
        return getShield(tool);
    }

    @Override
    public void setShield(ModDataNBT persistentData, int amount) {
        super.setShield(persistentData, amount);
    }


    public void setDefenseShield(IToolStackView tool, int amount) {
        setShield(tool, 0, amount);
    }

    public void addDefenseShield(IToolStackView tool, int amount) {
        addShield(tool, 0, amount);
    }
    @Override
    public void onAttacked(IToolStackView tool, int level, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Entity attacker = source.getEntity();
        LivingEntity target = context.getEntity();
        //DefenseShieldModifier defenseshield = Utils.defenseshield.get();
        //if(target != null && defenseshield.getDefenseShield(tool) > 0) {
        //    target.heal(amount);
        //    defenseshield.addDefenseShield(tool, (int) (0 - amount));
        //}
    }
}
