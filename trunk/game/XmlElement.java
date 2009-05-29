package game;

import java.io.*;
import java.util.*;

/**
*  BETA, DONT TOUCH
*/
public class XmlElement
{

	public XmlElement(){
		this("","");
	}
	public XmlElement(String name){
		this(name,"");
	}
	public XmlElement(String name, String content){
		if(name!=null)this.name=name;
		if(content!=null)this.content=content;
	}
	
	/**
	*Returns the name of this node
	 */
	public String name(){return name;}
	/**
	*Sets the name of this node
	 */
	public void name(String x) {name=x;}
	
	/**
	*Returns the content of this node
	 */
	public String content() {return content;}
	/**
	*Sets the content of this node
	 */
	public void content(String x) {content = x;}
	
	/**
	*Returns an enumeration of all children of this node
	 */
	public List<XmlElement> children() {return children;}
	
	/**
	*Adds a subnode with the given name and content.
	 */
	public XmlElement addSubnode(String name,String contents){
		return addSubnode(new XmlElement(name,contents));
	}
	
	/**
	*Adds a subnode
	 */
	public XmlElement addSubnode(XmlElement n){
		children.add(n);
		n.parent = this;
		return n;
	}
	
	/**
	*Returns the first found subnode with the given name
	 */
	public XmlElement subnode(String subnodeName){
		for(XmlElement n:children){
			if(n.name.equals(subnodeName)) return n;
		}
		return null;
	}
	
	/**
	*Returns an enumeration of all children with the given name
	 */
	public List<XmlElement> children(String subnodeName){
		List<XmlElement> matches = new ArrayList<XmlElement>();
		for(XmlElement n:children){
			if(n.name.equals(subnodeName)) matches.add(n);
		}
		return matches;
	}
	
	/**
	*Returns the content of the first subnode with the given name
	 */
	public String contentOf(String subnodeName){
		XmlElement n = subnode(subnodeName);
		if(n!=null) return n.content();
		return "";
	}
	public int icontentOf(String subnodeName){
		return new Integer(contentOf(subnodeName));
	}
	public double dcontentOf(String subnodeName){
		return new Double(contentOf(subnodeName));
	}
	
	
	private String encoded(){
		String x="";
		x+="<"+encode(name)+">"+encode(content);
		for(XmlElement n: children) x+= n.encoded();
		x+="</"+encode(name)+">";
		return x;
	}

	/**
	*Writes this stanza to the given stream
	 */
	public void writeOn(OutputStream os) throws IOException	{
		os.write( encoded().getBytes() );
		os.flush();
	}
	
	/**
	*Writes this stanza to the given stream, ignoring any errors
	 */
	public void tryWriteOn(OutputStream os){
		try{
			writeOn(os);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	*Returns a node containing all stanzas in the given file as children
	 */
	public static XmlElement documentRootFrom(String filename) throws IOException
	{
		InputStream is = new FileInputStream(filename);
		XmlElement root = new XmlElement(filename);
		for(XmlElement n: parseAllFrom(is)) root.addSubnode(n);
		return root;
	}
	
	/**
	*Parses and returns all stanzas from the stream, an empty collection if failure before any parsed
	 */
	public static List<XmlElement> parseAllFrom(InputStream is)
	{
		ArrayList<XmlElement> nodes = new ArrayList<XmlElement>();
		
		try{
			while(true)	nodes.add( parseFrom(is) );
		}catch(IOException e){}
		
		return nodes;
	}
	
	/**
	*Parse and return the next stanza, useful for streaming apps
	 */
	public static XmlElement parseFrom(InputStream is) throws IOException
	{
		XmlElement top = new XmlElement();
		
		upTo(is,'<');
		
		top.name( decode(upTo(is,'>')) );
		
		while(true)
		{
			String con = decode(upTo(is,'<'));
			top.appendContent(con);
		
			String name = decode(upTo(is,'>'));
			if(name.equals(""))
			{
				throw new IOException("Format error: empty tag");
			}
			else if(name.startsWith("/"))
			{
				//an end tag
				name = name.substring(1);
				if(name.equals(top.name()))
				{
					XmlElement parent = top.parent;
					if(parent==null) //root node
						return top;
					else
						top = parent;
				}
				else
				{
					//should never happen
					throw new IOException("Format error: Premature close of "+name);
				}
			}
			else
			{
				//an open tag
				top = top.addSubnode(name,"");
			}
		}
	}
	
	/////////////////////////PRIVATE
	private String name="";
	private String content="";
	private List<XmlElement> children = new ArrayList<XmlElement>(); //or children
	private XmlElement parent = null;
	private XmlElement(XmlElement parent){
		this.parent = parent;
	}
	private void appendContent(String c){
		content += c;
	}
	private XmlElement parent() {
		return parent;
	}
	
	//for debugging
	public String toString() {
		String x="";
		x+=name+"(c="+content+")(p="+(parent==null?"null":parent.name)+")\n";
		for(XmlElement n: children) x+= "\t"+n.toString()+"";
		return x;
	}
	
	//Smalltalk provides a better version of this by default
	private static String upTo(InputStream is, char end) throws IOException
	{
		byte[] bytes = new byte[1024];
		int pos=0;
		int b;
		
		while( (b=is.read())!=-1)
		{
			if(b==(int)end)
			{
				return new String(bytes,0,pos);
			}
			bytes[pos] = (byte)b;
			pos++;
		}
		return new String(bytes,0,pos);
	}
	
	private static String encode(String x)
	{
		x = x.replace("&","&amp;");
		x = x.replace("<","&lt;");
		x = x.replace(">","&gt;");
		return x;
	}
	
	private static String decode(String x)
	{
		x = x.replace("&lt;","<");
		x = x.replace("&gt;",">");
		x = x.replace("&amp;","&");
		return x;
	}
}