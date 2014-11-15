public class Alarm {
	private double howLong;
	private String name;
	private String alarmMessage;
	private boolean running;
	
	public Alarm(double length, String nameGiven, String alarmMessageGiven) {
		name=nameGiven;
		howLong=length;		
		running=false;
		alarmMessage=alarmMessageGiven;
	}
	
	
public void alarmWentOff(){
	System.out.println(name+alarmMessage);	
}
}
