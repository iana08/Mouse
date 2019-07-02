
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mouse
{
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private int position_x;
	private int position_y;
	private int target_x;
	private int target_y;
	private int direction;
	
	private boolean free;
	
	char[][] maze;
	
	public Mouse()
	{
		position_x = 1;
		free = false;
		position_y = 1;
		this.direction = 0;
	}
	
	public Mouse(int x, int y)
	{
		this.position_x = x;
		this.position_y = y;
		free = false;
		this.direction = 0;
	}
	
	public void read_map(char[][] array)
	{
		if(array == null)
		{
			LOGGER.log(Level.INFO, "Array is Null Meaning it could not read the file.");
			return;
		}
		else
		{
			LOGGER.log(Level.INFO, "Map was completed.");
			this.maze = array;
		}
	}
	
	public void chk_freedom()
	{
		if(this.position_x == this.target_x && this.position_y == this.target_y)
		{
			this.free = true;
		}
	}
	
	private boolean North()
	{
		if(maze[this.position_x - 1][this.position_y] == '0') {
			maze[this.position_x][this.position_y] = '*';
			this.position_x--;
			direction = 0;
			return true;
		}
		else if(maze[this.position_x - 1][this.position_y] == '*')
		{
			if(maze[this.position_x][this.position_y - 1] == '0')
			{
				LOGGER.info("Turning West");
				maze[this.position_x][this.position_y] = '*';
				direction = 3;
				return false;
			}
			else if(maze[this.position_x][this.position_y + 1] == '0')
			{
				LOGGER.info("Turning East");
				maze[this.position_x][this.position_y] = '*';
				direction = 1;
				return false;
			}
			else if(maze[this.position_x - 1][this.position_y] != '1')
			{
				maze[this.position_x][this.position_y] = '*';
				this.position_x--;
				direction = 0;
				return true;
			}
		}
		return false;
	}
	
	private boolean East()
	{
		if(maze[this.position_x][this.position_y + 1] == '0') {
			maze[this.position_x][this.position_y] = '*';
			this.position_y++;
			direction = 1;
			return true;
		}
		else if(maze[this.position_x][this.position_y + 1] == '*')
		{
			if(maze[this.position_x - 1][this.position_y] == '0')
			{
				LOGGER.info("Turning North");
				maze[this.position_x][this.position_y] = '*';
				direction = 0;
				return false;
			}
			else if(maze[this.position_x + 1][this.position_y] == '0')
			{
				LOGGER.info("Turning South");
				maze[this.position_x][this.position_y] = '*';
				direction = 2;
				return false;
			}
			else if(maze[this.position_x][this.position_y + 1] != '1') 
			{
				maze[this.position_x][this.position_y] = '*';
				this.position_y++;
				direction = 1;
				return true;
			}
		}
		return false;
	}
	
	private boolean South()
	{
		if(maze[this.position_x + 1][this.position_y] == '0') {
			maze[this.position_x][this.position_y] = '*';
			this.position_x++;
			direction = 2;
			return true;
		}
		else if(maze[this.position_x + 1][this.position_y] == '*')
		{
			if(maze[this.position_x][this.position_y + 1] == '0')
			{
				LOGGER.info("Turning East");
				maze[this.position_x][this.position_y] = '*';
				direction = 1;
				return false;
			}
			else if(maze[this.position_x][this.position_y - 1] == '0')
			{
				LOGGER.info("Turning West");
				maze[this.position_x][this.position_y] = '*';
				direction = 3;
				return false;
			}
			else if(maze[this.position_x + 1][this.position_y] != '1')
			{
				maze[this.position_x][this.position_y] = '*';
				this.position_x++;
				direction = 2;
				return true;
			}
		}
		return false;
	}
	
	private boolean West()
	{
	
		if(maze[this.position_x][this.position_y - 1] == '0') {
			maze[this.position_x][this.position_y] = '*';
			this.position_y--;
			direction = 3;
			return true;
		}
		else if(maze[this.position_x][this.position_y - 1] == '*')
		{
			if(maze[this.position_x + 1][this.position_y] == '0')
			{
				LOGGER.info("Turning South");
				maze[this.position_x][this.position_y] = '*';
				direction = 2;
				return false;
			}
			else if(maze[this.position_x - 1][this.position_y] == '0')
			{
				LOGGER.info("Turning North");
				maze[this.position_x][this.position_y] = '*';
				direction = 0;
				return false;
			}
			else  if(maze[this.position_x][this.position_y - 1] != '1')
			{
				LOGGER.info("Going Straight");
				maze[this.position_x][this.position_y] = '*';
				this.position_y--;
				direction = 3;
				return true;
			}
		}
		return false;
	}
	
	
	
	public void move()
	{	
		switch(direction)
		{
			case 0: //North
				if(North())
				{
					LOGGER.info("Going North");
				}
				else
				{
					if(West())
					{
						LOGGER.info("Going West");
					}
					else if(East())
					{
						LOGGER.info("Going East");
					}
					else 
					{
						LOGGER.info("Going Back");
						this.direction = 2;
					}
				}
				break;
			case 1: // East
				if(East())
				{
					LOGGER.info("M: Going East");
				}
				else
				{
					if(North())
					{
						LOGGER.info("E: Going North");
					}
					else if(South())
					{
						LOGGER.info("E: Going South");
					}
					else
					{
						LOGGER.info("E: Going Back");
						this.direction = 3;
					}
				}
				break;
			case 2: // South
				if(South())
				{
					LOGGER.info("M:Going South");
				}
				else
				{
					if(East())
					{
						LOGGER.info("S: Going East");
					}
					else if(West())
					{
						LOGGER.info("S: Going West");
					}
					else
					{
						LOGGER.info("S: Going Back");
						this.direction = 0;
					}
				}
				break;
			case 3: // West
				if(West())
				{
					LOGGER.info("M: Going West");
				}
				else
				{
					if(South())
					{
						LOGGER.info("W: Going South");
					}
					else if(North())
					{
						LOGGER.info("W: Going North");
					}
					else
					{
						LOGGER.info("W: Going Back");
						this.direction = 1;
					}
				}
				break;		
		}
	}
	
	private int[] random_position()
	{
		Random rand = new Random();
		while(true)
		{
			int x = rand.nextInt(maze.length);
			int y = rand.nextInt(maze[0].length);
			if(maze[x][y] == '0')
			{
				return new int[] {x , y};
			}
		}
	}
	
	public void random_target() {
		LOGGER.log(Level.INFO, "Setting Target.");
		int[] postion = random_position();
		target_x = postion[0];
		target_y = postion[1];
	}

	public void random_postion() {
		LOGGER.log(Level.INFO, "Setting Mouse Down.");
		int[] postion = null;
		do
		{
			postion = random_position();
		}
		while(postion[0] == target_x && postion[1] == this.target_y);
		this.position_x = postion[0];
		this.position_y = postion[1];	
	}
	
	public void print_maze()
	{
		int curr_row = 0;
		for(char[] row : maze)
		{
			int curr_col = 0;
			for(char index : row)
			{
				
				if(this.position_y == curr_col && this.position_x == curr_row)
				{
					System.out.print("R ");
				}
				else if(this.target_y == curr_col && this.target_x == curr_row)
				{
					System.out.print("T ");
				}
				else
				{
					if(index == '0')
					{
						System.out.print("  ");
					}else
					{
						System.out.print(index +" ");
					}	
				}
				curr_col++;
			}
			curr_row++;
			System.out.println();
		}
	}
	public boolean is_free()
	{
		return this.free;
	}

	public void find_target() {
		
	}
}