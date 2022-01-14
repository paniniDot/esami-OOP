package e2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelImpl implements Model {

	private final List<Optional<String>> lines = new ArrayList<>();
	private final File file;
	
	public ModelImpl(final String fileName) {
		this.file = new File(fileName);
		this.readFile(this.file);
		System.out.println(this.lines);
	}
	
	@Override
	public void disableLine(int line) {
		this.lines.set(line, Optional.empty());
	}
	
	@Override
	public List<String> getLines() {
		return this.lines.stream().flatMap(Optional::stream).collect(Collectors.toList());
	}
	
	@Override
	public void writeFile() {
		try(PrintStream ps = new PrintStream(this.file)) {
			this.lines.stream().flatMap(Optional::stream).peek(ps::println).forEach(System.out::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void readFile(final File file) {
		try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
			String str = null;
			while((str = bf.readLine()) != null) {
				this.lines.add(Optional.of(str));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
