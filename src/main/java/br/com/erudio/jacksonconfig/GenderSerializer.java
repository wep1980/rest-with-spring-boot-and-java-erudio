package br.com.erudio.jacksonconfig;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<String> {


    /**
     * Aplicando logica na serializacao
     * Formatando o campo gender na serializacao
     */
    @Override
    public void serialize(String gender, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        String formatedGender = "Male".equals(gender) ? "Male" : "F";
        gen.writeString(formatedGender);
    }
}
