import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.security.KeyPairGenerator;

public class Community {
    static Map<String, User> map = new HashMap<String, User>();
    static ArrayList<Transaction> transactionsOnWaitList = new ArrayList<>();
    static ArrayList<Transaction> transactionsOnProcess = new ArrayList<>();
    static final BlockChain blockChain = new BlockChain();
    static Boolean isFirst = true;
    Timer timer = new Timer("timer", 10);
    public int quantityofUsers(){
        return map.size();
    }
    public Boolean isValid(String public_key){return !map.containsKey(public_key);}

    public synchronized double getReward(User user,Block block){
        if(isFirst){
            isFirst = false;
            System.out.println(user.id + " got reward \uD83C\uDFC6");
            block.isSolved = true;
            blockChain.blocks.add(block);
            transactionsOnProcess = new ArrayList<>();
            return 0.25;
        }else{
            return 0;
        }
    }
    class User extends Thread{
        public final int id;
        public final String[] type = {"MINER","TRADER"};
        public String usertype;
        public String public_key = null;
        public String private_key = null;
        public Wallet wallet;
        public User() throws NoSuchAlgorithmException {
            super();
            generateKeys();
            this.wallet = new Wallet(0,this.public_key,this.private_key);
            map.put(this.public_key,this);
            this.id = quantityofUsers();
        }
        public void generateKeys() throws NoSuchAlgorithmException {
            if(this.public_key != null || this.private_key != null){
                map.remove(this.public_key);
            }
            do {
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(512);
                KeyPair kp = kpg.generateKeyPair();
                Base64.Encoder encoder = Base64.getEncoder();
                this.public_key = encoder.encodeToString(kp.getPublic().getEncoded());
                this.private_key = encoder.encodeToString(kp.getPrivate().getEncoded());
            }while(!isValid(public_key));
            map.put(this.public_key,this);
        }
        public void walletTopUp(double amount) throws NoSuchAlgorithmException {
            map.remove(this.public_key);
            this.generateKeys();
            this.wallet = new Wallet(this.wallet.balance + amount,this.public_key,this.private_key);
        }
        public void send(String sender,double amount) throws SelfTransactionException, NotEnoughBalanceException, UserNotFoundException, NoSuchAlgorithmException {
            if (amount > wallet.balance){
                throw new NotEnoughBalanceException("NotEnoughBalance: "+this.id);
            }
            else if(Objects.equals(sender, this.public_key)){
                throw new SelfTransactionException("SelfTransaction: "+this.id);
            }else if(isValid(sender)){
                throw new UserNotFoundException("UserNotFound: "+this.id);
            }else{
                Transaction transaction = new Transaction(map.get(sender),this, amount);
                transactionsOnWaitList.add(transaction);
            }
        }
        public void mine(){
            try {
                if(transactionsOnProcess.size()!=0){
                    int newBlockId = blockChain.blocks.size() + 1;
                    MerkleTree merkleTree = new MerkleTree();
                    for (int i = 0; i < transactionsOnProcess.size(); i++) {
                        merkleTree.addTransaction(transactionsOnProcess.get(i));
                    }
                    String oldHash = null;
                    if(newBlockId != 1) {
                        oldHash = blockChain.blocks.get(blockChain.blocks.size() - 1).currentHash;
                    }else{
                        oldHash = "0000000000000000000000000000000000000000000000000000000000000000";
                    }
                    sleep(500);
                    String finalMerkleTree = merkleTree.calculateRootTx();
                    Block myBlock = null;
                    myBlock = new Block(newBlockId, finalMerkleTree,oldHash);
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    int nonce = 0;
                    do {
                        String blockHash = Integer.toString(myBlock.id) + myBlock.data + Integer.toString(nonce) + myBlock.previousHash;
                        byte[] hash = digest.digest(blockHash.getBytes(StandardCharsets.UTF_8));
                        myBlock.currentHash = Base64.getEncoder().encodeToString(hash);
                        nonce++;
                        if(timer.delay == 2){
                            return;
                        }
                        sleep(new Random().nextInt(3));
                    } while (!myBlock.currentHash.startsWith("00"));
                    this.walletTopUp(getReward(this,myBlock));
                }
            }catch ( NoSuchAlgorithmException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        public void run(){
            try{
                int rnd = new Random().nextInt(type.length);
                this.usertype = type[rnd];
                while(true) {
                    if (Objects.equals(this.usertype, "TRADER")){
                        if (this.wallet.balance < 10) {
                            int amount = new Random().nextInt(100);
                            this.walletTopUp(amount);
                            System.out.println(this.id + " \uD83D\uDC5B " + "← " + amount);
                        } else {
                            Object[] users = map.values().toArray();
                            User randomUser = (User)users[new Random().nextInt(users.length)];
                            double amount = new Random().nextDouble(1,this.wallet.balance);
                            this.send(randomUser.public_key, amount);
                            System.out.println(this.id + " ⟶ \uD83D\uDCB0 " + randomUser.id + ": " + amount);
                        }
                        sleep(new Random().nextInt(1000,10000));
                    }else{
                        break;
                    }
                }
                if (Objects.equals(this.usertype, "MINER")) {
                    while (true) {
                        Thread.sleep(200);
                        if(timer.delay <= 0){
                            transactionsOnProcess = transactionsOnWaitList;
                            transactionsOnWaitList = new ArrayList<>();
                            this.mine();
                        } else if (timer.delay == 5) {
                            isFirst = true;
                        }
                    }
                }
            } catch (SelfTransactionException | NotEnoughBalanceException | UserNotFoundException |
                        NoSuchAlgorithmException | InterruptedException e) {
                    System.out.print("");
            }
        }
    }
}
