package ar.edu.unlu.poo.tpfinal;

public interface Subject {
    public void attach(Observer anObserver);
    public void detach(Observer anObserver);
    public void notifyMessage(Evento evento);
}
