
class TimerThread extends Thread {
         private double howLong;
		private String nameThing;
		private String finalMsg;

		TimerThread(double howLong, String nameThing, String finalMsg) {
        	 this.howLong=howLong;
        	 this.nameThing=nameThing;
        	 this.finalMsg=finalMsg;
        	 
         }

         public void run() {
        	 Alarm clockFace=new Alarm(howLong, nameThing, nameThing);
        	 double start=System.currentTimeMillis();
     		double stop=start+howLong;
     		while(stop>System.currentTimeMillis()){
                //NADA
     		}  
     	  		clockFace.alarmWentOff();
     }
}
 