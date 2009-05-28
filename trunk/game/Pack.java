package game;

import java.util.*;

/**
*Contains maps of all of the pockets which contains the Items and the quantity of each
*/

public class Pack {
	/**
	* contains all of the Key Items
	*/
	private HashMap<Item,Integer> keyItemsPocket;
	/**
	* contains all of the regular items
	*/
	private HashMap<Item,Integer> itemsPocket;
	/**
	* contains all of the TMs and HMs
	*/
	private HashMap<Item,Integer> tmHmPocket;
	/**
	* contains all of the pokeballs
	*/
	private HashMap<Item,Integer> pokeballPocket;
	
	/**
	 *constructor to create empty Pack object
	 */
	public Pack() {
		keyItemsPocket = new HashMap<Item,Integer>();
		itemsPocket = new HashMap<Item,Integer>();
		tmHmPocket = new HashMap<Item,Integer>();
		pokeballPocket = new HashMap<Item,Integer>();
	}
	
	/**
	 *@param a constructor for loading the pack from disk
	 *@param Node: node to be opened
	 */
	public Pack(Node n) {
		for(Node x : n.subnodes("key_item")) {
			keyItemsPocket.put(Item.fromNode(x.contentOf("item")), x.icontentOf("qty"));
		}
		
		for(Node x : n.subnodes("normal_item")) {
			itemsPocket.put(Item.fromNode(x.contentOf("item")), x.icontentOf("qty"));
		}
		
		for (Node x : n.subnodes("tmhm")) {
			tmHmPocket.put(Item.fromNode(x.contentOf("item")), x.icontentOf("qty"));
		}
		
		for (Node x : n.subnodes("pokeball")) {
			pokeballPocket.put(Item.fromNode(x.contentOf("item")), x.icontentOf("qty"));
		}
	}
	
	/**
	 *a method to return node for saving
	 *@return	returns a Node representing the pack that can be saved to disk
	 */
	public Node getPack() {
		//create parent node
		Node packNode = new Node("pack");
		
		//create 1st child nodes
		Node keyItem = new Node("key_item");
		Node item = new Node("normal_item");
		Node tmhm = new Node("tmhm");
		Node pokeball = new Node("pokeball");
		
		Iterator keyItr = keyItemsPocket.entrySet().iterator();
		Iterator itemItr = itemsPocket.entrySet().iterator();
		Iterator tmHmItr = tmHmPocket.entrySet().iterator();
		Iterator pokeballItr = pokeballPocket.entrySet().iterator();
		
		while (keyItr.hasNext()) {
			Map.Entry kItem = (Map.Entry)keyItr.next();
			keyItem.addSubnode("item", ""+kItem.getValue());
			kItem = (Map.Entry)keyItr.next();
			keyItem.addSubnode("qty",""+kItem.getValue());
		}
		
		while (itemItr.hasNext()) {
			Map.Entry i = (Map.Entry)itemItr.next();
			item.addSubnode("item", ""+i.getValue());
			i = (Map.Entry)itemItr.next();
			item.addSubnode("qty",""+i.getValue());
		}
		
		while (tmHmItr.hasNext()) {
			Map.Entry th = (Map.Entry)tmHmItr.next();
			tmhm.addSubnode("item",""+th.getValue());
			th = (Map.Entry)tmHmItr.next();
			tmhm.addSubnode("qty", ""+th.getValue());
		}
		
		while (pokeballItr.hasNext()) {
			Map.Entry pBall = (Map.Entry)pokeballItr.next();
			pokeball.addSubnode("item", ""+pBall.getValue());
			pBall = (Map.Entry)pokeballItr.next();
			pokeball.addSubnode("qty", ""+pBall.getValue());
		}
		
		packNode.addSubnode(keyItem);
		packNode.addSubnode(item);
		packNode.addSubnode(tmhm);
		packNode.addSubnode(pokeball);
		
		return packNode;
	}
	
	/**
	* updates the quantity of a key item
	*@param Item: item to be added, Int: number to add
	*/
	public void addKeyItem(Item i, int qty) {
		int q = keyItemsPocket.get(i);
		
		keyItemsPocket.put(i, q + qty);
	}
	
	/**
	* updates the quantity of a regular item
	*@param Item: item to be added, Int: number to add
	*/
	public void addItem(Item i, int qty) {
		int q = 0;
		if (itemsPocket.get(i)==null){}else
			q=itemsPocket.get(i);
		
		itemsPocket.put(i, q + qty);
	}
	
	/**
	* updates the quantity of a pokeball
	*@param Item: item to be added, Int: number to add
	*/
	public void addPokeball(Item i, int qty) {
		int q = pokeballPocket.get(i);
		
		pokeballPocket.put(i, q + qty);
	}
	
	/**
	* updates the quantity of a TM or HM
	*@param Item: item to be added, Int: number to add
	*/
	public void addTmHm(Item i, int qty) {
		int q = tmHmPocket.get(i);
		
		tmHmPocket.put(i, q + qty);
	}
	
	/**
	* @return returns all of the KeyItems
	*/
	public Set<Item> getAllKeyItems() {
		return keyItemsPocket.keySet();
	}
	
	/**
	* @return returns all of the regular Items
	*/
	public Set<Item> getAllItems() {
		return itemsPocket.keySet();
	}
	
	/**
	* @return returns all of the pokeballs
	*/
	public Set<Item> getAllPokeballs() {
		return pokeballPocket.keySet();
	}
	
	/**
	* @return returns all of theTMs and HMs
	*/
	public Set<Item> getAllTmHms() {
		return tmHmPocket.keySet();
	}
	
	/**
	* @return returns the quantity of a given key item
	*/
	public int getQtyOfKeyItem(Item i) {
		return keyItemsPocket.get(i);
	}
	
	/**
	* @return returns the quantity of a given item
	*/
	public int getQtyOfItem(Item i) {
		return itemsPocket.get(i);
	}
	
	/**
	* @return returns the quantity of a given pokeball
	*/
	public int getQtyOfPokeball(Item i) {
		return pokeballPocket.get(i);
	}
	
	/**
	* @return returns the quantity of a given HM TM
	*/
	public int getQtyOfTmHm(Item i) {
		return tmHmPocket.get(i);
	}
}