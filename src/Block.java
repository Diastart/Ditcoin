import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HexFormat;
import java.util.Objects;

public class Block {
    public int id;
    private int nonce;
    public String previousHash = "0000000000000000000000000000000000000000000000000000000000000000";
    private final int difficulty = 4;
    public String currentHash;
    public String data;
    public Boolean isSolved = false;

    public Block(int id, String merkleTree) throws NoSuchAlgorithmException {
        this.data = merkleTree+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.id = id;
    }

    public Block(int id, String merkleTree,String previousHash) throws NoSuchAlgorithmException{
        this.data = merkleTree +new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.id = id;
        this.previousHash = previousHash;
    }


}
