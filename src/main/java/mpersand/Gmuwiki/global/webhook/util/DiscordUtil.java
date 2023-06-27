package mpersand.Gmuwiki.global.webhook.util;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DiscordUtil {

    private final OkHttpClient httpClient;

    @Value("${discord.webhook.url}")
    private String discordWebhookUrl;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void sendDiscordMessage(String message) {

        RequestBody requestBody = RequestBody.create(message, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(discordWebhookUrl)
                .post(requestBody)
                .build();

        try(okhttp3.Response response = httpClient.newCall(request).execute()) {

            if(response.isSuccessful()) {

                log.trace("문의봇이 문의사항 요청을 성공적으로 전달했어요!");
            } else {
                log.error(response.body().string());
                log.error("문의봇이 문의사항 요청 대신 " + response.code() + " 코드를 보냈어요!");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String toSingleDiscordMessage(String string) {

        return "{\n" +
                "    \"content\":\"" + string + "\"\n" +
                "}".trim();
    }

    public String createInquiryMessage(String inquiryName, String inquiryType) {

        return "{\n" +
                "    \"content\": \"문의사항 요청이 들어왔어요!\",\n" +
                "    \"embeds\": [\n" +
                "        {\n" +
                "            \"title\": \"문의사항을 확인해주세요!\",\n" +
                "            \"color\": 5725911,\n" +
                "            \"fields\": [\n" +
                "                {\n" +
                "                    \"name\": \"문의사항 제목\",\n" +
                "                    \"value\": \"" + inquiryName + "\",\n" +
                "                    \"inline\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"문의사항 카테고리\",\n" +
                "                    \"value\": \"" + inquiryType + "\",\n" +
                "                    \"inline\": true\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"attachments\": []\n" +
                "}".trim();
    }
}