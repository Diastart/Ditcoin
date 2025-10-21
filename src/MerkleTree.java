import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class MerkleTree {
    ArrayList<String> transactions = new ArrayList<>();
    String rootTx;
    public void addTransaction(Transaction merkle) throws NoSuchAlgorithmException {
        String tx = merkle.id + merkle.sender.public_key + merkle.receiver.public_key + merkle.datetime;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(tx.getBytes(StandardCharsets.UTF_8));
        transactions.add(Base64.getEncoder().encodeToString(hash));
    }
    public int getSize(){
        return transactions.size();
    }
    public String calculateRootTx() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        if(transactions.size() == 1){
            byte[] hash = digest.digest(transactions.get(0).getBytes(StandardCharsets.UTF_8));
            transactions.add(Base64.getEncoder().encodeToString(hash));
            return transactions.get(getSize()-1);
        } else if (transactions.size()==0) {
            return "";
        }
        int i = 1;
        while(this.getSize() > i){
            i = i*2;
        }
        int difference = i - this.getSize();
        for(int j = 0;j<difference;j++){
            this.transactions.add(this.transactions.get(this.getSize()-1));
        }
        int length = 2*getSize()-2;
        for(i=0;i<length;i=i+2){
            String kley = transactions.get(i) +transactions.get(i+1);
            byte[] hash = digest.digest(kley.getBytes(StandardCharsets.UTF_8));
            transactions.add(Base64.getEncoder().encodeToString(hash));
        }
        return transactions.get(getSize()-1);
    }
}
