package algorithm;

import java.util.Stack;

import algorithm.DeepFirstGo.Position;
import world.Tile;

public class DeepFirstGo {	
	private Stack<Position> stack;
	
	private int[][] map;
	
	class Position{
		int x;
		int y;
		Direction direction;
		Direction lstdirection;
		
		Position(int x,int y,Direction lstdir){
			this.x = x;
			this.y = y;
			direction = Direction.LEFT;
			this.lstdirection = lstdir;
		}
		
		Direction nextDirection() {
			switch(direction) {
			case LEFT:if(lstdirection != Direction.RIGHT)return Direction.RIGHT;
			case RIGHT:if(lstdirection != Direction.UP)return Direction.UP;
			case UP:if(lstdirection != Direction.DOWN)return Direction.DOWN;
			case DOWN:;
			default:;
			}
			return Direction.DEFAULT;
		}
	}
	
	public DeepFirstGo(int[][] map,int x,int y) {
		stack = new Stack<Position>();
		this.map = map;
		Position position = new Position(x,y,Direction.DEFAULT);
		stack.push(position);
	}
	
	public Direction go() {
		Position position = stack.pop();
		Direction direction = Direction.LEFT;
		int x = position.x,y = position.y;
		map[y][x] = -1;
		switch(position.direction) {
		case LEFT:{
			if(position.lstdirection!=Direction.LEFT&&position.x-1>=0&&map[position.y][position.x-1]==1) {
				direction = Direction.LEFT;
				position.direction = position.nextDirection();
				x -= 1;
				break;
			}
		}
		case RIGHT:{
			if(position.lstdirection!=Direction.RIGHT&&position.x+1<map[position.y].length&&map[position.y][position.x+1]==1) {
				direction = Direction.RIGHT;
				position.direction = position.nextDirection();
				x += 1;
				break;
			}
		}
		case UP:{
			if(position.lstdirection!=Direction.UP&&position.y-1>=0&&map[position.y-1][position.x]==1) {
				direction = Direction.UP;
				position.direction = position.nextDirection();
				y -= 1;
				break;
			}
		}
		case DOWN:{
			if(position.lstdirection!=Direction.DOWN&&position.y+1<map.length&&map[position.y+1][position.x]==1) {
				direction = Direction.DOWN;
				position.direction = position.nextDirection();
				y += 1;
				break;
			}
		}
		case DEFAULT:{
			return position.lstdirection;
		}
		}
		stack.push(position);
		stack.push(new Position(x,y,direction.directionBack()));
		return direction;
	}
	
}
