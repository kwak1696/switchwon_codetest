package dev.be.homework;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class HomeworkApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    void test_1() throws Exception {
        // 1. 잔액 조회
        String userId = "12345";

        MvcResult response = mvc.perform(request(HttpMethod.GET, "/api/payment/balance/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject(response.getResponse().getContentAsString());

        assertEquals(json.getString("userId"), "12345");
        assertEquals(json.getDouble("balance"), 1000.00);
        assertEquals(json.getString("currency"), "USD");
    }


    @Test
    void test_1_2() throws Exception {
        // 1. 잔액 조회 - userId가 없는 경우
        String userId = "67890";

        MvcResult response = mvc.perform(request(HttpMethod.GET, "/api/payment/balance/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject(response.getResponse().getContentAsString());

        assertEquals(json.getString("status"), "fail");
    }


    @Test
    void test_2() throws Exception {
        // 2. 결제 예상 결과 조회
        Map<String, Object> map = Map.of(
                "amount", 150.00,
                "currency", "USD",
                "destination", "merchantId123",
                "userId", "12345"
        );

        MvcResult response = mvc.perform(request(HttpMethod.POST, "/api/payment/estimate")
                        .contentType("application/json")
                        .content(new JSONObject(map).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject(response.getResponse().getContentAsString());

        assertEquals(json.getDouble("estimatedTotal"), 155.00);
        assertEquals(json.getDouble("fees"), 5.00);
        assertEquals(json.getString("currency"), "USD");

    }

    @Test
    void test_2_2() throws Exception {
        // 2. 결제 예상 결과 조회 - merchantId가 없는 경우
        Map<String, Object> map = Map.of(
                "amount", 150.00,
                "currency", "USD",
                "destination", "notExistMerchantId123",
                "userId", "12345"
        );

        MvcResult response = mvc.perform(request(HttpMethod.POST, "/api/payment/estimate")
                        .contentType("application/json")
                        .content(new JSONObject(map).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject(response.getResponse().getContentAsString());

        assertEquals(json.getString("status"), "fail");
    }


    @Test
    void test_3() throws Exception {
        // 3. 결제 승인 요청
        Map<String, Object> map = Map.of(
                "userId", "12345",
                "amount", 200.00,
                "currency", "USD",
                "merchantId", "merchantId123",
                "paymentMethod", "creditCard",
                "paymentDetails", Map.of(
                        "cardNumber", "1234567890123456",
                        "expirationDate", "12/24",
                        "cvv", "123"
                )
        );

        MvcResult response = mvc.perform(request(HttpMethod.POST, "/api/payment/approval")
                        .contentType("application/json")
                        .content(new JSONObject(map).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject(response.getResponse().getContentAsString());

        assertTrue(json.getString("paymentId").startsWith("paymentId"));
        assertEquals(json.getString("status"), "approved");
        assertEquals(json.getDouble("amount"), 200.00);
        assertEquals(json.getString("currency"), "USD");
        assertTrue(json.getString("timestamp").matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
    }


    @Test
    void test_3_2() throws Exception {
        // 3. 결제 승인 요청 - 잘못된 CVV인 경우
        Map<String, Object> map = Map.of(
                "userId", "12345",
                "amount", 200.00,
                "currency", "USD",
                "merchantId", "merchantId123",
                "paymentMethod", "creditCard",
                "paymentDetails", Map.of(
                        "cardNumber", "1234567890123456",
                        "expirationDate", "12/24",
                        "cvv", "999"
                )
        );

        MvcResult response = mvc.perform(request(HttpMethod.POST, "/api/payment/approval")
                        .contentType("application/json")
                        .content(new JSONObject(map).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject(response.getResponse().getContentAsString());

        assertTrue(json.getString("paymentId").startsWith("paymentId"));
        assertEquals(json.getString("status"), "error");
        assertEquals(json.getDouble("amount"), 200.00);
        assertEquals(json.getString("currency"), "USD");
        assertTrue(json.getString("timestamp").matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
    }

}
