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
package net.runelite.client.plugins.Py4j;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.Graphics2D;
import net.runelite.client.util.ImageUtil;
import javax.inject.Inject;
import lombok.Getter;
// import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Renderable;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.Py4j.KeyEvents;
import net.runelite.client.ui.ClientUI;
import py4j.GatewayServer;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.runelite.client.RuneLite;
import net.runelite.client.chat.ChatCommandManager;


@PluginDescriptor(
	name = "AAAAAAAA",
	description = "Track n whack",
	tags = {}
)

@Slf4j
public class Py4j extends Plugin
{
	@Inject
	private Py4jInputListener inputListener;

	@Inject
	private MouseManager mouseManager;

	@Inject
	@Getter
	private Client client;

	@Inject
	@Getter
	private ChatCommandManager chatCommandManager;

	@Inject
	@Getter
	private ClientUI clientUI;

	@Getter
	private KeyEvents keyEvents = new KeyEvents();

	@Getter
	@Inject
	private ConcurrentLinkedQueue<Renderable> clickQueue = new ConcurrentLinkedQueue<>();
	
	private GatewayServer server;

	public int tickCount = 0;

	public Image lastImage;

	static Client api_client = RuneLite.getInjector().getInstance(Client.class);

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
