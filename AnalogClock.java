public class AnalogClock implements Observer {

    private final ClockTimer timer;

    public AnalogClock(ClockTimer timer) {
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
            "Relógio analógico atualizado: %02d:%02d:%02d%n\n",
            timer.getHour(),
            timer.getMinute(),
            timer.getSecond()
        );
    }
}