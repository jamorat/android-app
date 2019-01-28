package com.example.myapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JavaUrlConnectionReader
{
    public static void main(String[] args)
    {
        String output  = getUrlContents("http://localhost:8080/");
        System.out.println(output);
    }

    public static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }

            bufferedReader.close();
            System.out.print("buffer closed, we good?");
        }
        catch(Exception e)
        {
            System.out.print("exception oiwehfiowe");
            e.printStackTrace();
        }
        System.out.println("success down qwqwqw");
        //System.out.print(content.toString());
        System.out.println("success up qwqwqw");
        return content.toString();
    }
}
