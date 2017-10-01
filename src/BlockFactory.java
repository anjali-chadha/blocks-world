import java.util.ArrayList;
import java.util.List;

public class BlockFactory {

	private List<Block> blockList;

	BlockFactory(int blockCount) {
		
		blockList = new ArrayList<>(blockCount);
		char i = 'A';
		while(i < 'A' + blockCount) {
			blockList.add(new Block(i));
			i++;
		}
	}
	
	List<Block> getBlockList() {
		return blockList;
	}

	Block getBlock(char a) {
	    return blockList.get(a -'A');
    }

}
