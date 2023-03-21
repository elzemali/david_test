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
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import davidtest.model.models.Persons;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class ConvertJson {

    /**
     * la methode recupere la liste des personnes a partir d'un fichier json rsc
     * est le chemin vers le fichier data.json, ce dernier est inclut dans le
     * projet, essayez le chemin 'davidtest/ressource/data.json La méthode se
     * base sur le concepte de databinding avec ObjectMapper pour une methode
     * avec JsonParser
     *
     * @see readJsonGenerator#readJson
     *
     * @param rsc le chemin de fichier dans package ressource
     * @return list de person
     * @throws IOException
     */
    public static Persons jsonToPerson(String rsc) throws IOException {
        ObjectMapper om = getObjectMapper();
        try (InputStream i = getResource(rsc)) {
            if (i != null) {
                Persons readValue = om.readValue(i, Persons.class);
                i.close();
                return readValue;
            } else {
                throw new IOException();
            }
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

        try (FileOutputStream output = new FileOutputStream(new File(dist))) {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            om.findAndRegisterModules();

            om.writer(new DefaultPrettyPrinter()).writeValue(output, p);
            System.out.println("Operation terminée");
        }
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

    /**
     * Description : this method generate json file from list of person with
     * jsonGenerator
     *
     *
     * @param jsonFactory
     * @param dist
     * @param persons
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void writeJsonGenerator(JsonFactory jsonFactory, String dist, Persons persons)
            throws FileNotFoundException, IOException {

        try (FileOutputStream file = new FileOutputStream(new File(dist))) {
            JsonGenerator jsonGen = jsonFactory.createGenerator(file, JsonEncoding.UTF8);
            jsonGen.setPrettyPrinter(new DefaultPrettyPrinter());
            jsonGen.setCodec(getObjectMapper());
            jsonGen.writeObject(persons);
            personnaliserJsonGenerator(persons, jsonGen);
            System.out.println("Done");
        }
    }

    /**
     * This method parse json file in Persons object using jsonParser if you
     * want parse with ObjectMapper @see jsonToPerson
     *
     * @param jsonFactory
     * @param rsc
     * @return
     * @throws IOException
     */
    public static Persons readJson(JsonFactory jsonFactory, String rsc) throws IOException {

        try (InputStream in = getResource(rsc)) {
            JsonParser jp = jsonFactory.createJsonParser(in);
            jp.setCodec(getObjectMapper());
            Persons readValueAs = jp.readValueAs(Persons.class);

            return readValueAs;
        }

    }

    /**
     * This method create structure of json according to person's attributes
     *
     * @param persons
     * @param jsonGen
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void personnaliserJsonGenerator(Persons persons, JsonGenerator jsonGen) throws FileNotFoundException, IOException {
        jsonGen.writeStartObject();
        jsonGen.writeFieldName("persons");
        jsonGen.writeStartArray();
        persons.forEach(x -> {
            try {
                jsonGen.writeStartObject();
                jsonGen.writeStringField("_id", x.getId());
                jsonGen.writeStringField("eyeColor", x.getEyeColor());
                jsonGen.writeStringField("firstName", x.getFirstName());
                jsonGen.writeStringField("lastName", x.getLastName());
                jsonGen.writeStringField("gender", x.getGenderName());
                jsonGen.writeStringField("dateOfBirth",
                        x.getDateOfBirth().format(DateTimeFormatter.ISO_DATE_TIME));
                jsonGen.writeStringField("email", x.getEmail());
                jsonGen.writeStringField("phone", x.getPhone().toString());
                jsonGen.writeStringField("address", x.getAddress());
                jsonGen.writeStringField("country", x.getCountry());
                jsonGen.writeStringField("about", x.getAbout());
                jsonGen.writeStringField("isActive", (x.isActive()) ? "true" : "false");
                jsonGen.writeStringField("registered",
                        x.getRegistered().format(DateTimeFormatter.ISO_DATE));
                jsonGen.writeFieldName("children");
                jsonGen.writeStartArray();

                x.getChildren().forEach(y -> {
                    try {
                        jsonGen.writeStartObject();
                        jsonGen.writeStringField("_id", y.getId());
                        jsonGen.writeStringField("firstName", y.getFirstName());
                        jsonGen.writeStringField("lastName", y.getLastName());
                        jsonGen.writeEndObject();
                    } catch (IOException ex) {
                        Logger.getLogger(ConvertJson.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                });
                jsonGen.writeEndArray();
                jsonGen.writeEndObject();

            } catch (IOException ex) {
                Logger.getLogger(ConvertJson.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        });
        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
        jsonGen.close();
    }
}
