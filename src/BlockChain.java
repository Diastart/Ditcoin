import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BlockChain {
    ArrayList<Block> blocks = new ArrayList<>();
    public void addNewBlock(String merkleTree) throws NoSuchAlgorithmException {
        if (blocks.size() == 0){
            blocks.add(new Block(1,merkleTree));
        }else{
            blocks.add(new Block(blocks.size()+1,merkleTree,blocks.get(blocks.size()-1).currentHash));
        }
    }
}
