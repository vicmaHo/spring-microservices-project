package com.vho.microservices.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    // Definir el stub
    public static void stubInventoryCall(String skuCode, Integer quantity) {
        stubFor(get(urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
