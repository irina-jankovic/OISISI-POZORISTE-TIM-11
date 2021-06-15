package pozoriste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
