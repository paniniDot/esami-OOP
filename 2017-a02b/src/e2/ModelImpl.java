package e2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelImpl implements Model {

	private final File file;
	private final List<String> lines;
	
	public ModelImpl(final String fileName) {
		this.file = new File(fileName);
		this.lines = new ArrayList<>();
		this.reset();
	}
	
	private void isAllowed() {
		if(this.getLines() <= 0) {
			throw new IllegalStateException("no lines to do that onto");
		}
	}
	
	private void printLines() {
		System.out.println(this.lines.stream().collect(Collectors.joining(",", "<<", ">>")));
	}
	
	@Override
	public void concat(int line) {
		this.isAllowed();
		this.lines.set(line, this.lines.get(line) + this.lines.get(line));
		this.printLines();
		this.updateFile();
	}
	
	@Override
	public void remove(int line) {
		this.isAllowed();
		this.lines.remove(line);
		this.printLines();
		this.updateFile();
	}
	
	@Override
	public void add(int line) {
		this.isAllowed();
		this.lines.add(line+1, "*"+this.lines.get(line));
		this.printLines();
		this.updateFile();
	}
	
	@Override
	public String getLine(int line) {
		return this.lines.get(line);
	}
	
	@Override
	public int getLines() {
		return this.lines.size();
	}
	
	private void updateFile() {
		try(final BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
			this.lines.forEach(line -> {
				try {
					bw.write(line);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	private void reset() {
		try (final BufferedReader bf = new BufferedReader(new FileReader(this.file))) {
			String line = null;
			while((line = bf.readLine()) != null) {
				this.lines.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
