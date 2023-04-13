package services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.Order;
import models.Product;

public class JsonParser {
	public static String parseOrder(Order order) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = null;
		try {
			json = mapper.writeValueAsString(order);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static List<Product> parseToMap(String pizzenStr) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return new ArrayList<Product>(Arrays.asList(mapper.readValue(pizzenStr, Product[].class)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
