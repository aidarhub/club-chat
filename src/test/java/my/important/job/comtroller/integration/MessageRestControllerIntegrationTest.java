package my.important.job.comtroller.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.important.job.Main;
import my.important.job.WrapperJettyServer;
import my.important.job.entity.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MessageRestControllerIntegrationTest {

    @Test
    void test() throws Exception {
        final var wrapperJettyServer = new WrapperJettyServer();
        wrapperJettyServer.start();

        final var httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        final var saveRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8080/api/v1/message"))
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                          "text": "hello",
                          "senderId": 1,
                          "receivedId": 2,
                          "createTime": "2025-12-08"
                        }
                        """))
                .header("Content-Type", "application/json")
                .build();

        final var response = httpClient.send(saveRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(201, response.statusCode());

        final var findRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8080/api/v1/message/0"))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        final var responseFind = httpClient.send(findRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(responseFind.body());

        final var objectMapper = new ObjectMapper();
        final var jsonNode = objectMapper.readerFor(Message.class).readTree(responseFind.body());

        Assertions.assertEquals(0, jsonNode.findValue("messageId").asInt());
    }
}
