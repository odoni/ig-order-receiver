package com.odoni.ig.igorderreceiver.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	OrderService orderService;

	@InjectMocks
	OrderController orderController;

	@Test
	public void shouldSendOrdersToQueueSuccessfully() throws Exception {
		StringBuilder line = new StringBuilder();
		line.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		line.append(System.lineSeparator());
		line.append("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");

		MockMultipartFile mockFile = new MockMultipartFile("mockFile",
				"mockFile.xml",
				MediaType.TEXT_PLAIN_VALUE,
				line.toString().getBytes());

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		mockMvc.perform(multipart("/api/v1/order")
				.file(mockFile)
				.param("conn","conn")
				.param("user","user")
				.param("password","password")
				.param("broker","broker")
				.param("queue","queue"))
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnError400() throws Exception {
		StringBuilder line = new StringBuilder();
		line.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		line.append(System.lineSeparator());
		line.append("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");

		MockMultipartFile mockFile = new MockMultipartFile("mockFile",
				"mockFile.xml",
				MediaType.TEXT_PLAIN_VALUE,
				line.toString().getBytes());

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		mockMvc.perform(multipart("/api/v1/order")
				.file(mockFile))
				.andExpect(status().isBadRequest());
	}
}
