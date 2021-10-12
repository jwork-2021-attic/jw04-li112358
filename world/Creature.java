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
package world;

import java.awt.Color;

/**
 *
 * @author Aeranythe Echosong
 */
public class Creature {

    private World world;

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int x() {
        return x;
    }

    private int y;

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    private char glyph;

    public char glyph() {
        return this.glyph;
    }

    private Color color;

    public Color color() {
        return this.color;
    }

    private CreatureAI ai;

    public void setAI(CreatureAI ai) {
        this.ai = ai;
    }

    public Tile tile(int wy, int wx) {
        return world.tile(wy, wx);
    }

    public void dig(int wy, int wx) {
        world.dig(wy, wx);
    }

    public void moveBy(int my, int mx) {
    	if(x+mx<0||y+my<0||x+mx>=world.width()||y+my>=world.height()) {
    		return;
    	}
        Creature other = world.creature(y + my, x + mx);

        if (other == null) {
            ai.onEnter(y + my, x + mx, world.tile(y + my, x + mx));
        } else {
            world.remove(other);
            ai.onEnter(y + my, x + mx, world.tile(y + my, x + mx));
        }
    }

    public void update() {
        this.ai.onUpdate();
    }

    public boolean canEnter(int y, int x) {
        return world.tile(y, x).isGround();
    }

    public void notify(String message, Object... params) {
        ai.onNotify(String.format(message, params));
    }

    public Creature(World world, char glyph, Color color) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
    }
}
