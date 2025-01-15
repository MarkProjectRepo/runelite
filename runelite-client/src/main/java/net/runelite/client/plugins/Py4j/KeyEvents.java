package net.runelite.client.plugins.Py4j;

import net.runelite.client.RuneLite;
import net.runelite.api.Client;
import java.awt.event.KeyEvent;


public class KeyEvents {
    private static final Client client = RuneLite.getInjector().getInstance(Client.class);

    public static void pressKey(int keyCode) {
        if (keyCode == -1) {
            keyCode = KeyEvent.VK_ENTER;
        }
        KeyEvent keyPress = new KeyEvent(client.getCanvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyRelease = new KeyEvent(client.getCanvas(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyTyped = new KeyEvent(client.getCanvas(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, (char) keyCode);
        try {
            // Add random delay between 20-100ms for key press to release
            // This simulates typical human key press duration
            Thread.sleep((long)(20 + Math.random() * 80));
        } catch (InterruptedException e) {
            // Ignore interruption
        }
        client.getCanvas().dispatchEvent(keyPress);
        client.getCanvas().dispatchEvent(keyRelease);
        client.getCanvas().dispatchEvent(keyTyped);
    }
} 