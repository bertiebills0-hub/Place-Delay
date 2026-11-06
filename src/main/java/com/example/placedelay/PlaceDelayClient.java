package com.example.placedelay;

import com.example.placedelay.mixin.MinecraftAccessor;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class PlaceDelayClient implements ClientModInitializer {
    private static final int VANILLA_DELAY = 4;
    private static int delay = VANILLA_DELAY; // starts at vanilla

    private static KeyMapping cycleKey;

    @Override
    public void onInitializeClient() {
        cycleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.placedelay.cycle",
                InputConstants.Type.KEYBOARD,
                InputConstants.KEY_MINUS,
                KeyMapping.Category.GAMEPLAY));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (cycleKey.consumeClick()) {
                delay = (delay + 1) % (VANILLA_DELAY + 1); // 0,1,2,3,4,0,...
                if (mc.player != null) {
                    mc.gui.hud.setOverlayMessage(
                            Component.literal("Place delay: " + delay
                                    + (delay == VANILLA_DELAY ? " (vanilla)" : "")),
                            false);
                }
            }

            // Vanilla sets the delay to 4 after every placement/use; cap it to ours.
            MinecraftAccessor acc = (MinecraftAccessor) mc;
            if (acc.placedelay$getRightClickDelay() > delay) {
                acc.placedelay$setRightClickDelay(delay);
            }
        });
    }
}
