package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.IOException;
import java.time.LocalDate;

import java.io.*;
import java.util.Scanner;


public class PatronDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/patrons.txt";
    private final String SEPERATOR = "::";
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        // TODO: implementation here
    	File file = new File(RESOURCE);
    	if (!file.exists()) return;
    	
    	try(Scanner sc = new Scanner(file)) {
    		while (sc.hasNextLine()) {
    			String line = sc.nextLine().trim();
    			if(line.isEmpty()) continue;
    			
    			String[]parts = line.split(SEPARATOR);
    			
    			int id = Integer.parseInt(parts[0]);
    			String name = (parts[1]);
    			String phone = (parts[2]);
    			
    			Patron patron = new Patron(id, name, phone);
    			library.addPatron(patron);
    		}
    	}
    }

    @Override
    public void storeData(Library library) throws IOException {
        // TODO: implementation here
    	try(PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		for(Patron p : library.getPatron()) {
    			out.println(p.getId() + SEPARATOR
    					+ p.getName() + SEPARATOR
    					+ p.getPhone() + SEPARATOR);
    		}
    	}
    }
}
 