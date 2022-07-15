package main.java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWriter {
	private final String template;

	private String fileName;

	public FileWriter(String content) {
		Pattern pattern = Pattern.compile("(GET|POST|PUT)\\s/(.+\\..+)\\s.*", Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(content);
		while (m.find()) {
			fileName = buildFilenameFromUrl(m.group(2)); // group one matching the request method
		}
		LocalDateTime time = LocalDateTime.now();
		String title = "Socket HTTP Server";
		this.template = title + "\n" + "\n" + time.toLocalDate()
		+ "\n" + content;
		write();
	}
	String buildFilenameFromUrl(String url) {
		url = url.toLowerCase();
		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) == '/') {
				url =  url.substring(0, i) + Character.toUpperCase(url.charAt(i + 1)) + url.substring(i + 2);
			}
		}
		return url;
	}
	public void write() {
		try {
			Path file = Paths.get(fileName);
			if (Files.notExists(file)) {
				Files.write(file, Arrays.asList(template,
								"created @: " + LocalDateTime.now().toLocalTime().toString())
						, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
//			System.out.println("NPE");
		}
	}
	public String readFile() throws IOException {
		try {
		return Files.readString(Paths.get(fileName));
	} catch (NullPointerException e) {
			return "You should ask for a file with any extension.";
		}
	}
}
