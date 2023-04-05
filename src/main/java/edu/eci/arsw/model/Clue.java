package edu.eci.arsw.model;

public class Clue {
    private String Pista;
    private boolean Take;

    public Clue(){}

    public Clue(String Pista, boolean Take){
        this.Pista = Pista;
        this.Take = Take;
    }

    public boolean getTake(){
        return Take;
    }

    public void setTake(boolean WinnerClue){
        this.Take = WinnerClue;
    }

    public String getPista(){
        return Pista;
    }

    public void setPista(String Pista){
        this.Pista = Pista;
    }

}
