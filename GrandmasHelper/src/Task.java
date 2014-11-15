public class Task {

	private String name;
	private double frequency;
	private String comments;
	private boolean done;

	public Task(String title, boolean completed, double freq) throws InterruptedException {
		this.name=title;
		this.done=completed;
		this.frequency=freq;
		this.comments="";
		TimerThread ti=new TimerThread(24*60*60*1000/freq, title, ". It is time for this task!");
		ti.start();
		ti.join(100);
		ti.interrupt();
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String label){
		this.name=label;
	}
	public boolean getComplete()
	{
		return done;
	}
	public void setComplete(boolean doneYet){
		done=doneYet;
	}
	public double getFreq(){
		return frequency;
	}
	public void setFreq(double howOften){
		frequency=howOften;
	}
}
