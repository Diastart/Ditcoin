import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, UserNotFoundException, NotEnoughBalanceException, SelfTransactionException, InterruptedException {
        Community comunity = new Community();
        ArrayList<Community.User> users = new ArrayList<>();
        for(int i = 0; i<10;i++){
            users.add(comunity.new User());
        }
        comunity.timer.start();
        for(int i = 0; i<10;i++){
            users.get(i).start();
        }
    }
}