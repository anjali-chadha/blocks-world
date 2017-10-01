import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * This class represents the stack on which blocks will be placed.
 * Each block has an id associated with it - 1,2,..
 * Each stack has a blockList associated with them. They are being represented by the
 * stack datastructure.
 */
public class BlockStack {
    private int id;
    private Deque<Block> stack;

    private volatile int hashCode; //Cached hash code, Lazily intialised [Item 9]

    BlockStack(int id) {
        this.id = id;
        stack = new ArrayDeque<>();
    }

    BlockStack(int id, Deque<Block> stack) {
        this.id = id;
        this.stack = stack;
    }


    int getId() {
        return id;
    }
    Deque<Block> getStack() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockStack blockStack = (BlockStack) o;

        if (this.id != blockStack.id) return false;
        int n = this.stack.size();
        if( blockStack.stack.size() != n) return false;
        Block[] stack1 = stack.toArray(new Block[n]);
        Block[] stack2 = blockStack.stack.toArray(new Block[n]);
        return Arrays.equals(stack1, stack2);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            result = 17;
            result = 31*result + id;
            for(Block block: stack) {
                result = 31*result + block.hashCode();
            }
            hashCode = result;
        }
        return result;
    }

    @Override
    public String toString() {
        return "BlockStack{" +
                "id=" + id +
                ", stack=" + stack +
                '}';
    }
}
