
public class Card{
    private boolean isCredit;
    private double balance;

    private String number;
    private String authNumber;

    public Card(double balance, boolean isCredit,String number, String authNumber){
        this.balance = balance;
        this.isCredit = isCredit;
        this.number = number;
        this.authNumber = number;
    }
    public boolean getIsCredit(){
        return this.isCredit;
    }
    public double getBalance(){
        return this.balance;
    }
    public boolean hasSufficientFunds(double charge){
        return charge <= this.balance;
    }
    public boolean charge(double payment){
        if(!this.hasSufficientFunds(payment)){
            return false;
        }
        // else can pay
        this.balance -= payment;
        return true;
    }
}