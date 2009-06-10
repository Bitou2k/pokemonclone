import java.util.*;
import java.io.*;
import javax.imageio.stream.*;
import game.Node;

public class RemoveClones
{
	private Map<File, File> images;

	public static void oneSec()
	{
		try
		{
			Thread.currentThread().sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static byte[] getBytesFromFile(File file) throws IOException
	{
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE)
		{
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int)length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
			   && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
		{
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length)
		{
			throw new IOException("Could not completely read file " + file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static void deleteFile(File fileN)
	{
		// A File object to represent the filename
		File f = new File(".\\tileImages\\" + fileN.getName());

		// Attempt to delete it
		boolean success = f.delete();

		if (!success)
		{
			throw new IllegalArgumentException("Delete: deletion failed");
		}
		else
		{
			System.out.println("File deleted: " + f);
		}
	}

	public static void main(String[] args)
	{
		File dir = new File("./tileImages");
		File[] children = dir.listFiles();

		File map = new File("./areas");
		File[] maps = map.listFiles();

		String outputString = "";
		String mapString = "";
		String s1 = "";
		String s2 = "";
		File mapFile;


		if (children != null)
		{
			int counter = 0;
			for (int i = 0; i < children.length; i++)
			{
				for (int j = 0; j < children.length; j++)
				{
					if (i != j)
					{
						try
						{
							byte[] file1 = getBytesFromFile(children[i]);
							byte[] file2 = getBytesFromFile(children[j]);

							boolean isEqual = Arrays.equals(file1, file2);
							if (isEqual)
							{
								s1 = file1.toString();
								s2 = file2.toString();

								System.out.println("before for loop");
								for (int k = 0; k < maps.length; k++)
								{
									System.out.println("in for loop");
									try
									{
										Node mapNode = Node.parseFrom(new FileInputStream(maps.toString()));
										oneSec();
										System.out.println("in try");
										mapString = mapNode.content();
										System.out.println("mapNode content: " + mapString);
										oneSec();
										mapString = mapString.replaceAll(s2, s1);
										mapNode.content(mapString);
										System.out.println("new mapNode content: " + mapString);
										oneSec();
									}
									catch (Exception ex) { System.err.println("Caught Exception: " + ex.getMessage()); }
								}
								System.out.println("after for loop");
								deleteFile(children[j]);
							}
						}
						catch (IOException e)
						{
							System.err.println("Caught IOException: " + e.getMessage());
						}
					}
				}
			}
		}
		else
		{
			System.out.println("Children is null..not a directory..");
		}
	}
}