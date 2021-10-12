package com.anish.screen;

import javax.swing.JFrame;//

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.QuickSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.ColorFactory;
import com.anish.calabashbros.QuickSorter;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

	public static int lines = 16;
	
    private World world;
    private Calabash[][] bros;
    String[] sortSteps;

    public WorldScreen() {   
        List<Integer> ran = new ArrayList<Integer>(lines*lines);
        for(int i = 0;i < lines*lines;++i) {
        	ran.add(i);
        }
        Collections.shuffle(ran);
        
        world = new World();

        bros = new Calabash[lines][lines];
        
        for(int i = 0;i < lines*lines;++i) {
        	int r = ran.get(i).intValue() / lines;
        	int j = ran.get(i).intValue() % lines;
        	bros[r][j] = new Calabash(ColorFactory.colors[i/lines][i%lines],i,world);
        }
        
        for(int r = 0;r < lines;++r) {
        	for(int j = 0;j < lines;++j) {
        		world.put(bros[r][j], World.WIDTH / (lines + 1) * (j + 1), World.HEIGHT / (lines + 1) * (r + 1));
        	}
        }

//        BubbleSorter<Calabash> b = new BubbleSorter<>();
//        b.load(bros);
//        b.sort();
        
        QuickSorter<Calabash> b = new QuickSorter<>();
        b.load(bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }
    
    private void execute(Calabash[][] bros, String step) {
    	String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }
    
    private Calabash getBroByRank(Calabash[][] bros, int rank) {
        for (int r = 0;r < lines;++r) {
        	for(int j =0;j < lines;++j)
        		if (bros[r][j].getRank() == rank) {
        			return bros[r][j];
            }
        }
        return null;
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(JFrame key) {

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
            if(key != null)key.repaint();
        }
        else {
        	
        }

        return this;
    }

}
