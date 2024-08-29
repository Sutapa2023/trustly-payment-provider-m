package com.trustly.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HighestInternationalStudents {
    public static void main(String[] args) throws JSONException {
        String apiUrl = "https://jsonmock.hackerrank.com/api/universities";
        int currentPage = 1;
        int totalPages = 10; // Initialize with a large number
        int highestInternationalStudents = 0;
        String universityWithHighestInternationalStudents = "";

        try {
            while (currentPage <= totalPages) {
                URL url = new URL(apiUrl + "?page=" + currentPage);
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
                // Assuming you have a JSON library to parse the response, e.g., Gson or Jackson
                // Here, let's assume the JSON parsing is done manually for simplicity

                // Parse JSON and find university with highest international students
                // JSON parsing logic based on your JSON structure
                // Here's a simplified example assuming direct parsing
                // Replace with actual parsing based on your JSON structure
                // Example:
                JSONObject jsonObject = new JSONObject(response);
                JSONArray data = jsonObject.getJSONArray("data");

                // For demonstration, assume data parsing
                // Replace with actual parsing based on your JSON structure
                 for (int i = 0; i < data.length(); i++) {
                 JSONObject university = data.getJSONObject(i);
                String universityName = university.getString("university");
                 String internationalStudentsStr = university.getString("international_students");
                 int internationalStudents = Integer.parseInt(internationalStudentsStr.replaceAll(",", ""));
                 if (internationalStudents > highestInternationalStudents) {
                 highestInternationalStudents = internationalStudents;
                 universityWithHighestInternationalStudents = universityName;
                }
                 }

                currentPage++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("University with the highest number of international students:");
        System.out.println(universityWithHighestInternationalStudents);
        System.out.println("Number of international students:");
        System.out.println(highestInternationalStudents);
    }
}
