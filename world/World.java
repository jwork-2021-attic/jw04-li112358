package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
/**
 *
 * @author Aeranythe Echosong
 */
public class World {

	private Creature destination;
    private Tile[][] tiles;
    private int width;
    private int height;
    private List<Creature> creatures;

    public static final int TILE_TYPES = 2;

    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.height = tiles.length;
        this.width = tiles[0].length;
        this.creatures = new ArrayList<>();
    }

    public Tile tile(int y, int x) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.BOUNDS;
        } else {
            return tiles[y][x];
        }
    }

    public char glyph(int y, int x) {
        return tiles[y][x].glyph();
    }

    public Color color(int y, int x) {
        return tiles[y][x].color();
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void dig(int y, int x) {
        if (tile(y, x).isDiggable()) {
            tiles[y][x] = Tile.FLOORG;
        }
    }

    public void addAtEmptyLocation(Creature creature) {
        int x;
        int y;

        do {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
        } while (!tile(y, x).isGround() || this.creature(y, x) != null);

        creature.setX(x);
        creature.setY(y);

        this.creatures.add(creature);
    }

    public Creature creature(int y, int x) {
        for (Creature c : this.creatures) {
            if (c.x() == x && c.y() == y) {
                return c;
            }
        }
        return null;
    }

    public List<Creature> getCreatures() {
        return this.creatures;
    }

    public void remove(Creature target) {
        this.creatures.remove(target);
        destination = null;
    }

    public void update() {
        ArrayList<Creature> toUpdate = new ArrayList<>(this.creatures);

        for (Creature creature : toUpdate) {
            creature.update();
        }
    }
    
    public void setDestination(Creature des) {
    	this.destination = des;
    }
    
    public Creature Destination() {
    	return this.destination;
    }
    
    public int[][] getMap(){
    	int[][] map = new int[this.tiles.length][];
    	for(int i = 0;i < this.tiles.length;++i) {
    		map[i] = new int[this.tiles[i].length];
    		for(int j = 0;j < this.tiles[i].length;++j) {
    			switch(this.tiles[i][j]) {
    			case WALL:map[i][j] = 0;break;
    			default:map[i][j] = 1;break;
    			}
    		}
    	}
    	return map;
    }
    
    public void setTile(int y, int x,Tile tile) {
    	this.tiles[y][x] = tile;
    }
}
