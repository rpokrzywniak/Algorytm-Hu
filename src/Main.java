
public class Main
{
	
	
  public static void error(String text)
  {
	    System.out.println(text);
	    System.exit(0);
  }
	  
  public static void main(String args[])
  {
    System.out.println("HU\n");
   
    FileData.getData("dane.txt");
    MachineManager.setSchedule();
    MachineManager.showScheldue();
  }
}