import java.util.Iterator;
import org.javastack.kvstore.KVStoreFactory;
import org.javastack.kvstore.Options;
import org.javastack.kvstore.holders.IntHolder;
import org.javastack.kvstore.holders.StringHolder;
import org.javastack.kvstore.structures.btree.BplusTree;
import org.javastack.kvstore.structures.btree.BplusTree.TreeEntry;
import org.javastack.kvstore.structures.btree.BplusTreeFile;

public class Example {


    private static final String btreeFile = "./test";

    //
    public static void main(final String[] args) throws Exception {
        final int[] keys = new int[] { 5, 7, -11, 111, 0 };

        //
//        KVStoreFactory<IntHolder, IntHolder> factory = new KVStoreFactory<IntHolder, IntHolder>(
//                IntHolder.class, IntHolder.class);
        KVStoreFactory<IntHolder, StringHolder> factory = new KVStoreFactory<IntHolder, StringHolder>(
                IntHolder.class, StringHolder.class);
        Options opts = factory.createTreeOptionsDefault()
                .set(KVStoreFactory.FILENAME, btreeFile);
        BplusTreeFile<IntHolder, StringHolder> tree = factory.createTreeFile(opts);
        //
        // Open & Recovery tree if needed
        try {
            if (tree.recovery(false) && tree.open())
                System.out.println("open tree ok");
        } catch (BplusTree.InvalidDataException e) {
            System.out.println("open tree error, recovery needed");
            if (tree.recovery(false) && tree.open()) {
                System.out.println("recovery ok, tree opened");
            } else {
                throw new Exception("Fatal Error Opening Tree");
            }
        }
        // clear all previous content if any
        tree.clear();
        // ============== PUT
        for (int i = 0; i < keys.length; i++) {
            final IntHolder key = IntHolder.valueOf(keys[i]);
//            final IntHolder value = IntHolder.valueOf(i);
            final StringHolder value = StringHolder.valueOf(String.valueOf(i));
            tree.put(key, value);
        }
        tree.sync();
        // ============== GET
        System.out.println("tree.get(7)=" + tree.get(IntHolder.valueOf(7)));
        // ============== REMOVE
        tree.remove(IntHolder.valueOf(7));
        // ============== ITERATOR
        for (Iterator<TreeEntry<IntHolder, StringHolder>> i = tree.iterator(); i
                .hasNext();) {
            TreeEntry<IntHolder, StringHolder> e = i.next();
            System.out.println("Key=" + e.getKey() + " Value=" + e.getValue());
        }
        // ============== FIRST / LAST
        System.out.println("tree.firstKey()=" + tree.firstKey());
        System.out.println("tree.lastKey()=" + tree.lastKey());
        //
        tree.sync();
        tree.close();
    }

}
