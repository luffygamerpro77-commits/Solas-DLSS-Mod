package com.luffygamerpro77.solasdlss.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "solas-dlss-mod.json";

    public DlssMode dlssMode = DlssMode.OFF;
    public FrameGenerationMode frameGenerationMode = FrameGenerationMode.OFF;
    public ReflexMode reflexMode = ReflexMode.OFF;
    public boolean showHud = true;
    public boolean debugInfo = false;

    public static void initializeDefaults() {
        // Initialize with default values
    }

    public static ModConfig load() {
        ModConfig config = new ModConfig();
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);

        if (!Files.exists(path)) {
            save(config);
            return config;
        }

        try {
            String json = Files.readString(path, StandardCharsets.UTF_8);
            ModConfig loaded = GSON.fromJson(json, ModConfig.class);
            return loaded != null ? loaded : config;
        } catch (IOException e) {
            return config;
        }
    }

    public static void save(ModConfig config) {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(config), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to save Solas DLSS config: " + e.getMessage());
        }
    }

    public enum DlssMode {
        OFF("OFF"),
        QUALITY("Quality (0.75x)"),
        BALANCED("Balanced (0.67x)"),
        PERFORMANCE("Performance (0.5x)");

        public final String label;

        DlssMode(String label) {
            this.label = label;
        }
    }

    public enum FrameGenerationMode {
        OFF("OFF"),
        X2("2× (OpenGL unsupported)"),
        X3("3× (OpenGL unsupported)");

        public final String label;

        FrameGenerationMode(String label) {
            this.label = label;
        }
    }

    public enum ReflexMode {
        OFF("OFF"),
        ON("ON"),
        BOOST("BOOST");

        public final String label;

        ReflexMode(String label) {
            this.label = label;
        }
    }
}
