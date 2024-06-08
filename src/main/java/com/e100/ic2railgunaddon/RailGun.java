package com.e100.ic2railgunaddon;

import com.e100.ic2railgunaddon.item.ModItems;
import com.e100.ic2railgunaddon.registries.AddonRecipies;
import com.mojang.logging.LogUtils;

import ic2.core.platform.recipes.misc.AdvRecipeRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RailGun.MODID)
public class RailGun
{
    
    public static final String MODID = "ic2railgunaddon";
    
    private static final Logger LOGGER = LogUtils.getLogger();

    public RailGun()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        
        modEventBus.addListener(this::commonSetup);        

        
        MinecraftForge.EVENT_BUS.register(this);

        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    	AdvRecipeRegistry.INSTANCE.registerListener(AddonRecipies::initRecipes);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            
        }
    }
}
