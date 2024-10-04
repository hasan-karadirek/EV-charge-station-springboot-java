package com.sparkshare.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class PointSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("latitude");
        gen.writeNumber(value.getY());  // Y is latitude
        gen.writeFieldName("longitude");
        gen.writeNumber(value.getX());  // X is longitude
        gen.writeEndObject();
    }
}
