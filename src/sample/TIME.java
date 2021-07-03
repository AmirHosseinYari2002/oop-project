package sample;

import javafx.scene.control.Label;

import java.util.Calendar;

public class TIME {
    int n;

    public TIME(int n) {
        this.n = n;
    }

    public TIME(TIME time){
        this.n = time.n;
    }


    public static int diff(TIME time1, TIME time2){
        return Math.abs(time1.n- time2.n);
    }

    public static void showTime(Label label){
        Thread t1 = new Thread() {
            public void run() {
                try {
                    for(;;) {
                        Calendar cal = Calendar.getInstance();
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);
                        int second = cal.get(Calendar.SECOND);
                        label.setText("Time: "+hour + ":" + minute + ":" + second);
                        label.setVisible(true);
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };t1.start();
    }
}
