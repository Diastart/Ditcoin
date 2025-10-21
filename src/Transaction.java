import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Transaction {
    private Boolean isDone = false;
    public static int counter = 0;
    public int id;
    Community.User sender;
    Community.User receiver;
    String datetime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    public double amount;
    public Transaction(Community.User sender, Community.User receiver, double amount) throws NoSuchAlgorithmException {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.transact();
        this.id = ++counter;
    }
    private void transact() throws NoSuchAlgorithmException{
        if(!this.isDone){
            if(!Objects.equals(this.sender.wallet.wallet_key, this.sender.private_key)){
                return;
            }
            this.sender.generateKeys();
            this.sender.wallet = new Wallet(this.sender.wallet.balance + amount,this.sender.public_key,this.sender.private_key);
            this.receiver.generateKeys();
            this.receiver.wallet = new Wallet(this.receiver.wallet.balance - amount,this.receiver.public_key,this.receiver.private_key);
            this.isDone = true;
        }
    }
}
