package net.runelite.client.plugins.datatracking;


import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.api.events.GameTick;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.MouseListener;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.client.ui.ClientUI;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * client.getRealSkillLevel(Skill.HITPOINTS);
 * client.getBoostedSkillLevel(Skill.HITPOINTS);
 *
*/
@PluginDescriptor(
        name = "DataTracking",
        description = "Track user motions and actions"
)
@Slf4j
public class DataTracking extends Plugin{
    @Inject
    private Client client;

    private int tickCounter = 0;
    private CircularQueue<Point> movements = new CircularQueue<>(30);
    private MouseManager mousemanager;

    @Inject
    private DataTrackingInputListener inputListener;

    @Override
    protected void startUp() throws Exception
    {
        mousemanager.registerMouseListener(inputListener);
    }

    @Subscribe
    public void MouseClicked(MouseEvent e){
        log.debug(e.toString());
    }
}