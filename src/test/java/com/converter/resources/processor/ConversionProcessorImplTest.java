package com.converter.resources.processor;

import com.converter.exception.ResourceException;
import com.converter.model.ConversionRequest;
import com.converter.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConversionProcessorImplTest extends TestUtil {

    private static final String MULTIPLE_CURRENCY_LINES = "multiple-currency-lines-test.txt";
    private static final String SINGLE_CURRENCY_LINE = "single-currency-line-test.txt";
    private static final String FILE_DOES_NOT_EXIST = "file-does-not-exist.txt";
    private static final String EMPTY_FILE = "empty-file-test.txt";
    private static final String STATEMENT_AND_QUESTION = "statement-and-question-test.txt";

    private final List<String> hissYowlMeowMeow = buildCatCurrency("how much is hiss yowl meow meow ?");
    private final List<String> meowPurrMackerel = buildCatCurrency("how many Credits is meow purr Mackerel ?");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ConversionProcessorImpl processor = new ConversionProcessorImpl();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testProcessRequests_returnsSingleFileLineAsStringList() throws Exception {

        List<ConversionRequest> content = processor.processRequests(SINGLE_CURRENCY_LINE);

        assertThat(content.get(0).getCatCurrency(), is(hissYowlMeowMeow));
    }

    @Test
    public void testProcessRequests_returnsMultipleFileLinesAsExpectedNumberOfStrings() throws Exception {

        List<ConversionRequest> content = processor.processRequests(MULTIPLE_CURRENCY_LINES);

        assertThat(content.size(), is(2));

    }

    @Test
    public void testProcessRequests_returnsMultipleFileLinesWithExpectedContent() throws Exception {

        List<ConversionRequest> conversionRequests = processor.processRequests(MULTIPLE_CURRENCY_LINES);

        List<String> firstContentLine = conversionRequests.get(0).getCatCurrency();
        List<String> secondContentLine = conversionRequests.get(1).getCatCurrency();

        assertThat(firstContentLine, is(hissYowlMeowMeow));
        assertThat(secondContentLine, is(meowPurrMackerel));

    }

    @Test
    public void testProcessRequests_doesNotAddStatementsToFileInput() throws Exception {

        List<ConversionRequest> conversionRequests = processor.processRequests(STATEMENT_AND_QUESTION);

        List<String> catCurrency = conversionRequests.get(0).getCatCurrency();

        assertThat(catCurrency, is(hissYowlMeowMeow));

    }

    @Test
    public void testProcessRequests_throwsFormatterExceptionWhenFileNotFound() throws Exception {

        exception.expect(ResourceException.class);
        exception.expectMessage("Cannot process invalid file");

        processor.processRequests(FILE_DOES_NOT_EXIST);

    }

    @Test
    public void testProcessRequests_throwsFormatterExceptionWhenProcessingEmptyFile() throws Exception {

        exception.expect(ResourceException.class);
        exception.expectMessage("Cannot process invalid request");

        processor.processRequests(EMPTY_FILE);

    }

    @Test
    public void testProcessRequests_throwExceptionWhenProvidedNullValue() throws Exception {

        exception.expect(ResourceException.class);

        processor.processRequests(null);

    }
}
