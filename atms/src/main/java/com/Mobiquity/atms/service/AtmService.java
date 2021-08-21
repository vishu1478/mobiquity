package com.Mobiquity.atms.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

@Service
public class AtmService {
	String url = "https://www.ing.nl/api/locator/atms/";

	RestTemplate restEmp = new RestTemplate();

	public String getAllAtm() {

		String res = restEmp.getForObject(url, String.class);
		String res2 = res.substring(5);
		System.out.println(res2);
		return (String) res2;
	}

	@SuppressWarnings("unchecked")
	public Object getAtmByCity(String city) {
		String res = restEmp.getForObject(url, String.class);
		String res2 = res.substring(5);
		System.out.println(res2.substring(0, 10));
		List<Object> listTest = null;
		try {
			JSONArray jsonArray = new JSONArray(res2);
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("Hello1");
			listTest = mapper.readValue(String.valueOf(jsonArray), List.class);
			System.out.println("Hello2");
			// listTest.get

			for (int i = 0; i < listTest.size(); i++) {
				// JSONObject explrObject = arr.getJSONObject(i);

				System.out.println(listTest.get(i));
				@SuppressWarnings("rawtypes")
				JSONObject jsonObject = new JSONObject((Map) listTest.get(i));
				String address = jsonObject.getString("address");
				JSONObject jsonObject2 = new JSONObject(address);
				if (city.equals(jsonObject2.getString("city"))) {
					return listTest.get(i);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "Incorrect City";
	}
}
