package corgiaoc.byg;


import corgiaoc.byg.client.textures.renders.BYGCutoutRenders;
import corgiaoc.byg.common.entity.boat.BYGBoatRenderer;
import corgiaoc.byg.common.properties.BYGCreativeTab;
import corgiaoc.byg.common.properties.vanilla.BYGCompostables;
import corgiaoc.byg.common.properties.vanilla.BYGFlammables;
import corgiaoc.byg.common.properties.vanilla.BYGHoeables;
import corgiaoc.byg.common.properties.vanilla.BYGStrippables;
import corgiaoc.byg.common.world.biome.BYGBiomeWeightSystem;
import corgiaoc.byg.common.world.dimension.end.BYGEndBiomeCatch;
import corgiaoc.byg.common.world.dimension.end.BYGEndBiomeProvider;
import corgiaoc.byg.common.world.dimension.nether.BYGNetherBiomeCatch;
import corgiaoc.byg.common.world.dimension.nether.BYGNetherBiomeProvider;
import corgiaoc.byg.common.world.feature.biomefeatures.BYGFeaturesInVanilla;
import corgiaoc.byg.config.BYGWorldConfig;
import corgiaoc.byg.config.biomeweight.ConfigWeightManager;
import corgiaoc.byg.core.world.BYGBiomes;
import corgiaoc.byg.core.BYGEntities;
import corgiaoc.byg.data.BYGDataGenerator;
import corgiaoc.byg.data.BlockDataHelperCleanedUp;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod("byg")
public class BYG {
    public static final String MOD_ID = "byg";
    public static boolean isClient = false;
    public static Logger LOGGER = LogManager.getLogger();
    public static boolean isUsingMixin;
    private static final String filePath = "kachow";

    public BYG() {
        BYGWorldConfig.loadConfig(BYGWorldConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-world-common.toml"));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::bygCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::bygClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::bygLoadComplete);
        MinecraftForge.EVENT_BUS.register(new SubscribeEvents());
    }

    private void bygCommonSetup(FMLCommonSetupEvent event) {
        ConfigWeightManager.buildConfig();
        ConfigWeightManager.loadConfig(ConfigWeightManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(BYG.MOD_ID + "-weights-common.toml"));
        LOGGER.debug("BYG: \"Common Setup\" Event Starting...");
        BYGCreativeTab.init();
        BYGEndBiomeCatch.endBiomeConfigCollection();
        BYGNetherBiomeCatch.netherBiomeConfigCollection();
        BYGFeaturesInVanilla.addBYGFeaturesToBiomes();
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(MOD_ID, "bygnether"), BYGNetherBiomeProvider.BYGNETHERCODEC);
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(MOD_ID, "bygend"), BYGEndBiomeProvider.BYGENDCODEC);
        BYGBiomes.addBiomeNumericalIDsForLayerSampler();
        BYGBiomeWeightSystem.addBiomesToWeightSystem();
        BYGBiomeWeightSystem.addBiomesToWeightSystem();
        LOGGER.info("BYG: \"Common Setup\" Event Complete!");
//        BlockDataHelperCleanedUp.createWoodRecipeGenerator(filePath, MOD_ID, "glacial_oak", true, true, true);
//        makeBYGLangFile();
    }


    public static void makeBYGLangFile() {
        List<String> blockIDList = new ArrayList<>();
        List<String> itemIDList = new ArrayList<>();
        List<String> biomeIDList = new ArrayList<>();

        for (Block block : ForgeRegistries.BLOCKS) {
            String blockID = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString();

            if (blockID.contains(MOD_ID))
                blockIDList.add(blockID.replace(MOD_ID + ":", ""));
        }

        for (Item item : ForgeRegistries.ITEMS) {
            String itemID = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();

            if (itemID.contains(MOD_ID))
                itemIDList.add(itemID.replace(MOD_ID + ":", ""));
        }


        for (Biome biome : WorldGenRegistries.BIOME) {
            String biomeID = Objects.requireNonNull(WorldGenRegistries.BIOME.getKey(biome)).toString();

            if (biomeID.contains(MOD_ID))
                biomeIDList.add(biomeID.replace(MOD_ID + ":", ""));
        }

        BlockDataHelperCleanedUp.createLangFile(filePath, MOD_ID, blockIDList, biomeIDList, itemIDList);
    }


    private void bygClientSetup(FMLClientSetupEvent event) {
        isClient = true;
        LOGGER.debug("BYG: \"Client Setup\" Event Starting...");
        BYGCutoutRenders.renderCutOuts();
//        MainMenuBYG.mainMenuPanorama();
        RenderingRegistry.registerEntityRenderingHandler(BYGEntities.BYGBOAT, BYGBoatRenderer::new);
//        WorldType116.addGenerator();
        LOGGER.info("BYG: \"Client Setup\" Event Complete!");
    }

    private void bygLoadComplete(FMLLoadCompleteEvent event) {
        LOGGER.debug("BYG: \"Load Complete Event\" Starting...");
        BYGCompostables.compostablesBYG();
        BYGHoeables.hoeablesBYG();
        BYGFlammables.flammablesBYG();
        BYGStrippables.strippableLogsBYG();
        LOGGER.info("BYG: \"Load Complete\" Event Complete!");
    }

    public static class SubscribeEvents {
        @SubscribeEvent
        public void commandRegisterEvent(FMLServerStartingEvent event) {
            LOGGER.debug("BYG: \"Server Starting\" Event Starting...");
            BYGDataGenerator.dataGenCommand(event.getServer().getCommandManager().getDispatcher());
            LOGGER.info("BYG: \"Server Starting\" Event Complete!");
        }
    }
}