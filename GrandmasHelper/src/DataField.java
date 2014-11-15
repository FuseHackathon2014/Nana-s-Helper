import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DataField {

	private String name;
	private double minimumValue;
	private double maximumValue;
	private double minimumRate;
	private double maximumRate;
	private double currentSlope;
	private double currentValue;
	private int measurementCount;
	private double frequency;
	private long[] times;
	private double[] values;
	private String comments;
	private boolean done;
	PrintWriter outputTimes;
	PrintWriter outputValues;
	PrintWriter commentPage;

	public DataField(String title, boolean completed, double freq,double evidence, double max, double min, double maxRate, double minRate, int counter) throws InterruptedException, IOException {
		this.name=title;
		this.done=completed;
		this.frequency=freq;
		this.maximumRate=maxRate;
		this.minimumRate=minRate;
		this.minimumValue=min;
		this.maximumValue=max;
		this.currentSlope=0;
		this.currentValue=evidence;
		this.setMeasurementCount(counter);
		this.comments="";
		this.times=new long[96];
		this.values=new double[96];
		this.outputTimes=new PrintWriter(new FileWriter("data/output/Times.txt")); 
		this.commentPage = new PrintWriter(new FileWriter("data/output/Comments.txt"));
		this.outputValues = new PrintWriter( new FileWriter("data/output/Vals.txt"));
		TimerThread ti=new TimerThread(24*60*60*1000/freq, title, " timer has gone off!");
		ti.start();
		ti.join(100);
		ti.interrupt();
	}
	public double getCurrentSlope(){
		return this.currentSlope;
	}
	
	
	public void setCurrentSlope(double result){
		this.currentSlope=result;
	}
public double[] getValues() {
		return this.values;
		
	}
public long[] getTime() {
		return this.times;
		
	}
public String getName()
{
	return this.name;
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
public double getSlope(){	
	return this.calcSlope(1, 2, 2, 1);	
}
public void setMax(double ceiling){
	maximumValue=ceiling;
	}
public double getmax(){
	return this.maximumValue;
}
public void setMin(double floor){
	minimumValue=floor;
}
public double getmin(){
	return this.minimumValue;
}
public void setMaxSlope(double bigO){
	maximumRate=bigO;
}
public double getmaxSlope(){
	return this.maximumRate;
}
public void setMinSlope(double bigOmega){
	minimumRate=bigOmega;
}
public double getminSlope(){
	return this.minimumRate;
}
public double getValue(){
	return this.currentValue;
}
public double takeMeasurement(Double amount, int placeInArray) throws IOException{
	long timer = (long) System.currentTimeMillis();
	this.times[placeInArray]=timer;
	currentValue=amount;
	this.values[placeInArray]=amount;
	setMeasurementCount(getMeasurementCount() + 1);
	this.updateFile(placeInArray);
	return currentValue;	
}
public void updateFile(int placeInArray) throws IOException{
	int i=placeInArray;
	StringBuilder s = new StringBuilder();
	s.append(values[i]);
	s.append(", ");
	StringBuilder t = new StringBuilder();
	t.append(times[i]);
	t.append(", ");
	String valuer=s.toString();
	String timeliness = t.toString();
	(outputValues).println(valuer);
	(outputTimes).println(timeliness);
	System.out.println("Print done!\n");
	//APPEND TO FILES
	i=0;
	if(getMeasurementCount()==72){
		for(int k=0; k<60; k++){
			times[k]=0;
			values[k]=0;
		}
	for(int j=getMeasurementCount()-12; j<getMeasurementCount(); j++){
		times[i]=times[j];
		values[i]=values[j];
		i++;
	}
	setMeasurementCount(12);
		//SO WE CAN STILL TAKE A DECENT LOOK AT THE SLOPE
	}
}
public double calcSlope(long oldTime, long time, double oldSize, double size){
	double net=(size-oldSize);
	double netTime=(double)time-oldTime;
	double result = net/netTime;
	this.setCurrentSlope(result);
	return this.currentSlope;
	}
public void enterComments(String Commentary) throws IOException{
	(commentPage).print((Commentary));
}
public String getComments(){
	return comments;
}
public int getMeasurementCount() {
	return measurementCount;
}
public void setMeasurementCount(int measurementCount) {
	this.measurementCount = measurementCount;
}
}
