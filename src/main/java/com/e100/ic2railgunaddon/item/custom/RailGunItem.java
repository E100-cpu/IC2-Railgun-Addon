package com.e100.ic2railgunaddon.item.custom;

import com.e100.ic2railgunaddon.item.AmmoManager;
import com.e100.ic2railgunaddon.item.ModCreativeModeTab;

import ic2.api.items.electric.ElectricItem;
import ic2.api.items.electric.IDamagelessElectricItem;
import ic2.api.items.electric.IElectricEnchantable;
import ic2.core.item.base.IC2ElectricItem;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.platform.rendering.features.item.ICustomItemModelTransform;
import ic2.core.platform.rendering.features.item.IItemModel;
import ic2.core.utils.helpers.StackUtil;
import ic2.core.utils.plugins.IRegistryProvider;
import ic2.core.utils.tooltips.IAdvancedTooltip;
import ic2.core.utils.tooltips.ToolTipHelper;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class RailGunItem extends CrossbowItem implements IDamagelessElectricItem, IAdvancedTooltip, IElectricEnchantable {
	private final int ENERGY_PER_USAGE = 10000;
	
	public RailGunItem(Properties p_41383_) {
		super(p_41383_.setNoRepair().stacksTo(1));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.CROSSBOW;
	}
	
	public boolean isDamageable(ItemStack stack) {
	    return false;
	}

	@Override
	public boolean canProvideEnergy(ItemStack arg0) {
		return false;
	}

	@Override
	public int getCapacity(ItemStack arg0) {
		return 200000;
	}

	@Override
	public int getTier(ItemStack arg0) {
		return 2;
	}

	@Override
	public int getTransferLimit(ItemStack arg0) {
		return 512;
	}

	@Override
	public InteractionResult getEnchantmentCompatibility(ItemStack stack, Enchantment enchantment) {
		if (enchantment == Enchantments.QUICK_CHARGE) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
	}

	@Override
	public EnchantmentCategory getEnchantmentType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return EnchantmentCategory.CROSSBOW;
	}
	
	
	
	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
	    if (entity instanceof Player player) {
	        if (!player.getAbilities().instabuild) {
	            ItemStack ammo = AmmoManager.findAmmo(player);
	            if (!ammo.isEmpty()) {
	                if (shouldLoadRound(stack, timeLeft)) {
	                    loadRailgun(stack, world, player);
	                } else {
	                    fireRailgun(stack, world, player);
	                    ElectricItem.MANAGER.discharge(stack, ENERGY_PER_USAGE, getTier(stack), true, false, false); 
	                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
	                }
	            }
	        }
	    }
	}
	
	private boolean shouldLoadRound(ItemStack stack, int useDuration) {
	    return useDuration >= 3; // Adjust as needed for the desired loading time
	}
	
	private void loadRailgun(ItemStack railgun, Level world, Player player) {
		ItemStack ammo = AmmoManager.findAmmo(player);
		if (!ammo.isEmpty()) {
			ammo.shrink(1);     
		}
	}
	
	private void fireRailgun(ItemStack railgun, Level world, Player player) {
        Vec3 start = player.getEyePosition(1.0F);
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(10000)); //Adjust the range here

        HitResult hitResult = world.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        if (hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            entityHitResult.getEntity().hurt(DamageSource.playerAttack(player), getDamage());
        }
    }
	
	private float getDamage() {
        return 10.0F;
    }
	
	public boolean shouldShowChargeBar(ItemStack stack) {
        return !StackUtil.getNbtData(stack).getBoolean("hide_charge_bar");
    }

    public int getRGBDurability(ItemStack stack) {
        return IC2ElectricItem.getRGBDurability(stack);
    }

    public int getElectricWidth(ItemStack stack) {
        return IC2ElectricItem.getElectricWidth(stack);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (group == ModCreativeModeTab.IC2CRAILGUNADDON_TAB) {
            IC2ElectricItem.addEmptyAndFullToGroup(this, items);
        }
    }

	@Override
	public void addToolTip(ItemStack arg0, Player arg1, TooltipFlag arg2, ToolTipHelper arg3) {
		// TODO Auto-generated method stub
		
	}

}
