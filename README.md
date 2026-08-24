# Padrão Observer — Exemplo em Java

Este projeto apresenta uma implementação do padrão de projeto **Observer (Observador)** em Java, utilizando como referência conceitual o padrão descrito por **Gamma et al.** em *Padrões de Projeto: Soluções Reutilizáveis de Software Orientado a Objetos*.

## Sobre o padrão Observer

O **Observer** é um padrão de projeto comportamental que estabelece uma relação de dependência **um-para-muitos** entre objetos.

Quando o estado de um objeto observado é alterado, os objetos interessados nessa mudança são automaticamente notificados.

A estrutura básica é composta por:

* **Subject:** mantém e gerencia os Observers.
* **ConcreteSubject:** possui o estado que está sendo observado.
* **Observer:** define a interface utilizada para receber as notificações.
* **ConcreteObserver:** implementa a reação à mudança do Subject.

## Exemplo utilizado

O projeto utiliza um sistema de relógios.

O `ClockTimer` mantém o horário atual e desempenha o papel de **ConcreteSubject**.

Dois objetos observam suas alterações:

* `DigitalClock` — **ConcreteObserver**
* `AnalogClock`  — **ConcreteObserver**

Quando o estado do `ClockTimer` é alterado, os Observers registrados são notificados e atualizam suas representações do horário.

### Subject.java

Responsável por manter a lista de Observers e disponibilizar as operações:

* `attach()` — registra um Observer;
* `detach()` — remove um Observer;
* `notifyObservers()` — notifica os Observers registrados.

### Observer.java

Interface que determina a operação:

```java
void update(Subject theChangedSubject);
```

Todo objeto que deseja receber notificações do Subject deve implementar essa interface.

### ClockTimer.java

Representa o **ConcreteSubject**.

Mantém o estado:

```text
hour
minute
second
```

O método `tick()` altera o horário e, após a mudança, solicita a notificação dos Observers.

### DigitalClock.java e AnalogClock.java

Representam os **ConcreteObservers**.

Ao serem criados, registram-se no `ClockTimer` utilizando `attach()`.

Quando recebem `update()`, consultam o estado atual do `ClockTimer` e executam `draw()` para atualizar sua representação.


## Fluxo do Observer

O funcionamento pode ser resumido da seguinte maneira:

```text
1. Subject é criado
        ↓
2. Observers se registram
        ↓
3. Estado do ConcreteSubject muda
        ↓
4. Subject executa notifyObservers()
        ↓
5. update() é chamado nos Observers
        ↓
6. Cada ConcreteObserver reage à mudança
```

No exemplo:

```text
      ClockTimer
          ↓
        tick()
          ↓
   notifyObservers()
          ↓
   Observer.update()
     ↙        ↘
 Digital     Analog
  Clock       Clock
     ↘        ↙
    ClockTimer
       Pull
```

## Estratégia Pull

Esta implementação utiliza a estratégia **Pull**.

O Subject informa aos Observers que ocorreu uma alteração, mas não envia todos os valores diretamente.

Depois de receber `update()`, cada Observer consulta o `ClockTimer` para obter:

```java
timer.getHour();
timer.getMinute();
timer.getSecond();
```

Assim:

```text
Subject → "meu estado mudou" → Observer
                              ↓
                         consulta
                              ↓
                           Subject
```

### Pull x Push

**Pull:** o Observer consulta o Subject após receber a notificação.

**Push:** o Subject envia os dados necessários diretamente para o Observer durante a notificação.

Neste projeto foi adotado o modelo **Pull**.

## Principal vantagem

O `ClockTimer` não precisa conhecer diretamente `DigitalClock` ou `AnalogClock`.

Ele trabalha com a abstração:

```java
Observer
```

Dessa forma, novos Observers podem ser adicionados sem que o `ClockTimer` precise conhecer suas implementações concretas, reduzindo o **acoplamento** entre os componentes.

## Referência

GAMMA, Erich; HELM, Richard; JOHNSON, Ralph; VLISSIDES, John. **Padrões de Projeto: soluções reutilizáveis de software orientado a objetos**. Porto Alegre: Bookman.
(2002, p. 281–283).