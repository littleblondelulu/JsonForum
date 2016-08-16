package com.company;

import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import static java.util.Collections.sort;

public class Main {



    public static void main(String[] args) throws FileNotFoundException, IOException {


        //string is country arraylist is people from country
        HashMap<String, ArrayList<Person>> countryMap = new HashMap<>();

        // read all the posts into memory
        File f = new File("/Users/lindseyringwald/IdeaProjects/Forum_Assignment/src/people.csv");
        Scanner fileScanner = new Scanner(f);
        fileScanner.nextLine();
        Person person = null;
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split(",");
            person = new Person(Integer.valueOf(columns[0]), columns[1], columns[2], columns[3],
                    columns[4], columns[5]);

            //put is how you put a value to a key in a hashmap
            //at the least has an arraylist of zero
            countryMap.putIfAbsent(person.country, new ArrayList<>());
            countryMap.get(person.country).add(person);
            Collections.sort(countryMap.get(person.country));
        }
//        System.println(Collections.sort(countryMap.get(person.getCountry())));
        System.out.println(countryMap);

        //  countryMap.put(person.firstName);
        //   Collections.sort(countryMap.get(person.country));

        File p = new File("people.json");
        FileWriter fw = new FileWriter(p);


        // write json
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(countryMap);
        fw.write(json);
        fw.close();
    }

    }
