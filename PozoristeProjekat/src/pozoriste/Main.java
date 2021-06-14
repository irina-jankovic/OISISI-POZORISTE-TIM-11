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

	public static Object openFromFile(String file) {
		try {
			FileInputStream fi = new FileInputStream(new File(file));
			ObjectInputStream oi = new ObjectInputStream(fi);
			Object obj = oi.readObject();
			oi.close();
			fi.close();
			return obj;

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return null;

	}

}
