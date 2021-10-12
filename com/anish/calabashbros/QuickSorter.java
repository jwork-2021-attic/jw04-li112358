package com.anish.calabashbros;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

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
    	if(i==j)return;
        Collections.swap(a, i, j);
        plan += "" + a.get(i) + "<->" + a.get(j) + "\n";
    }

    private String plan = "";
    
    private void QuickSort(int left,int right) {
    	if(left >= right)return;
    	int tip = left;
    	for(int i = left + 1;i <= right;++i) {
    		if(a.get(i).compareTo(a.get(left)) > 0) {
    			tip++;
    			swap(tip,i);
    		}
    	}
    	swap(left,tip);
    	QuickSort(left,tip-1);
    	QuickSort(tip+1,right);
    }
    
    @Override
    public void sort() {
       QuickSort(0,a.size()-1);
    }
    
    @Override
    public String getPlan() {
        return this.plan;
    }

}
