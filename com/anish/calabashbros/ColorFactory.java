package com.anish.calabashbros;

import java.awt.Color;

public class ColorFactory {
	public static Color[][] colors;
	public static Color[][] colors_tmp;
	
	static {
		int lines = com.anish.screen.WorldScreen.lines;
		colors = new Color[lines][lines];

		for(int i = 0;i < lines;++i) {
			double ans = ((double)6 / lines) * i;
			int sw = (int)ans;
			ans = ans - sw;
			switch(sw) {
			case 0:{
				colors[i][0] = new Color(255,(int)(ans*255),0);//1 0 0
				break;
			}
			case 1:{
				colors[i][0] = new Color((int)((1-ans)*255),255,0);//1 1 0
				break;
			}
			case 2:{
				colors[i][0] = new Color(0,255,(int)(ans*255));//0 1 0
				break;
			}
			case 3:{
				colors[i][0] = new Color(0,(int)((1-ans)*255),255);//0 1 1
				break;
			}
			case 4:{
				colors[i][0] = new Color((int)(ans*255),0,255);//0 0 1
				break;
			}
			case 5:{
				colors[i][0] = new Color(255,0,(int)((1-ans)*255));//1 0 1
				break;
			}
			}
		}
		for(int i = 0;i < lines - 1;++i) {
			int r = (colors[i+1][0].getRed() - colors[i][0].getRed())/lines;
			int g = (colors[i+1][0].getGreen() - colors[i][0].getGreen())/lines;
			int b = (colors[i+1][0].getBlue() - colors[i][0].getBlue())/lines;
			for(int k = 1;k < lines;++k) {
				colors[i][k] = new Color(colors[i][0].getRed()+r*k,colors[i][0].getGreen()+g*k,colors[i][0].getBlue()+b*k);
			}
		}
		for(int k = 1;k < lines;++k) {
			colors[lines-1][k] = new Color(colors[lines-1][0].getRed()+(colors[0][0].getRed()-colors[lines-1][0].getRed())/lines*k,
					colors[lines-1][0].getGreen()+(colors[0][0].getGreen()-colors[lines-1][0].getGreen())/lines*k,
					colors[lines-1][0].getBlue()+(colors[0][0].getBlue()-colors[lines-1][0].getBlue())/lines*k);
		}
	}
	static {
		int lines = com.anish.screen.WorldScreen.lines;
		colors_tmp = new Color[lines][lines];
		for(int i = 0;i < lines;++i) {
			double ans = ((double)6 / lines) * i;
			int sw = (int)ans;
			ans = ans - sw;
			switch(sw) {
			case 0:{
				colors_tmp[i][lines-1] = new Color(255,(int)(ans*255),0);
				break;
			}
			case 1:{
				colors_tmp[i][lines-1] = new Color((int)((1-ans)*255),255,0);
				break;
			}
			case 2:{
				colors_tmp[i][lines-1] = new Color(0,255,(int)(ans*255));
				break;
			}
			case 3:{
				colors_tmp[i][lines-1] = new Color(0,(int)((1-ans)*255),255);
				break;
			}
			case 4:{
				colors_tmp[i][lines-1] = new Color((int)(ans*255),0,255);
				break;
			}
			case 5:{
				colors_tmp[i][lines-1] = new Color(255,0,(int)((1-ans)*255));
				break;
			}
			}
		}
		for(int i = 0;i < lines;++i) {
			int r = (255-colors_tmp[i][lines-1].getRed())/lines;
			int g = (255-colors_tmp[i][lines-1].getGreen())/lines;
			int b = (255-colors_tmp[i][lines-1].getBlue())/lines;
			for(int k = 0;k < lines - 1;++k) {
				colors_tmp[i][k] = new Color(colors_tmp[i][lines-1].getRed()+r*(lines-1-k),colors_tmp[i][lines-1].getGreen()+g*(lines-1-k),colors_tmp[i][lines-1].getBlue()+b*(lines-1-k));
			}
		}
	}
	
}
