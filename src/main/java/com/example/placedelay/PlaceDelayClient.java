package com.example.placedelay;

import com.example.placedelay.mixin.MinecraftAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class PlaceDelayClient implements ClientModInitializer {
    private static final int DELAY = 3;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            MinecraftAccessor acc = (MinecraftAccessor) mc;
            if (acc.placedelay$getRightClickDelay() > DELAY) {
                acc.placedelay$setRightClickDelay(DELAY);
            }
        });
    }
}
