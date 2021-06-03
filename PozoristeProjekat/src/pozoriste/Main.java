package pozoriste;

import java.io.*;

public class Main {

	public static void main(String[] args) {
		new MainWindow();
	}

	public static void saveToFile(Object obj, String file) {
		try {
			FileOutputStream f = new FileOutputStream(new File(file));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(obj);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

}
