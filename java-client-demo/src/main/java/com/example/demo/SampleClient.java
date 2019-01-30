package com.example.demo;

import com.example.demo.web.models.Person;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SampleClient {

    private static final String BASE_URL = "http://localhost:50000/person";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Random random = new Random();
        IntStream.range(0, 2000).forEach(i -> {
            try{
                requests.get(random.nextInt(1000) % 4).accept(restTemplate);
            } catch (Exception  e) {

            } finally {
                System.out.println("request " + i);
            }
        });
    }

    private static List<Consumer<RestTemplate>> requests = Arrays.asList(SampleClient::getPerson, SampleClient::addPerson, SampleClient::updatePerson, SampleClient::deletePerson);

    private static void getPerson(RestTemplate restTemplate) {
        restTemplate.getForEntity(BASE_URL + "/1", Person.class);
    }

    private static void addPerson(RestTemplate restTemplate) {
        restTemplate.postForEntity(BASE_URL, new Person( "New", "Person", "email"), Person.class);
    }

    private static void updatePerson(RestTemplate restTemplate) {
        restTemplate.put(BASE_URL, new Person( 1,"Updated", "Person", "email"), Person.class);
    }

    private static void deletePerson(RestTemplate restTemplate) {
        restTemplate.delete(BASE_URL +"/3");
    }
}
