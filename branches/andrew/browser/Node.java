/*	This class is identical, except for the package to which it belongs
	to the version being used in the actual game. Written by Ryan Macnak.	*/

package browser;

import java.io.*;
import java.util.*;

/**
*  parser/generator of a simplified version of xml;
*  node names are case-insenstive;
* a stanza is a top level node of a document
*/
public class Node
{

	public Node(){
		this("","");
	}
	public Node(String name){
		this(name,"");
	}
	public Node(String name, String content){
		if(name!=null)this.name=name;
		if(content!=null)this.content=content;
	}
	private Node(Node supernode){
		this.supernode = supernode;
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
	*Returns an enumeration of all subnodes of this node
	 */
	public List<Node> subnodes() {return subnodes;}
	
	/**
	*Adds a subnode with the given name and content.
	 */
	public Node addSubnode(String name,String contents){
		return addSubnode(new Node(name,contents));
	}
	
	/**
	*Adds a subnode
	 */
	public Node addSubnode(Node n){
		subnodes.add(n);
		n.supernode = this;
		return n;
	}
	
	/**
	*Returns the first found subnode with the given name
	 */
	public Node subnode(String subnodeName){
		for(Node n:subnodes){
			if(n.name.equalsIgnoreCase(subnodeName)) return n;
		}
		return null;
	}
	
	/**
	*Returns an enumeration of all subnodes with the given name
	 */
	public List<Node> subnodes(String subnodeName){
		List<Node> matches = new ArrayList<Node>();
		for(Node n:subnodes){
			if(n.name.equalsIgnoreCase(subnodeName)) matches.add(n);
		}
		return matches;
	}
	
	/**
	*Returns the content of the first subnode with the given name
	 */
	public String contentOf(String subnodeName){
		Node n = subnode(subnodeName);
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
		for(Node n: subnodes) x+= n.encoded();
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
	public static Node documentRootFrom(String filename) throws IOException
	{
		InputStream is = new FileInputStream(filename);
		Node root = new Node(filename);
		for(Node n: parseAllFrom(is)) root.addSubnode(n);
		return root;
	}
	
	/**
	*Parses and returns all stanzas from the stream, an empty collection if failure before any parsed
	 */
	public static List<Node> parseAllFrom(InputStream is)
	{
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		try{
			while(true)	nodes.add( parseFrom(is) );
		}catch(IOException e){}
		
		return nodes;
	}
	
	/**
	*Parse and return the next stanza, useful for streaming apps
	 */
	public static Node parseFrom(InputStream is) throws IOException
	{
		Node top = new Node();
		
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
				if(name.equalsIgnoreCase(top.name()))
				{
					Node supernode = top.supernode;
					if(supernode==null) //root node
						return top;
					else
						top = supernode;
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
	private List<Node> subnodes = new ArrayList<Node>(); //or children
	private Node supernode = null; //or parent
	
	private void appendContent(String c){
		content += c;
	}
	private Node supernode() {
		return supernode;
	}
	
	//for debugging
	public String toString() {
		String x="";
		x+=name+"(c="+content+")(p="+(supernode==null?"null":supernode.name)+")\n";
		for(Node n: subnodes) x+= "\t"+n.toString()+"";
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