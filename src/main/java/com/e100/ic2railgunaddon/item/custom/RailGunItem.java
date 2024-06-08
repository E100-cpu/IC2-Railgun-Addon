package com.e100.ic2railgunaddon.item.custom;

import com.e100.ic2railgunaddon.item.ModCreativeModeTab;

import ic2.core.platform.registries.IC2Sounds;
import ic2.api.items.electric.ElectricItem;
import ic2.api.items.electric.IDamagelessElectricItem;
import ic2.api.items.electric.IElectricEnchantable;
import ic2.core.IC2;
import ic2.core.audio.AudioManager;
import ic2.core.item.base.IC2ElectricItem;
import ic2.core.utils.tooltips.IAdvancedTooltip;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class RailGunItem extends IC2ElectricItem implements IDamagelessElectricItem, IAdvancedTooltip, IElectricEnchantable {
	
	public RailGunItem() {
        super("combat_laser");
        this.capacity = 200000;
        this.tier = 2;
        this.transferLimit = 512;
    }

	@Override
	public InteractionResult getEnchantmentCompatibility(ItemStack arg0, Enchantment arg1) {
		// TODO Auto-generated method stub
		return InteractionResult.FAIL;
	}

	@Override
	public EnchantmentCategory getEnchantmentType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return EnchantmentCategory.BREAKABLE;
	}

	@Override
	protected int getEnergyCost(ItemStack arg0) {
		// TODO Auto-generated method stub
		return 1000;
	}
	
	@Override
    public boolean isBarVisible(ItemStack stack) {
        return ElectricItem.MANAGER.getCharge(stack) > 0;
    }

	@Override
    public int getBarColor(ItemStack stack) {
        return IC2ElectricItem.getRGBDurability(stack);
    }

	@Override
    public int getBarWidth(ItemStack stack) {
        return IC2ElectricItem.getElectricWidth(stack);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (group == ModCreativeModeTab.IC2CRAILGUNADDON_TAB) {
            IC2ElectricItem.addEmptyAndFullToGroup(this, items);
        }
    }
    
    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return false;
    }
    
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand){
		ItemStack itemstack = pPlayer.getItemInHand(pHand);
		if (!pLevel.isClientSide()) {
		
		if (ElectricItem.MANAGER.getCharge(itemstack) > 1000) {
		   EntityHitResult result = rayTraceEntities(pLevel, (LivingEntity)pPlayer, false, 60.0D);//detects if entity was hit
		   
		   HitResult result1 = pPlayer.pick(60.0D, 0.0F, false);//detects if block was hit
		
		   if (result != null) {
			   EntityHitResult entityHitResult = (EntityHitResult) result;
               entityHitResult.getEntity().hurt(DamageSource.playerAttack(pPlayer), getDamage());
               entityHitResult.getEntity().setSecondsOnFire(5);
               ElectricItem.MANAGER.discharge(itemstack, 1000, getTier(itemstack), true, false, false);
               pPlayer.getCooldowns().addCooldown(this, 20); 
               IC2.AUDIO.playSound(pPlayer, IC2Sounds.LASER_DEFAULT, AudioManager.SoundType.ITEM);
               return InteractionResultHolder.success(itemstack);
               
		   }else if(result1.getType() == HitResult.Type.BLOCK){//checks if a block was hit
			   BlockHitResult blockHitResult = (BlockHitResult) result1;
               BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
               
               if(pLevel.getBlockState(blockPos).isAir()) {
            	   pLevel.setBlockAndUpdate(blockPos, BaseFireBlock.getState(pLevel, blockPos));
                   ElectricItem.MANAGER.discharge(itemstack, 1000, getTier(itemstack), true, false, false);
                   pPlayer.getCooldowns().addCooldown(this, 20);
                   IC2.AUDIO.playSound(pPlayer, IC2Sounds.LASER_DEFAULT, AudioManager.SoundType.ITEM);
                                                    
                   return InteractionResultHolder.success(itemstack);
                   
               }else {
                   
                   ElectricItem.MANAGER.discharge(itemstack, 1000, getTier(itemstack), true, false, false);
                   IC2.AUDIO.playSound(pPlayer, IC2Sounds.LASER_DEFAULT, AudioManager.SoundType.ITEM);
                   pPlayer.getCooldowns().addCooldown(this, 20);
                   return InteractionResultHolder.success(itemstack);
                   
               }
			   			   			   
		   }else {
			   
			   ElectricItem.MANAGER.discharge(itemstack, 1000, getTier(itemstack), true, false, false);
               IC2.AUDIO.playSound(pPlayer, IC2Sounds.LASER_DEFAULT, AudioManager.SoundType.ITEM);
			   pPlayer.getCooldowns().addCooldown(this, 20); 
			   return InteractionResultHolder.success(itemstack);
		   }
		   
		}else {
            return InteractionResultHolder.fail(itemstack);
            
        }
		
		}
	
	return super.use(pLevel, pPlayer, pHand);    
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }
               
    private float getDamage() {
        return 10.0F;
    }

}
