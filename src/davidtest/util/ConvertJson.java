/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import davidtest.model.models.Persons;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author lenovo
 */
public class ConvertJson {

    /**
     * la methode recupere la liste des personnes a partir d'un fichier json
     *  rsc est le chemin vers le fichier data.json, ce dernier est inclut dans le projet,
     *  essayez le chemin 'davidtest/ressource/data.json
     * @param rsc
     * @return
     * @throws IOException 
     */
    public static Persons jsonToPerson(String rsc) throws IOException {
        //TODO regarder la JsonMapper()
        ObjectMapper om = new ObjectMapper();
        //Ajouter le module pour traiter dateLocal
        om.registerModule(new JavaTimeModule());
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
   
        om.findAndRegisterModules();

        InputStream i = getResource(rsc);
        if (i != null) {
            Persons readValue = om.readValue(i, new TypeReference<Persons>() {
            });
            i.close();
            
            return readValue;
        } else {
            throw new IOException();
        }
    }

     /**
     * la methode genere un fichier json  a partir de l'iterable persons 
     *  rsc est le chemin vers le fichier data.json, ce dernier est inclut dans le projet,
     *  
     * @param dist le chemin vers l'emplacement pour creer le fichier
     * @return
     * @throws IOException 
     */
    public static void personToJson(String dist, Persons p) throws IOException {
       
        File myObj = new File(dist);
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.findAndRegisterModules();
        //TODO verefier l'existance de close stream dans writer
        om.writer(new DefaultPrettyPrinter()).writeValue(myObj, p.personList);
        System.out.println("Operation terminée");
    }

    /**
     * generer un stream a partir de l'emplacement de fichier
     * @param rsc
     * @return 
     */
    private static InputStream getResource(String rsc) {
        try {
            return ConvertJson.class.getResourceAsStream(rsc);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    
    //the same operation with json simple
    //TODO Complete Parse manuel
//    public static BasePerson jsonToPersonParser(String rsc) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        //URL resource = ConvertJson.class.getResource(rsc).getFile();
//       try (FileReader reader = new FileReader(ConvertJson.class.getResource(rsc).getFile())) {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONObject personsObject = (JSONObject) obj;
//            System.out.println(personsObject);
//            JSONArray personsList = (JSONArray) personsObject.get("persons");
//
//            System.out.println(personsList);
//            //Iterate over employee array
//            //       employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
//
//        } catch (FileNotFoundException e) {
//        } catch (IOException | ParseException e) {
//        }
//
//        return null;
//    }

//    private static void parsePersonObject(JSONObject person) {
        //Get employee object within list
        //  JSONObject employeeObject = (JSONObject) employee.get("employee");

//        //Get employee first name
//        String firstName = (String) person.get("firstName");
//        System.out.println(firstName);

        //Get employee last name
//        String lastName = (String) person.get("lastName");
//        System.out.println(lastName);
//
//        //Get employee website name
//        String website = (String) person.get("website");
//        System.out.println(website);
//
//        String eyeColor = (String) person.get("firstName");
//
//        String gender = (String) person.get("firstName");
//
//        //  LocalDateTime dateOfBirth=  (String) person.get("firstName");
//        String email = (String) person.get("firstName");
//
//        // Long phoen =  (String) person.get("firstName");
//        String address = (String) person.get("firstName");
//
//        String country = (String) person.get("firstName");
//
//        String about = (String) person.get("firstName");
//      //  boolean isActive =  (String) person.get("firstName");
//
//        //    LocalDate registered =  (String) person.get("firstName");
//        List children;
//    }

}
