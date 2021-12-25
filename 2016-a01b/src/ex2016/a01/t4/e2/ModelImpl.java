package ex2016.a01.t4.e2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelImpl implements Model {
	
	private final List<String> l = new ArrayList<>();
	private final File file;
	private Iterator<String> it;
	
	public ModelImpl(final String fileName) {
		this.file = new File(fileName);
		try (var bf = new BufferedReader(new FileReader(this.file))) {
			String str;
			while((str = bf.readLine()) != null) {
				this.l.add(str);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.it = this.l.iterator();
	}
	
	@Override
	public boolean hasNextNumber() {
		return this.it.hasNext();
	}
	
	@Override
	public String nextNumber() {
		return this.it.next();
	}
	
	@Override
	public Model reset() {
		return new ModelImpl(this.file.getAbsolutePath());
	}
	
	
}
