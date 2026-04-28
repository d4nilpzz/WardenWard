package dev.d4nilpzz.wardenward;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class WardenwardKeyBinds {

    public static final KeyMapping WARDEN_PULSE_ACTION = new KeyMapping(
            "key.wardenward.warden_pulse_action",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.categories.wardenward"
    );
}
