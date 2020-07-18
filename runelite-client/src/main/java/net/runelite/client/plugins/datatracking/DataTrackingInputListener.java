package net.runelite.client.plugins.datatracking;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.Point;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.input.MouseManager;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.awt.event.MouseEvent;

@Slf4j
public class DataTrackingInputListener extends MouseAdapter {
    private final Client client;
    private final DataTracking plugin;

    private CircularQueue<Point> queue = new CircularQueue<>(30);

    @Inject
    private DataTrackingInputListener(Client client, DataTracking plugin)
    {
        this.client = client;
        this.plugin = plugin;
    }

    @Override
    public MouseEvent mouseClicked(MouseEvent e)
    {
        log.debug(queue.toString());

        return super.mouseClicked(e);
    }

    @Override
    public MouseEvent mouseMoved(MouseEvent e)
    {
        if (queue.isFull()){
            queue.dequeue();
        }

        queue.enqueue(new Point(e.getXOnScreen(), e.getYOnScreen()));
        return super.mouseClicked(e);
    }
}
