package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.Order;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class OrderFileParserDefaultTest {

	@Test
	public void removeFirstLineIfXmlHeaderShouldRemoveTheFirstElementWithSuccess() {
		List<String> linesOfXmlFile = new ArrayList<>();
		linesOfXmlFile.add("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		linesOfXmlFile.add("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");
		linesOfXmlFile.add("<Order><accont>AX002</accont><SubmittedAt>1507060723651</SubmittedAt><ReceivedAt>1507060723652</ReceivedAt><market>VOD.L</market><action>BUY</action><size>200</size></Order>");

		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();

		orderFileParser.removeFirstLineIfXmlHeader(linesOfXmlFile);

		assertEquals(2, linesOfXmlFile.size());
		assertTrue(linesOfXmlFile.get(0).startsWith("<Order>"));
	}

	@Test
	public void removeFirstLineIfXmlHeaderShouldNotRemoveLinesWhenHasNoHeader() {
		List<String> linesOfXmlFile = new ArrayList<>();
		linesOfXmlFile.add("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");
		linesOfXmlFile.add("<Order><accont>AX002</accont><SubmittedAt>1507060723651</SubmittedAt><ReceivedAt>1507060723652</ReceivedAt><market>VOD.L</market><action>BUY</action><size>200</size></Order>");

		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();

		orderFileParser.removeFirstLineIfXmlHeader(linesOfXmlFile);

		assertEquals(2, linesOfXmlFile.size());
	}

	@Test
	public void parseOrderFileLinesShouldParseAllLinesSuccessfully() throws InvalidOrderFileException {
		List<String> linesOfXmlFile = new ArrayList<>();
		linesOfXmlFile.add("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		linesOfXmlFile.add("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");
		linesOfXmlFile.add("<Order><accont>AX002</accont><SubmittedAt>1507060723651</SubmittedAt><ReceivedAt>1507060723652</ReceivedAt><market>VOD.L</market><action>BUY</action><size>200</size></Order>");

		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();

		List<Order> orders = orderFileParser.parseOrderFileLines(linesOfXmlFile);

		assertEquals(2, orders.size());
		assertEquals("AX001", orders.get(0).getAccount());
		assertEquals("AX002", orders.get(1).getAccount());
	}

	@Test(expected = InvalidOrderFileException.class)
	public void parseOrderFileLinesShouldThrowExceptionForEmptyListOfLines() throws InvalidOrderFileException {
		List<String> linesOfXmlFile = new ArrayList<>();
		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();
		orderFileParser.parseOrderFileLines(linesOfXmlFile);
	}

	@Test(expected = InvalidOrderFileException.class)
	public void parseOrderFileLinesShouldThrowExceptionForListOfEmptyLines() throws InvalidOrderFileException {
		List<String> linesOfXmlFile = new ArrayList<>();
		linesOfXmlFile.add("");
		linesOfXmlFile.add(" ");
		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();
		orderFileParser.parseOrderFileLines(linesOfXmlFile);
	}

	@Test(expected = InvalidOrderFileException.class)
	public void parseOrderFileLinesShouldThrowExceptionForListOfInvalidLines() throws InvalidOrderFileException {
		List<String> linesOfXmlFile = new ArrayList<>();
		linesOfXmlFile.add("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		linesOfXmlFile.add("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");
		linesOfXmlFile.add("<Order><Order><accont>AX002</accont><SubmittedAt>1507060723651</SubmittedAt><ReceivedAt>1507060723652</ReceivedAt><market>VOD.L</market><action>BUY</action><size>200</size></Order>");
		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();
		orderFileParser.parseOrderFileLines(linesOfXmlFile);
	}

	@Test
	public void getOrdersFromFileShouldParseAllLinesSuccessfully() throws InvalidOrderFileException {
		StringBuilder line = new StringBuilder();
		line.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		line.append(System.lineSeparator());
		line.append("<Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");

		MockMultipartFile mockFile = new MockMultipartFile("mockFile",
				"mockFile.xml",
				MediaType.TEXT_PLAIN_VALUE,
				line.toString().getBytes());

		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();

		List<Order> orders = orderFileParser.getOrdersFromFile(mockFile);

		assertEquals(1, orders.size());
		assertEquals("AX001", orders.get(0).getAccount());
	}

	@Test(expected = InvalidOrderFileException.class)
	public void getOrdersFromFileShouldThrowExceptionForNullMultiPart() throws InvalidOrderFileException {
		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();

		List<Order> orders = orderFileParser.getOrdersFromFile(null);
	}

	@Test(expected = InvalidOrderFileException.class)
	public void getOrdersFromFileShouldThrowExceptionForInvalidFileContent() throws InvalidOrderFileException {
		StringBuilder line = new StringBuilder();
		line.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		line.append(System.lineSeparator());
		line.append("<Order><Order><accont>AX001</accont><SubmittedAt>1507060723641</SubmittedAt><ReceivedAt>1507060723642</ReceivedAt><market>VOD.L</market><action>BUY</action><size>100</size></Order>");

		MockMultipartFile mockFile = new MockMultipartFile("mockFile",
				"mockFile.xml",
				MediaType.TEXT_PLAIN_VALUE,
				line.toString().getBytes());

		OrderFileParserDefault orderFileParser = new OrderFileParserDefault();

		orderFileParser.getOrdersFromFile(mockFile);
	}
}
