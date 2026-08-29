import java.time.LocalTime;
public class Main {

    public static void main(String[] args) {

        // pega a hora atual do sistema
        LocalTime agora = LocalTime.now();

        // cria o ConcreteSubject com a hora atual
        ClockTimer timer = new ClockTimer(
            agora.getHour(),
            agora.getMinute(),
            agora.getSecond()
        );

        // cria e registra os ConcreteObservers
        DigitalClock digitalClock = new DigitalClock(timer);
        AnalogClock analogClock = new AnalogClock(timer);

        // mostra o horário inicial
        digitalClock.draw();
        analogClock.draw();

        // simula a passagem de 3 segundos
        timer.tick();
        timer.tick();
        timer.tick();
    }
}

