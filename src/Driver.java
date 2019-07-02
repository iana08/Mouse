import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		
		if(args.length<0)
		{
			System.out.println("Needs a Map.");
			return;
		}
		
		Mouse mouse = new Mouse();
		
		try {
			mouse.read_map(Read_Map.read_map(args[0]));
			
			mouse.random_target();
			
			mouse.random_postion();
			
			mouse.find_target();
			
			while(!mouse.is_free())
			{	
				mouse.move();
				
				mouse.print_maze();
				
				mouse.chk_freedom();
				
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("\n\n\n");
			}
			
			System.out.println("\n\n\n");
			
			System.out.println("I AM FREE");
			
			
		} catch (FileNotFoundException e) {
			System.err.println("Could not Read File.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Something else happened while reading.");
			e.printStackTrace();
		}
		

	}

}
