package luigibros.data.xml.user;

import java.awt.event.KeyEvent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** The root XML element to hold the key mapping
 *  preferences for the user.  Key values are
 *  to be stored according to the AWT standard.  
 * 
 * @author R.Wisniewki1101
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "key_settings")
public class KeySettings {
	@XmlElement(defaultValue = "" + KeyEvent.VK_RIGHT)
	private int forward;
	@XmlElement(defaultValue = "" + KeyEvent.VK_LEFT)
	private int back;
	@XmlElement(defaultValue = "" + KeyEvent.VK_UP)
	private int jump;
	@XmlElement(defaultValue = "" + KeyEvent.VK_DOWN)
	private int crouch;
	@XmlElement(defaultValue = "" + KeyEvent.VK_SPACE)
	private int accelerate;
	@XmlElement(defaultValue = "" + KeyEvent.VK_CONTROL)
	private int pause;
	
	public int getForward()		{return forward;}
	public int getBack()		{return back;}
	public int getJump()		{return jump;}
	public int getCrouch()		{return crouch;}
	public int getAccelerate()	{return accelerate;}
	public int getPause()		{return pause;}
	
	public void setForward		(int forward)		{this.forward = forward;}
	public void setBack			(int back)			{this.back = back;}
	public void setJump			(int jump)			{this.jump = jump;}
	public void setCrouch		(int crouch)		{this.crouch = crouch;}
	public void setAccelerate	(int accelerate)	{this.accelerate = accelerate;}
	public void setPause		(int pause)			{this.pause = pause;}
	
	public int hashCode() {
		return forward ^
		back ^
		jump ^
		crouch ^
		accelerate ^
		pause;
	}
	
	public String toString() {
		return "KeySettings[" +
			"forward=" + forward + 
			";back=" + back + 
			";juml=" + jump + 
			";crouch=" + crouch + 
			";accelerate=" + accelerate + 
			";pause=" + pause + ";]";
	}
}
