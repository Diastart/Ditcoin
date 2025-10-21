public class Wallet {
    public final double balance;
    public final String address;
    public final String wallet_key;

    public Wallet(double balance, String address, String walletKey){
        this.balance = balance;
        this.address = address;
        this.wallet_key = walletKey;
    }

}
