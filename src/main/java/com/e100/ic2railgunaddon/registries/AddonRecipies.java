package com.e100.ic2railgunaddon.registries;

import com.e100.ic2railgunaddon.RailGun;
import com.e100.ic2railgunaddon.item.ModItems;
import ic2.api.recipes.registries.IAdvancedCraftingManager;
import ic2.core.platform.registries.IC2Items;
import ic2.core.utils.config.gui.api.ISuggestionRenderer.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class AddonRecipies {
	
	public static void initRecipes(IAdvancedCraftingManager manager) {
		 
		        manager.addShapedRecipe(new ResourceLocation("ic2railgunaddon", "railgun"),
		                IC2Items.MINING_LASER.getDefaultInstance(),
		                "   ", "ETR", "   ",
		                    'R', ModItems.STOCK,
		                    'E', ModItems.BARREL,
		                    'T', ModItems.CORE);
		    
	}

}
