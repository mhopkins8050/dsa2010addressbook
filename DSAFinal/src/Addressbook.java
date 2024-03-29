import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

/**
 * Holds all the AddressEntry's for contacts
 *
 **
 * Authors:     Thomas F. Renn, Edward H. Springer
 * Date:       Tuesday, December 21, 2010
 * Course:     Data Structures (CS260) 
 * File:       Addressbook.java
 * Purpose:    Holds the entries for the BookDisplay
 * Known bugs: None 
 * Compiler:   Java 6
 * Operating System: 
 *             Windows 7 
 * Special Instructions: 
 *             None 
 * E-mail:     rennt79@students.rowan.edu,
 * 			   spring72@students.rowan.edu
 */
public class Addressbook {
	private BinarySearchTree entries;
	public static final int SEARCH_NAME = 0;
	public static final int SEARCH_PHONE = 1;
	public static final int SEARCH_ADDRESS = 2;
	public static final int SEARCH_AGE = 3;
	
	// constructor
	public Addressbook(){
		if(SerializationHandler.deserialize() == null){ // if there is no save, create some filler entries
			entries = new BinarySearchTree();
			addNewEntry("Tom Renn", "7325510804", "14 Mirta Ct");
			addNewEntry("Edward Springer", "7325555555", "101 Redmond Ave");
			addNewEntry("John Smith", "9285111111", "Outer Space");
			addNewEntry("Kevin Flynn", "07091982", "The Game Grid");
		}
		else
			entries = SerializationHandler.deserialize();
	}
	
	
	/**
	 * Searchs for something in the address book
	 * 
	 * @param searchterm What to search for
	 * @param SEARCH_FLAG flag for name, phone number, address
	 * @param exact if true, will only return objects that excatly match the searchterm
	 * @return An Object[] of AddressEntry[]
	 */
	public Object[] search(String searchterm, int SEARCH_FLAG, boolean exact){
		Object matches[] = null;
		switch(SEARCH_FLAG){
			case SEARCH_NAME:
				matches = entries.searchName(searchterm, exact);
				break;
			case SEARCH_PHONE:
				matches = entries.searchPhone(searchterm, exact);
				break;
			case SEARCH_ADDRESS:
				matches = entries.searchAddress(searchterm, exact);
				break;
			case SEARCH_AGE:
//				matches = null;
		}
		return matches;
	}
	
	// a basic method to get all entries 
	public Object[] getEntries()
	{
		ArrayList<AddressEntry> abc = entries.preorder(entries.root);
		return abc.toArray();
	}

	// adds new entry to address book or returns false if unable to
	public boolean addNewEntry(String name, String phone, String address){
		try{
			entries.insert(new AddressEntry(name, phone, address));
			return true;
		}
		catch (DuplicateItemException e)
		{
			return false; // entry already exists in address book
		}
	}
	
	// delete an Entry but giving the entry wanted to delete
	public void deleteEntry(AddressEntry entry)
	{
		entries.remove(entry);
	}
	
	// save the BinarySearchTree of entries
	public void save() {
		SerializationHandler.serialize(entries);
	}
	
}
