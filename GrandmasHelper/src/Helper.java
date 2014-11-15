import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class Helper {
	
	private Helper() {
	    }

	    /**
	     * InputStream for user commands--console.
	     */
	  private static final java.io.InputStream InputStream = System.in;

	   	    
	    /**
	     * Main method.
	     * 
	     * @param args
	     *            the command line arguments
	     * @throws IOException
	     * @throws InterruptedException 
	     */
	    public static void main(String[] args) throws IOException, InterruptedException {
	    	BufferedReader in = new BufferedReader(new InputStreamReader(
	                InputStream));

	        PrintWriter out = new PrintWriter(System.out, true);
	        ArrayList<DataField>Metrics=new ArrayList<DataField>();
	        ArrayList<Task>ToDos=new ArrayList<Task>();
	        char exit = 'q';
	        char exiteer = 'Q';
	        
	       //TESTY TESTY 1-2-3 TESTY TESTY 1-2-3
	        /*
	       out.println("First: a timer!");
	       TimerThread t = new TimerThread(5000, "so loooong", " ugh");
	       t.start();
			t.join(1000);
			t.interrupt();
			//ach Gott
			*/
			//Ask the user intro questions. To be replaced by Ariane's slick + actual interface:
			out.println("Would you like to create tasks (enter 0), or create metrics to follow (enter 1), or proceed to the main menu (enter 2)?");
			int riposte=Integer.parseInt(in.readLine());
			if(riposte==0){
				createTasks(in, out, ToDos);
			}
			if(riposte==1){
				createMetrics(in, out, Metrics);
			}
			if(riposte==2){
				//GOTO Main Menu
			}
	    		        
	    	//CHANGE VALUES MANUALLY VIA MENU
	        int replyToExitPrompt = 0;
	        int placeInArray=0;
	        while(replyToExitPrompt!=exit&&replyToExitPrompt!=exiteer){
	        	out.println("Here are the things we're tracking. choose one:\n");
	        	
	        	for (DataField ThisData: Metrics){
	        		out.println(ThisData.getName() + " " + Metrics.indexOf(ThisData)+"\n");
	        	}
	        	int responseEntry=Integer.parseInt(in.readLine());
	        	DataField ourData=Metrics.get(responseEntry);
	    	out.println("choose an option from the following: \n1. View patient data\n");
	    	out.println("2. Enter Measurement\n3. Change a max/min value\n4. Enter comments\n5. Check Off Task");
	    	int choice=Integer.parseInt(in.readLine());
	    	char response=1;
	    	if(choice==1){
	    		//show the graphs
	    		out.println("chose output with index "+placeInArray);
	    		displayStuff(ourData, in, out, placeInArray-1);
	    		//to do list
	    	}
	    	else if (choice==2){
	    		out.println("enter measurement\n");
	    		double newMeasure=Double.parseDouble(in.readLine());
	    		ourData.takeMeasurement(newMeasure, placeInArray);
	    		placeInArray++;
	    		//HEY ONLY SOMETIMES
	    		ourData.setComplete(true);
	    	}
	    	
	    	else if (choice==3){
	    		//CHANGING LIMITS N STUFF REALLY MORE OF A DOC THING BUT HE/SHE CAN CALL GRANDMA AND HAVE HER ALTER THE LIMS
	    		out.println("change max(y/n)?");	    		
	    		response=in.readLine().charAt(0);
	    		if(response=='y')
	    		{
	    			out.println("Please enter a new value.\n");
	    			double newVal=Double.parseDouble(in.readLine());
	    		ourData.setMax(newVal);
	    		}
	    		else {
	    			out.println("change min(y/n)?");
		    		response=in.readLine().charAt(0);
		    		if(response=='y'){
	    			out.println("Please enter a new value.\n");
	    			double newVal=Double.parseDouble(in.readLine());
	    		ourData.setMin(newVal);
	    		}
	    		}
	    		out.println("change max slope?");
	    		response=in.readLine().charAt(0);
	    		if(response=='y')
	    		{
	    			double newVal=Double.parseDouble(in.readLine());
		    		ourData.setMaxSlope(newVal);
	    		}
	    		else
	    		{
	    			out.println("change min slope?");
		    		response=in.readLine().charAt(0);
		    		if(response=='y')
		    		{
		    			double newVal=Double.parseDouble(in.readLine());
			    		ourData.setMinSlope(newVal);
		    		}
	    		}
	    	}
	    	else if (choice==4){
	    		out.println("OK go!\n");
	    		String situation = in.readLine();
	    		ourData.enterComments(situation);
	    	}
	    	else if (choice==5){
	    		out.println("which task have you completed? Enter the accompanying integer");
	    		for(Task thing : ToDos){
	    			out.println(thing.getName() + " " + ToDos.indexOf(thing));
	    		}
	    		int sought=Integer.parseInt(in.readLine());
	    		Task Modify = ToDos.remove(sought);
	    		Modify.setComplete(true);
	    		}	    				
	    	else
	    	{
	    		//probably a good idea to try to wrangle user errors
	    		out.println("We'll bring you through to the next screen. You can get back to wherever you need to go from there.");
	    	}
	    		out.println("punch in q or Q to finish entering vitals, or anything else to go to main menu.");
		    	replyToExitPrompt=in.readLine().charAt(0);       
	    }
	        in.close();
	        }

		private static void createMetrics(BufferedReader in, PrintWriter out, ArrayList<DataField>Metrix) throws InterruptedException, IOException {
			char escape='p';
			while(escape!='e'&&escape!='E'){
				out.println("Hey let's create a health value to follow:");
		    	String Named = in.readLine();
		    	out.println("Please enter its max and min values, its max and min rates, and how many times per day to take the measurement. Press enter after each!");
		    	
		    	double max = Double.parseDouble(in.readLine());
		    	double min = Double.parseDouble(in.readLine());
		    	double maxRate = Double.parseDouble(in.readLine());
		    	double minRate=Double.parseDouble(in.readLine());
		    	double frequency = Double.parseDouble(in.readLine());
		    	boolean done=false;
		    	double current=0;
		    	//DEAR GOD I HOPE THAT WORKS//
		    	DataField ourData=new DataField(Named, done, frequency, current, max, min, maxRate, minRate, 0);
		    	Metrix.add(ourData);
		    	out.println("punch in e or E to finish entering vitals, or anything else to go to main menu.");
		    	escape=in.readLine().charAt(0);
			}
		}

		private static void createTasks(BufferedReader in, PrintWriter out, ArrayList<Task> X) throws InterruptedException, NumberFormatException, IOException {
			char escape='p';
			while(escape!='e'&&escape!='E'){
				out.println("Hey let's create a health value to follow:");
		    	String Named = in.readLine();
		    	out.println("Please enter how many times per day to take the measurement.");
		    	double frequency = Double.parseDouble(in.readLine());
		    	boolean done=false;
		    	//DEAR GOD I HOPE THAT WORKS//
		    	Task Duty=new Task(Named, done, frequency);
		    	X.add(Duty);
				out.println("punch in e or E to finish entering new tasks, or anything else to go to main menu.");
		    	escape=in.readLine().charAt(0);
			}
		}

		private static void displayStuff(DataField ourData, BufferedReader in, PrintWriter out, int place) {
			String result="";
			int wayback=1;
			double val = ourData.getValue();
			if(val>ourData.getmax()){
				result=alarmNotice(1, 1);
				out.println(result);
			}
			else if(val<ourData.getmin()){
				result=alarmNotice(1, 0);
				out.println(result);
			}			
			boolean problem=false;
			while(wayback<12&&wayback<ourData.getMeasurementCount()&&!problem){
			double slopeOut=ourData.calcSlope(ourData.getTime()[place-wayback], ourData.getTime()[place], ourData.getValues()[place-wayback], ourData.getValues()[place]);	
			if(slopeOut>ourData.getmaxSlope()){
				result=alarmNotice(0, 1);
				out.println(result);
				problem=true;
			}
			else if (slopeOut<ourData.getminSlope()){
				result=alarmNotice(0, 0);
				out.println(result);
				problem=true;
			}
			wayback++;
			}
		}		
		
		private static String alarmNotice(int i, int j) {
			String alarm = "There is an alarm";
			if (i==0&&j==0)
			{
				alarm +=" because this metric is dropping faster than recommended.";
			}
			else if(i==0&&j==1){
				alarm +=" because this metric is rising faster than recommended.";
			}
			else if(j==0){
				alarm +=" because this metric is lower than recommended.";
			}
			else if (j==1){
				alarm +=" because this metric is higher than recommended.";
			}
			return alarm;
		}	    
	    }

