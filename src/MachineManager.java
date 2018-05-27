import java.util.ArrayList;

public class MachineManager
{
  public static ArrayList<Machine> machines = new ArrayList<Machine>();
  public static void addMachine(Machine machine)
  {
    machines.add(machine);
  }

  public static void displayScheduleList(ArrayList<Task> tasksToSchedule)
  {
    if (!tasksToSchedule.isEmpty())
    {
      System.out.print("   ");
      for (Task task : tasksToSchedule)
      {
        if (task.getTaskNumber() == 0)
        {
          System.out.print("||| ");
        }
        else
        {
          String taskToDisplay = "Z" + task.getTaskNumber();
          while (taskToDisplay.length() < 3)
          {
            taskToDisplay = " " + taskToDisplay;
          }
          System.out.print(taskToDisplay + " ");
        }
      }
    }
    else
    {
      System.out.print("empty");
    }
  }

  public static void showScheldue()
  {
    System.out.println("MACHINES:");

    int max = 0;
    for (Machine machine : machines)
    {
      if (max < machine.getSchedule().size())
      {
        max = machine.getSchedule().size();
      }
    }

    System.out.println("Time");
    System.out.print("   ");
    for (int i = 1; i <= max; i++)
    {
      String numberToDisplay = new String();
      numberToDisplay = numberToDisplay + i;
      while (numberToDisplay.length() <  3)
      {
        numberToDisplay = " " + numberToDisplay;
      }
      System.out.print(numberToDisplay + " ");
    }
    System.out.print("\n");

    for (Machine machine : machines)
    {
      System.out.println("Machine " + "M" + machine.getMachineNumber());
      displayScheduleList(machine.getSchedule());
      System.out.print("\n");
    }
  }

  public static void setSchedule()
  {
    while (!TaskManager.isFinished(TaskManager.tasks))
    {
      ArrayList<Task> availableTasks = new ArrayList<Task>();
      ArrayList<Task> tasksToMachines = new ArrayList<Task>();

      for (Task task : TaskManager.tasks)
        if (task.getPrevTasks().isEmpty() && !task.getIsCompleted())
          availableTasks.add(task);
    
      System.out.println("\nNEXT ITERATION");
      for (Task task : availableTasks){
    	  System.out.println(task.getTaskNumber());
      }

      if (availableTasks.size() <= machines.size())
      {
        tasksToMachines = availableTasks;
        while (tasksToMachines.size() < machines.size())
        {
          tasksToMachines.add(new Task(0));
        }
      }
      else
      {
        for (int i = 0; i < machines.size(); i++)
        {
          tasksToMachines.add(i, availableTasks.get(i));
        }
      }
      System.out.println("\nTASKS TO MACHINES");
      for (Task task : tasksToMachines){
    	  System.out.println(task.getTaskNumber());
      }

      for (int i = 0; i < machines.size(); i++)
      {
        Task taskToProceed = tasksToMachines.get(i);
        machines.get(i).getSchedule().add(taskToProceed);
        TaskManager.completeTaskByNumber(taskToProceed.getTaskNumber());
      }
    }
  }
}
