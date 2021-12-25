package e2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelImpl implements Model {
	
	private final File file;
	private final List<String> list;
	private final Iterator<String> iterator;
	
	public ModelImpl(final String fileName) {
		this.file = new File(fileName);
		this.list = new ArrayList<>();
		try (BufferedReader bf = new BufferedReader(new FileReader(this.file))) {
			String str;
			while((str = bf.readLine()) != null) {
				this.list.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.iterator = this.list.iterator();
	}
	
	@Override
	public boolean hasNextNumber() {
		return this.iterator.hasNext();
	}
	
	@Override
	public String nextNumber() {
		return this.iterator.next();
	}
	
	@Override
	public Model reset() {
		return new ModelImpl(this.file.getAbsolutePath());
	}
	
}
