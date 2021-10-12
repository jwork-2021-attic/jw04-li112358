/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package screen;

import world.*;
import algorithm.*;
import asciiPanel.AsciiPanel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Aeranythe Echosong
 */
public class PlayScreen implements Screen {

    private World world;
    private Creature player;
    private Creature destination;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;
    private List<String> oldMessages;
    private DeepFirstGo dfg;

    public PlayScreen() {
        this.screenHeight = 50;
        this.screenWidth = 40;
        createWorld();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();

        CreatureFactory creatureFactory = new CreatureFactory(this.world);
        createCreatures(creatureFactory);
        
        this.dfg = new DeepFirstGo(world.getMap(),player.x(),player.y());
        startGame();
    }
    
    private void startGame() {
    	
    }

    private void createCreatures(CreatureFactory creatureFactory) {
        this.player = creatureFactory.newPlayer(this.messages);
        this.destination = creatureFactory.newDestination();
        this.world.setDestination(this.destination);
    }

    private void createWorld() {
        world = new WorldBuilder(50, 40).makeCaves().build();
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        // Show terrain
        for (int y = 0; y < screenHeight && y < world.height(); y++) {
            for (int x = 0; x < screenWidth && x < world.width(); x++) {
                int wx = x + left;
                int wy = y + top;
                terminal.write(world.glyph(wy, wx), x, y, world.color(wy, wx));
            }
        }
        // Show creatures
        for (Creature creature : world.getCreatures()) {
            if (creature.x() >= left && creature.x() < left + screenWidth && creature.y() >= top
                    && creature.y() < top + screenHeight) {
                    terminal.write(creature.glyph(), creature.x() - left, creature.y() - top, creature.color());
            }
        }
        // Creatures can choose their next action now
        world.update();
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Terrain and creatures
        displayTiles(terminal, getScrollX(), getScrollY());
        // Player
        terminal.write(player.glyph(), player.x() - getScrollX(), player.y() - getScrollY(), player.color());
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
    	world.setTile(player.y(), player.x(),Tile.FLOORR);
    	switch (this.dfg.go()) {
        case LEFT:
            player.moveBy(0, -1);
            break;
        case RIGHT:
            player.moveBy(0, 1);
            break;
        case UP:
            player.moveBy(-1, 0);
            break;
        case DOWN:
            player.moveBy(1, 0);
            break;
		default:
			break;
		}
		this.destination = world.Destination();
		if(this.destination == null)
			return new WinScreen();
		return this;
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x() - screenWidth / 2, world.width() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y() - screenHeight / 2, world.height() - screenHeight));
    }


}
