package com.e100.ic2railgunaddon.registries;

import com.e100.ic2railgunaddon.item.ModItems;
import ic2.api.recipes.registries.IAdvancedCraftingManager;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class AddonRecipies {
	
	public static void initRecipes(IAdvancedCraftingManager manager) {
		 //weapon
		        manager.addShapedRecipe(new ResourceLocation("ic2railgunaddon", "railgun"),
		        		new ItemStack((ItemLike)ModItems.RAILGUN.get()),
		                "   ", "ETR", "   ",
		                    'R', ModItems.STOCK.get(),
		                    'E', ModItems.BARREL.get(),
		                    'T', ModItems.CORE.get());
		//barrel        
		        manager.addShapedRecipe(new ResourceLocation("ic2railgunaddon", "barrel"),
		        		new ItemStack((ItemLike)ModItems.BARREL.get()),
		                "AAA", "GGG", "AAA",
		                    'A', IC2Items.PLATE_ADVANCED_ALLOY,
		                    'G', IC2Items.DUST_DIAMOND);
	    //core       
		        manager.addShapedRecipe(new ResourceLocation("ic2railgunaddon", "core"),
		        		new ItemStack((ItemLike)ModItems.CORE.get()),
		                "AGG", "AAA", "  A",
		                    'A', IC2Items.PLATE_ADVANCED_ALLOY,
		                    'G', IC2Items.ENERGY_CRYSTAL);
		//stock        
		        manager.addShapedRecipe(new ResourceLocation("ic2railgunaddon", "stock"),
		        		new ItemStack((ItemLike)ModItems.STOCK.get()),
		                "AAA", " AA", "  A",
		                    'A', IC2Items.PLATE_ADVANCED_ALLOY);
	      
	}

}
