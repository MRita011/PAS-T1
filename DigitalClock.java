public class DigitalClock implements Observer {

    private final ClockTimer timer;

    public DigitalClock(ClockTimer timer) {
        this.timer = timer;
        timer.attach(this);
    }

    @Override
    public void update(Subject theChangedSubject) {
        if (theChangedSubject == timer) {
            draw();
        }
    }

    public void draw() {
        System.out.printf(
            "\nRelógio digital: %02d:%02d:%02d%n",
            timer.getHour(),
            timer.getMinute(),
            timer.getSecond()
        );
    }
}