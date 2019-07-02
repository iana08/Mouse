import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Read_Map {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static char[][] read_map(String text_file) throws FileNotFoundException, IOException
	{
		LOGGER.log(Level.INFO, "Reading Maze.");
		char[][] map = null;
		try(BufferedReader br = new BufferedReader(new FileReader(text_file)))
		{
			String str;
			int row = 0;
			int index = 0;
			int[] info = get_info(text_file);
			map = new char[info[0]][info[1]];
			while((str = br.readLine()) !=  null)
			{
				StringTokenizer st = new StringTokenizer(str, "\\s \n");
				index = 0;
				while(st.hasMoreTokens())
				{
					map[row][index] = st.nextToken().trim().charAt(0);
					index++;
				}
				row++;
			}
			br.close();
		}
		return map; 
	}
	
	private static int[] get_info(String text_file) throws IOException
	{
		int rows = 0;
		int cols = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(text_file)))
		{
			String str;
			while((str = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(str, " ");
				
				cols = st.countTokens(); // For Future Use: These are the individual elements
				
				rows++; // This is each Row
			}
			br.close();
		}
		return new int[] {rows,cols};
	}
}
