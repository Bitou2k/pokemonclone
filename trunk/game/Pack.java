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
		int q = itemsPocket.get(i);
		
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