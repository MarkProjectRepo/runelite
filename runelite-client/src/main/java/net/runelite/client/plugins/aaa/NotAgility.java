/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.aaa;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.Graphics2D;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;
import net.runelite.client.util.ImageUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.Renderable;
import net.runelite.api.Player;
import net.runelite.api.Tile;
import net.runelite.api.TileItem;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.DecorativeObjectDespawned;
import net.runelite.api.events.DecorativeObjectSpawned;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GroundObjectDespawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.ItemDespawned;
import net.runelite.api.events.ItemSpawned;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.StatChanged;
import net.runelite.api.events.WallObjectDespawned;
import net.runelite.api.events.WallObjectSpawned;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.aaa.aaaInputListener;
import net.runelite.client.plugins.xptracker.XpTrackerPlugin;
import net.runelite.client.plugins.xptracker.XpTrackerService;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.Text;
import py4j.GatewayServer;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

@PluginDescriptor(
	name = "AAAAAAAA",
	description = "Track n whack",
	tags = {}
)

@Slf4j
public class NotAgility extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private aaaInputListener inputListener;

	@Inject
	private MouseManager mouseManager;

	@Inject
	private Notifier notifier;

	@Inject
	@Getter
	private Client client;

	@Inject
	@Getter
	private ClientUI clientUI;

	@Getter
	@Inject
	private ConcurrentLinkedQueue<Renderable> clickQueue = new ConcurrentLinkedQueue<>();

	@Inject
	private ScheduledExecutorService executor;
	
	private GatewayServer server;

	public int tickCount = 0;

	public Image lastImage;
	// @Inject
	// private InfoBoxManager infoBoxManager;

	@Override
	public void startUp()
	{
		inputListener.setClickQueue(clickQueue); // Set the clickQueue before registering
		mouseManager.registerMouseListener(inputListener);
		server = new GatewayServer(this);
		server.start();
	}

	@Override
	public void shutDown()
	{
		mouseManager.unregisterMouseListener(inputListener);
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		tickCount++;
		tickCount %= 1000;
		Consumer<Image> imageCallback = (img) ->
		{
			// This callback is on the game thread, move to executor thread
			executor.submit(() -> lastImage = img);
		};
		// TODO: Use this as the trigger for data tracking, either every tick or every other
	}

	/**
	 * Processes the last captured image by resizing it to the specified dimensions
	 * and optionally normalizing the colors.
	 *
	 * @param width  The desired width of the processed image.
	 * @param height The desired height of the processed image.
	 * @param normalizeColors Whether to normalize the colors of the image.
	 * @return The processed BufferedImage.
	 */
	public Image processImage(int width, int height, boolean normalizeColors)
	{
		if (lastImage == null)
		{
			return null;
		}

		BufferedImage bufferedImage = ImageUtil.bufferedImageFromImage(lastImage);
		BufferedImage resizedImage = new BufferedImage(width, height, bufferedImage.getType());

		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(bufferedImage, 0, 0, width, height, null);
		g2d.dispose();

		if (normalizeColors)
		{
			resizedImage = normalizeImageColors(resizedImage);
		}

		return (Image) resizedImage;
	}

	/**
	 * Normalizes the colors of the given image.
	 *
	 * @param image The image to normalize.
	 * @return The color-normalized BufferedImage.
	 */
	private BufferedImage normalizeImageColors(BufferedImage image)
	{
		RescaleOp rescaleOp = new RescaleOp(1.2f, 15, null);
		rescaleOp.filter(image, image);
		return image;
	}
}
