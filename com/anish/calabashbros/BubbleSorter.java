package com.anish.calabashbros;

import java.util.ArrayList;
import java.util.Collections;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private ArrayList<T> a;

	public void load(T[][] a) {
		this.a = new ArrayList<T>(a.length*a.length);
    	for(int i = 0;i < a.length;++i) {
    		for(int j = 0;j < a[i].length;++j) {
    			this.a.add(a[i][j]);
    		}
    	}
    }
    
    @Override
    public void load(T[] a) {
    	this.a = new ArrayList<T>(a.length);
		for(T ti : a) {
        	this.a.add(ti);
        }
    }
    private void swap(int i, int j) {
        Collections.swap(a, i, j);
        plan += "" + a.get(i) + "<->" + a.get(j) + "\n";
    }

    private String plan = "";
    
    
    
    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.size() - 1; i++) {
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}