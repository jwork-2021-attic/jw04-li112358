package maze;

public class Try {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(30,30);
        mazeGenerator.generateMaze();
        InttoString method = array->{
        	String plan = "";
        	for(int r = 0;r < array.length;++r) {
        		for(int j = 0;j < array[r].length;++j) {
        			plan+=array[r][j];
        			plan+=" ";
        		}
        		plan+="\n";
        	}
        	return plan;
        };
        System.out.println("ARRAY MAZE\n"+method.getString(mazeGenerator.getArrayMaze()));
        System.out.println("RAW MAZE\n" + mazeGenerator.getRawMaze());
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());
    }
}

interface InttoString{
	String getString(int[][] array);
}