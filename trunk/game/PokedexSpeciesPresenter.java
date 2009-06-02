package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
*Displays the details for one species as part of the pokedex.
*/
public class PokedexSpeciesPresenter extends Presenter
{
	private Species s;
	private Presenter oldPresenter;
	
	public PokedexSpeciesPresenter(Species s, Presenter p)
	{
		this.s = s;
		this.oldPresenter = p;
	}
	
	public void drawOn(Graphics2D g)
	{
	}
	public void step(int ms)
	{
	}
	public void buttonPressed(Button b)
	{
		if(b==Button.B) enterPresenter(oldPresenter);
	}
}
