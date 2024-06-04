package com.e100.ic2railgunaddon.item.custom;

import org.antlr.v4.runtime.misc.NotNull;

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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class RailGunItem extends IC2ElectricItem implements IDamagelessElectricItem, IAdvancedTooltip, IElectricEnchantable {
	
	public RailGunItem() {
        super("combat_laser");
        this.capacity = 200000;
        this.tier = 2;
        this.transferLimit = 512;
    }
	
	
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
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
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return ElectricItem.MANAGER.getCharge(stack) > 0;
    }

	@Override
    public int getBarColor(@NotNull ItemStack stack) {
        return IC2ElectricItem.getRGBDurability(stack);
    }

	@Override
    public int getBarWidth(@NotNull ItemStack stack) {
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
		
		EntityHitResult result = rayTraceEntities(pLevel, (LivingEntity)pPlayer, false, 60.0D);
		
		if (result != null) {
			EntityHitResult entityHitResult = (EntityHitResult) result;
            entityHitResult.getEntity().hurt(DamageSource.playerAttack(pPlayer), getDamage());
            ElectricItem.MANAGER.discharge(itemstack, 1000, getTier(itemstack), true, false, false);
            pPlayer.getCooldowns().addCooldown(this, 20);
            //pPlayer.getAttackAnim(1.0f);
		}else {
			pPlayer.sendSystemMessage(Component.literal("No target locked"));
			pPlayer.getCooldowns().addCooldown(this, 20);
		}
		
		return super.use(pLevel, pPlayer, pHand);
    }
    
    private float getDamage() {
        return 10.0F;
    }

   

}
