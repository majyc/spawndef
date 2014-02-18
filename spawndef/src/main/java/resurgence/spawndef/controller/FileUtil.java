package resurgence.spawndef.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Helper class for reading and writing files.
 */
public class FileUtil {

	/**
	 * The character set. UTF-8 works good for windows, mac and Umlaute.
	 */
	private static final Charset CHARSET = Charset.forName("UTF-8");

	/**
	 * Reads the specified file and returns the content as a String.
	 * 
	 * @param file
	 * @return
	 * @throws IOException thrown if an I/O error occurs opening the file
	 */
	public static String readFile(File file) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();

		BufferedReader reader = Files.newBufferedReader(file.toPath(), CHARSET);

		String line = null;
		while ((line = reader.readLine()) != null) {
			stringBuffer.append(line).append("\n");
		}

		reader.close();

		return stringBuffer.toString();
	}

	/**
	 * Saves the content String to the specified file.
	 * 
	 * @param content
	 * @param file
	 * @throws IOException thrown if an I/O error occurs opening or creating the file
	 */
	public static void saveFile(File file, String content) throws IOException {
		// try with resource handles close on finally
		try(PrintWriter out = new PrintWriter(file)){
			out.print(content);
		} 
	}


	/**
	 * @param filename
	 * @param data
	 * @throws IOException
	 */
	public static void saveFileWithBackup(File file, String content) throws IOException{
		Path filePath = Paths.get(file.getPath()).toRealPath();
		Path backPath = Paths.get(file.getPath() + ".backup");
		Files.move(filePath, backPath, StandardCopyOption.REPLACE_EXISTING);
		// try with resource handles close on finally
		try(PrintWriter out = new PrintWriter(file)){
				out.print(content);
		}
	}


}