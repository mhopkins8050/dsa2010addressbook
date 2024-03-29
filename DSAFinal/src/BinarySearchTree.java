import java.util.ArrayList;
import java.io.Serializable;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 * 
 * 
 * @Modified by Tom Renn
 * Included Searches by name/address/phone_number of AddressEntry's
 * AddressEntry implements Comparable and compares the name of the AddressEntry. 
 * 		Thus BinarySearchTree is sorted by the names of the AddressEntry
 */
public class BinarySearchTree implements Serializable {
    /**
     * Construct the tree.
     */
    public BinarySearchTree( ) {
        root = null;
    }
    
    
  /// get all the entries starting from the root node
    public ArrayList<AddressEntry> preorder(BinaryNode root) {
            
            ArrayList<AddressEntry> entries = new ArrayList<AddressEntry>();
            
            BinaryNode left = root.left;
            BinaryNode right = root.right;
            entries.add((AddressEntry)root.element);
            
            if(left != null)
                    entries.addAll(preorder(left));
            if(right != null)
                    entries.addAll(preorder(right));
            
            return entries;
            }
    
    /* INDIVIDUAL SEARCH METHODS */
    
    /**
     * - Search for name
     */
    public Object[] searchName(String searchterm, boolean exact)
	{
		return findName(root, searchterm, exact).toArray();
	}
    
    // goes through the tree to find matching names
    public ArrayList<AddressEntry> findName(BinaryNode root, String searchterm, boolean exact){
		ArrayList<AddressEntry> entries = new ArrayList<AddressEntry>();
		
		BinaryNode left = root.left;
		BinaryNode right = root.right;
		
		if (exact && ((AddressEntry)root.element).getName().equalsIgnoreCase(searchterm))
			entries.add((AddressEntry)root.element);
		if (!exact && ((AddressEntry)root.element).getName().toLowerCase().contains(searchterm.toLowerCase()))
			entries.add((AddressEntry)root.element);
		
		if(left != null)
				entries.addAll(findName(left, searchterm, exact));
		
		// ----
		if(right != null)
				entries.addAll(findName(right, searchterm, exact));
		
		
		return entries;
    }
    
    /**
     * - Search by Age
     */
    public Object[] searchAge(String searchterm, boolean exact)
	{
		return null;
	}
    /* No age yet implemented so we cannot write searchAge */
    
    /**
     * 	- Search by Phone Numbers
     * 
     * @param searchterm Phone number to search
     * @param exact If the phone should be an exact match
     * @return Object[] of AddressEntry's
     */
	public Object[] searchPhone(String searchterm, boolean exact)
	{
		return findPhone(root, searchterm, exact).toArray();
	}
    
	/**
	 * - Recusively looks for AddressEntry's with particular phone number
	 * @param root The AddressEntry to look at
	 * @param searchterm Phone number to look for
	 * @param exact If we should match the number excatly
	 * @return ArrayList of AddressEntry's
	 */
    public ArrayList<AddressEntry> findPhone(BinaryNode root, String searchterm, boolean exact){
		ArrayList<AddressEntry> entries = new ArrayList<AddressEntry>();
		
		BinaryNode left = root.left;
		BinaryNode right = root.right;
		
		if (exact && ((AddressEntry)root.element).getPhone().equalsIgnoreCase(searchterm))
			entries.add((AddressEntry)root.element);
		if (!exact && ((AddressEntry)root.element).getPhone().toLowerCase().contains(searchterm.toLowerCase()))
			entries.add((AddressEntry)root.element);
		
		if(left != null)
				entries.addAll(findPhone(left, searchterm, exact));
		
		// ----
		if(right != null)
				entries.addAll(findPhone(right, searchterm, exact));
		
		
		return entries;
    }
   
    
    /**
     * - Search by Address
     * 
     * @param searchterm Address to look for
     * @param exact match the address excatly
     * @return AddressEntry's that match
     */
    public Object[] searchAddress(String searchterm, boolean exact)
	{
		return findAddress(root, searchterm, exact).toArray();
	}
    
    /**
     *  - Recusively look for particular address entry
     */
    public ArrayList<AddressEntry> findAddress(BinaryNode root, String searchterm, boolean exact){
		ArrayList<AddressEntry> entries = new ArrayList<AddressEntry>();
		
		BinaryNode left = root.left;
		BinaryNode right = root.right;
		
		if (exact && ((AddressEntry)root.element).getAddress().equalsIgnoreCase(searchterm))
			entries.add((AddressEntry)root.element);
		if (!exact && ((AddressEntry)root.element).getAddress().toLowerCase().contains(searchterm.toLowerCase()))
			entries.add((AddressEntry)root.element);
		
		if(left != null)
				entries.addAll(findAddress(left, searchterm, exact));
		
		// ----
		if(right != null)
				entries.addAll(findAddress(right, searchterm, exact));
		
		
		return entries;
    }
    
