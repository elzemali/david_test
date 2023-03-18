/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import davidtest.model.Person;
import davidtest.model.models.Persons;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author lenovo
 */
public class ConvertJson {

    /**
     * la methode recupere la liste des personnes a partir d'un fichier json rsc
     * est le chemin vers le fichier data.json, ce dernier est inclut dans le
     * projet, essayez le chemin 'davidtest/ressource/data.json
     *
     * @param rsc
     * @return
     * @throws IOException
     */
    public static Persons jsonToPerson(String rsc) throws IOException {
        ObjectMapper om = getObjectMapper();
        InputStream i = getResource(rsc);

        if (i != null) {
            Persons readValue = om.readValue(i, Persons.class);

            i.close();

            return readValue;
        } else {
            throw new IOException();
        }
    }

    /**
     * la methode genere un fichier json a partir de l'iterable persons rsc est
     * le chemin vers le fichier data.json, ce dernier est inclut dans le
     * projet,
     *
     * @param dist le chemin vers l'emplacement pour creer le fichier
     * @param p
     * @return
     * @throws IOException
     */
    public static void personToJson(String dist, Persons p) throws IOException {

        File myObj = new File(dist);
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.findAndRegisterModules();
        //TODO verefier l'existance de close stream dans writer
        om.writer(new DefaultPrettyPrinter()).writeValue(myObj, p);
        System.out.println("Operation terminée");
    }

    /**
     * generer un stream a partir de l'emplacement de fichier
     *
     * @param rsc
     * @return InputStream ou null
     */
    private static InputStream getResource(String rsc) {

        return ConvertJson.class.getResourceAsStream(rsc);
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());   //Ajouter le module pour traiter dateLocal

        return om;
    }

    public static void writeJsonGenerator() throws FileNotFoundException, IOException {
        JsonFactory jsonFactory = new JsonFactory();
        FileOutputStream file = new FileOutputStream(new File("D:/cp/infoOne.json"));
        JsonGenerator jsonGen = jsonFactory.createGenerator(file, JsonEncoding.UTF8);
        Person person = null;
        jsonGen.setPrettyPrinter(new DefaultPrettyPrinter());
        jsonGen.setCodec(new ObjectMapper());
        jsonGen.writeObject(person);
        System.out.println("Done");
    }

    public static Persons readJsonGenerator(String rsc) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createJsonParser(getResource(rsc));
        jp.setCodec(getObjectMapper());
        Persons readValueAs = jp.readValueAs(Persons.class);
        
        return readValueAs;

    }

//    public static void main(String[] args) throws JsonParseException, IOException {
//		JsonFactory jsonFactory = new JsonFactory();
//		JsonParser jp = jsonFactory.createJsonParser(new File("D:/cp/infoTwo.json"));
//		jp.setCodec(new ObjectMapper());
//		JsonNode jsonNode = jp.readValueAsTree();
//		readJsonData(jsonNode);
//	}
//	static void readJsonData(JsonNode jsonNode) {
//		Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.getFields();
//		while(ite.hasNext()){
//			Map.Entry<String, JsonNode> entry = ite.next();
//			if(entry.getValue().isObject()) {
//				readJsonData(entry.getValue());
//			} else {
//			    System.out.println("key:"+entry.getKey()+", value:"+entry.getValue());
//			}
//		}
//	}
}
