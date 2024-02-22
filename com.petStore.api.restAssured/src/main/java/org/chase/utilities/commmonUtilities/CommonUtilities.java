package org.chase.utilities.commmonUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonUtilities {

	public static String getJSON(String filePath) throws IOException {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
