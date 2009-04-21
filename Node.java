
import java.io.*;
import java.util.*;

//  like xml without attributes, namespace, or the <thisisanopenedandclosedtag/> shortcut
//
//  <anodesname>nodecontent<achildnode>childcontent</achildnode>morefirstnodecontent</anodesname>
//
//
public class Node
{
	private String name="";
	private String content="";
	private List<Node> children = new ArrayList<Node>();
	private Node parent=null;
	
	public Node(){}
	public Node(String name){
		this.name=name;
	}
	public Node(String name, String content){
		this.name=name;
		this.content=content;
	}
	public Node(Node parent){
		this.parent = parent;
	}
	
	public String name(){
		return name;
	}
	public void name(String x){
		name=x;
	}
	
	public String content(){
		return content;
	}
	public void content(String x){
		content = x;
	}
	private void appendContent(String c){
		content += c;
	}
	
	public List<Node> children(){
		return children;
	}
	public Node addChild(String name,String contents){
		return addChild(new Node(name,contents));
	}
	public Node addChild(Node n){
		children.add(n);
		n.parent = this;
		return n;
	}
	public Node childNamed(String name){
		for(Node n:children){
			if(n.name.equalsIgnoreCase(name)) return n;
		}
		return null;
	}
	public List<Node> childrenNamed(String name){
		List<Node> matches = new ArrayList<Node>();
		for(Node n:children){
			if(n.name.equalsIgnoreCase(name)) matches.add(n);
		}
		return matches;
	}
	public String contentOf(String childName){
		return childNamed(childName).content();
	}
	
	private Node parent()	{
		return parent;
	}
	
	
	public String encoded(){
		String x="";
		x+="<"+encode(name)+">"+encode(content);
		for(Node n: children) x+= n.encoded();
		x+="</"+encode(name)+">";
		return x;
	}

	public void writeOn(OutputStream os) throws IOException	{
		os.write( encoded().getBytes() );
		os.flush();
	}
	
	public void tryWriteOn(OutputStream os){
		try{
			writeOn(os);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String x="";
		x+=name+"(c="+content+")(p="+(parent==null?"null":parent.name)+")\n";
		for(Node n: children) x+= "\t"+n.toString()+"";
		return x;
	}
	
	
	//return a node containing all stanzas as children
	public static Node documentRootFrom(String filename) throws IOException
	{
		InputStream is = new FileInputStream(filename);
		Node root = new Node(filename);
		for(Node n: parseAllFrom(is))
			root.addChild(n);
		return root;
	}
	
	//parse and return all stanzas from the stream, an empty collection is failure before any parsed
	public static List<Node> parseAllFrom(InputStream is)
	{
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		try{
			while(true)	nodes.add( parseFrom(is) );
		}catch(IOException e){}
		
		return nodes;
	}
	
	//parse and return the next stanza, useful for streaming apps
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
					Node parent = top.parent();
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
				Node child = new Node(name);
				top.addChild(child);
				top = child;
			}
		}
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
	
	public static String encode(String x)
	{
		x = x.replace("&","&amp;");
		x = x.replace("<","&lt;");
		x = x.replace(">","&gt;");
		return x;
	}
	
	public static String decode(String x)
	{
		x = x.replace("&lt;","<");
		x = x.replace("&gt;",">");
		x = x.replace("&amp;","&");
		return x;
	}
}