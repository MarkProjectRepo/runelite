/*
 * Copyright (c) 2018, Jeremy Plsek <https://github.com/jplsek>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *	list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *	this list of conditions and the following disclaimer in the documentation
 *	and/or other materials provided with the distribution.
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

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Renderable;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.MouseAdapter;
import net.runelite.api.IndexedObjectSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Py4jInputListener extends MouseAdapter implements KeyListener
{
	private final Client client;
	private ConcurrentLinkedQueue<Renderable> clickQueue;

	@Inject
	private Py4jInputListener(Client client, ConcurrentLinkedQueue<Renderable> clickQueue)
	{
		this.client = client;
		this.clickQueue = clickQueue;
	}

	@Override
	public MouseEvent mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1) {
			IndexedObjectSet<? extends NPC> npcs = client.getTopLevelWorldView().npcs();
			synchronized (npcs) {
				for (NPC npc : npcs) {
					if (npc.getConvexHull().contains(e.getX(), e.getY())) {
						System.out.println("Clicked on NPC: " + npc.getName());
						npc.setOverheadText("Hey, this is a " + npc.getName());
						npc.setDead(true);
						clickQueue.add(npc);
						break;	
					}
				}
			}
		}
		
		return super.mousePressed(e);
	}

	public void setClickQueue(ConcurrentLinkedQueue<Renderable> clickQueue) {
		this.clickQueue = clickQueue;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
}
