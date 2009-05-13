package game;

import java.util.*;

/**
*Contains maps of all of the pockets which contins the Items and the quantity of each
*/

public class Bag{
	/**
	* contains all of the Key Items
	*/
	HashMap<Item,Integer> keyItemsPocket;
	/**
	* contains all of the regular items
	*/
	HashMap<Item,Integer> itemsPocket;
	/**
	* contains all of the TMs and HMs
	*/
	HashMap<Item,Integer> TmHmPocket;
	/**
	* contains all of the pokeballs
	*/
	HashMap<Item,Integer> pokeballPocket;
	
	/**
	* updates the quantity of a key item
	*@param Item: item to be added, Int: number to add
	*/
	public void addKeyItem(Item i, int qty){
	
	}
	/**
	* updates the quantity of a regular item
	*@param Item: item to be added, Int: number to add
	*/
	public void addItem(Item i, int qty){
	
	}
	/**
	* updates the quantity of a pokeball
	*@param Item: item to be added, Int: number to add
	*/
	public void addPokeball(Item i, int qty){
	
	}
	/**
	* updates the quantity of a TM or HM
	*@param Item: item to be added, Int: number to add
	*/
	public void addTmHm(Item i, int qty){
	
	}
	
	/**
	* @return returns all of the KeyItems
	*/
	public HashMap getAllKeyItems(){
		return null;
	}
	/**
	* @return returns all of the regular Items
	*/
	public HashMap getAllItems(){
		return null;
	}
	/**
	* @return returns all of the pokeballs
	*/
	public HashMap getAllPokeballs(){
		return null;
	}
	/**
	* @return returns all of theTMs and HMs
	*/
	public HashMap getAllTmHms(){
		return null;
	}
	
	/**
	* @return returns the quantity of a given key item
	*/
	public int getQtyOfKeyItem(Item i){
		return 0;
	}
	/**
	* @return returns the quantity of a given item
	*/
	public int getQtyOfItem(Item i){
		return 0;
	}
	/**
	* @return returns the quantity of a given pokeball
	*/
	public int getQtyOfPokeball(Item i){
		return 0;
	}
	/**
	* @return returns the quantity of a given HM TM
	*/
	public int getQtyOfTmHm(Item i){
		return 0;
	}
}