package com.softeng.database;

public class Main {
    
    public static void main(String[] args) {
        String[] movieAttributes = {
            "ID integer PRIMARY KEY,\n", 
            "title text NOT NULL,\n", 
            "year text NOT NULL,\n", 
            "directorID integer,\n",
            "FOREIGN KEY(directorID) references directors(id)\n"
        };
        
        String[] directorAttributes = {
            "ID integer PRIMARY KEY,\n", 
            "name text NOT NULL,\n", 
            "yearOfBirth text NOT NULL\n"
        };
        
        
        
        Database db = new Database("jdbc:sqlite:/home/gianmarco/Scrivania/ExamJuly/database.db");
        
        // Directories table
        db.createNewTable("directors", directorAttributes);
        
        // Movies table
        db.createNewTable("movies", movieAttributes);
        
        // insert data
        
        String[][] movies = {
            {"1", "'Harry Potter and the Philosopher Stone'", "'2001'", "1"},
            {"2", "'Harry Potter and the Chamber of Secrets'", "'2002'", "1"},
            {"3", "'Harry Potter and the Prisoner of Azkaban'", "'2004'", "2"},
            {"4", "'Harry Potter and the Golbet of Fire'", "'2005'", "3"},
            {"5", "'Harry Potter and the Order of the Phoenix'", "'2007'", "4"},
            {"6", "'Harry Potter and the Half-Blood Prince'", "'2009'", "4"},
            {"7", "'Harry Potter and the Deathly Hallows - Part 1'", "'2010'", "4"},
            {"8", "'Harry Potter and the Deathly Hallows - Part 2'", "'2011'", "4"},
            {"9", "'Avatar'", "'2009'", "5"},
            {"10", "'Titanic'", "'1997'", "5"},
            {"11", "'Aquaman'", "'2018'", "6"},
            {"12", "'The Hunger Games'", "'2012'", "7"},
            {"13", "'The Hunger Games: Catching Fire'", "'2013'", "8"},
            {"14", "'The Hunger Games: Mockingjay - Part 1'", "'2014'", "8"},
            {"15", "'The Hunger Games: Mockingjay - Part 2'", "'2015'", "8"}
        };
        
        String[][] directors = {
            {"1", "'Chris Columbus'", "'1958'"},
            {"2", "'Alfonso Cuarón'", "'1961'"},
            {"3", "'Mike Newell'", "'1942'"},
            {"4", "'David Yates'", "'1963'"},
            {"5", "'James Cameron'", "'1954'"},
            {"6", "'James Wan'", "'1977'"},
            {"7", "'Gary Ross'", "'1956'"},
            {"8", "'Francis Lawrence'", "'1971'"}
        };
        
        for(String[] director: directors) db.insert(
                "directors(ID, name, yearOfBirth)", director);
        for(String[] movie: movies) db.insert(
                "movies(ID, title, year, directorID)", movie);
        
        db.selectAll();
        
        System.out.println("Data successfully entered");
   }
}
