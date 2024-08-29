package com.trustly.payment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class CityWeatherSearch {

    public static void main(String[] args) {
    	
    	Scanner in = new Scanner(System.in);  
    	  String keyword = in.nextLine(); 
      //  String keyword = "Aberdeen"; // Replace with the city name or keyword you want to search
        int currentPage = 1;
        int totalRecords = 0;
        int totalPages = Integer.MAX_VALUE; // Initialize with a large number
        
        try {
            while (currentPage <= totalPages) {
                URL url = new URL("https://jsonmock.hackerrank.com/api/weather/search?name=" + keyword + "&page=" + currentPage);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    responseBuilder.append(line);
                }

                br.close();
                conn.disconnect();

                // Parse JSON response
                String response = responseBuilder.toString();
                JSONObject jsonResponse = new JSONObject(response);

                // Extract data array
                JSONArray dataArray = jsonResponse.getJSONArray("data");
                totalRecords = jsonResponse.getInt("total");
                totalPages = jsonResponse.getInt("total_pages");

                // Process each weather record
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject weatherRecord = dataArray.getJSONObject(i);
                    String cityName = weatherRecord.getString("name");
                    // You can access other fields like temperature, humidity, etc. here
                    System.out.println("City Name: " + cityName);
                    // Print other weather details as needed
                }

                currentPage++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Total records found: " + totalRecords);
    }
}