    /* END OF SEARCH METHODS */
    
    
    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert( Comparable x ) {
        root = insert( x, root );
    }
    
    /**
     * Remove from the tree..
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove( Comparable x ) throws ItemNotFoundException {
        root = remove( x, root );
    }
    
    /**
     * Remove minimum item from the tree.
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin( ) {
        root = removeMin( root );
    }
    
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable findMin( ) {
        return elementAt( findMin( root ) );
    }
    
    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public Comparable findMax( ) {
        return elementAt( findMax( root ) );
    }
    
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable find( Comparable x ) {
        return elementAt( find( x, root ) );
    }
    
    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( ) {
        root = null;
    }
    
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( ) {
        return root == null;
    }
    
    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private Comparable elementAt( BinaryNode t ) {
        return t == null ? null : t.element;
    }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode insert( Comparable x, BinaryNode t ) throws DuplicateItemException{
        if( t == null )
            t = new BinaryNode( x );
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right );
        else
            throw new DuplicateItemException( x.toString( ) );  // Duplicate
        return t;
    }
    
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode remove( Comparable x, BinaryNode t ) throws ItemNotFoundException {
        if( t == null )
            throw new ItemNotFoundException( x.toString( ) );
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = removeMin( t.right );
        } else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }
    
    /**
     * Internal method to remove minimum item from a subtree.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode removeMin( BinaryNode t ) {
        if( t == null )
            throw new ItemNotFoundException( );
        else if( t.left != null ) {
            t.left = removeMin( t.left );
            return t;
        } else
            return t.right;
    }
    
    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode findMin( BinaryNode t ) {
        if( t != null )
            while( t.left != null )
                t = t.left;
        
        return t;
    }
    
    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode findMax( BinaryNode t ) {
        if( t != null )
            while( t.right != null )
                t = t.right;
        
        return t;
    }
    
    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode find( Comparable x, BinaryNode t ) {
        while( t != null ) {
            if( x.compareTo( t.element ) < 0 )
                t = t.left;
            else if( x.compareTo( t.element ) > 0 )
                t = t.right;
            else
                return t;    // Match
        }
        
        return null;         // Not found
    }
    
    /** The tree root. */
    protected BinaryNode root;
    
    
    // Test program
    public static void main( String [ ] args ) {
        BinarySearchTree t = new BinarySearchTree( );
        final int NUMS = 4000;
        final int GAP  =   37;
        
        System.out.println( "Checking... (no more output means success)" );
        
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( new Integer( i ) );
        
        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( new Integer( i ) );
        
        if( ((Integer)(t.findMin( ))).intValue( ) != 2 ||
                ((Integer)(t.findMax( ))).intValue( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );
        
        for( int i = 2; i < NUMS; i+=2 )
            if( ((Integer)(t.find( new Integer( i ) ))).intValue( ) != i )
                System.out.println( "Find error1!" );
        
        for( int i = 1; i < NUMS; i+=2 ) {
            if( t.find( new Integer( i ) ) != null )
                System.out.println( "Find error2!" );
        }
    }
}


// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of this package.

class BinaryNode implements Serializable {
    // Constructors
    BinaryNode( Comparable theElement ) {
        element = theElement;
        left = right = null;
    }
    
    // Friendly data; accessible by other package routines
    Comparable element;      // The data in the node
    BinaryNode left;         // Left child
    BinaryNode right;        // Right child
}


/**
 * Exception class for duplicate item errors
 * in search tree insertions.
 * @author Mark Allen Weiss
 */
class DuplicateItemException extends RuntimeException {
    /**
     * Construct this exception object.
     */
    public DuplicateItemException( ) {
        super( );
    }
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public DuplicateItemException( String message ) {
        super( message );
    }
}


/**
 * Exception class for failed finds/removes in search
 * trees, hash tables, and list and tree iterators.
 * @author Mark Allen Weiss
 */
class ItemNotFoundException extends RuntimeException {
    /**
     * Construct this exception object.
     */
    public ItemNotFoundException( ) {
        super( );
    }
    
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public ItemNotFoundException( String message ) {
        super( message );
    }
}