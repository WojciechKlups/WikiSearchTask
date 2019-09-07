package pl.wojtek.xtmtask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class XtmTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(XtmTaskApplication.class, args);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter football club name:");
        String userInput = scanner.nextLine();

        final String uri = "https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&utf8=1&srsearch=football+club+intitle:" + userInput + "&srlimit=10&srinfo=&srprop=snippet";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        JSONObject json = new JSONObject(result);
        JSONObject query = json.getJSONObject("query");
        JSONArray data = query.getJSONArray("search");
        for (int i = 0; i < data.length(); i++) {
            JSONObject o = data.getJSONObject(i);
            String title = o.getString("title");
            String replacedTitle = title.replace(" ", "_");
            System.out.println("https://en.wikipedia.org/wiki/" + replacedTitle);
        }

    }

}



