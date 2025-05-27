package com.webapptest.webapptest.service;

import com.webapptest.webapptest.exception.AuthorizationException;
import com.webapptest.webapptest.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramAuthService {
    @Value("${telegram.bot.token}")
    private final String botToken;

    public void checkHash(Map<String, String> data) {
        String initData = data.get("initData");
        String hash = data.get("hash");
        try {
            List<String> parts = Arrays.stream(initData.split("&"))
                    .filter(s -> !s.startsWith("hash="))
                    .sorted()
                    .collect(Collectors.toList());
            String dataCheckString = String.join("\n", parts);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = md.digest(botToken.getBytes());
            SecretKeySpec key = new SecretKeySpec(keyBytes, "HmacSHA256");

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
            byte[] hmac = mac.doFinal(dataCheckString.getBytes());

            String calculatedHash = Hex.encodeHexString(hmac);
            if (!calculatedHash.equalsIgnoreCase(hash)) {
                throw new AuthorizationException("Invalid hash");
            }
        } catch (Exception e) {
            log.error("Ошибка при проверке хеш {}", e.getMessage());
            throw new AuthorizationException("Invalid hash");
        }
    }

    public User getUser(Map<String, String> data) {
        String initData = data.get("initData");
        Map<String, String> params = Arrays.stream(initData.split("&"))
                .map(s -> s.split("=", 2))
                .filter(arr -> arr.length == 2)
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        Long id = Long.valueOf(params.get("user.id"));
        String firstName = decode(params.get("user.first_name"));
        String lastName = decode(params.get("user.last_name"));
        String username = decode(params.get("user.username"));
        return User.builder().id(id).first_name(firstName).last_name(lastName).username(username).build();
    }

    private String decode(String s) {
        return java.net.URLDecoder.decode(s, java.nio.charset.StandardCharsets.UTF_8);
    }
}
