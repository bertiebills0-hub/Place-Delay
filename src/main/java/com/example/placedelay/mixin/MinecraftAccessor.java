package com.example.placedelay.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {
    @Accessor("rightClickDelay")
    int placedelay$getRightClickDelay();

    @Accessor("rightClickDelay")
    void placedelay$setRightClickDelay(int value);
}
