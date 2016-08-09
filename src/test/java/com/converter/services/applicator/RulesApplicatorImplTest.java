package com.converter.services.applicator;

import com.converter.exception.ServiceException;
import com.converter.model.ConversionRequest;
import com.converter.util.TestUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.converter.util.CatCurrency.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RulesApplicatorImplTest extends TestUtil {

    private RulesApplicatorImpl rulesApplicator = new RulesApplicatorImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testApplyCurrencyRules_concatenatesMeowPurr() throws Exception {

        List<String> currency = buildCatCurrency(MEOW.value(), PURR.value());
        ConversionRequest conversionRequest = buildConversionRequest(currency);
        List<String> expectedFormat = buildCatCurrency(concatenateCurrency(MEOW.value(), PURR.value()));

        ConversionRequest actualConversionRequest = rulesApplicator.applyCurrencyRules(conversionRequest);

        assertThat(actualConversionRequest.getCatCurrency(), is(expectedFormat));
    }

    @Test
    public void testApplyCurrencyRules_concatenatesMeowHiss() throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(buildCatCurrency(MEOW.value(), HISS.value()));
        List<String> expectedFormat = buildCatCurrency(concatenateCurrency(MEOW.value(), HISS.value()));

        ConversionRequest actualConversionRequest = rulesApplicator.applyCurrencyRules(conversionRequest);

        assertThat(actualConversionRequest.getCatCurrency(), is(expectedFormat));
    }

    @Test
    public void testApplyCurrencyRules_concatenatesHissYowl() throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(buildCatCurrency(HISS.value(), YOWL.value()));
        List<String> expectedFormat = buildCatCurrency(concatenateCurrency(HISS.value(), YOWL.value()));

        ConversionRequest actualConversionRequest = rulesApplicator.applyCurrencyRules(conversionRequest);

        assertThat(actualConversionRequest.getCatCurrency(), is(expectedFormat));
    }

    @Test
    public void testApplyCurrencyRules_combinesMeowReducerWithEligibleCurrencyWords() throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(
                        buildCatCurrency(HISS.value(), HISS.value(), MEOW.value(), HISS.value()));

        List<String> expectedFormat = buildCatCurrency(HISS.value(), HISS.value(),
                        concatenateCurrency(MEOW.value(), HISS.value()));

        ConversionRequest actualConversionRequest = rulesApplicator.applyCurrencyRules(conversionRequest);

        assertThat(actualConversionRequest.getCatCurrency(), is(expectedFormat));
    }

    @Test
    public void testApplyCurrencyRules_combinesMultipleReducerWords() throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(
                        buildCatCurrency(HISS.value(), YOWL.value(), MEOW.value(), HISS.value()));

        List<String> expectedFormat = buildCatCurrency(concatenateCurrency(HISS.value(), YOWL.value()),
                        concatenateCurrency(MEOW.value(), HISS.value()));

        ConversionRequest actualConversionRequest = rulesApplicator.applyCurrencyRules(conversionRequest);

        assertThat(actualConversionRequest.getCatCurrency(), is(expectedFormat));
    }

    @Test
    public void testApplyCurrencyRules_throwsExceptionWhenProvidedInvalidHighDenomination()
                    throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(buildCatCurrency(YOWL.value(), YOWL.value()));

        exception.expect(ServiceException.class);
        exception.expectMessage("Invalid cat currency");

        rulesApplicator.applyCurrencyRules(conversionRequest);
    }

    @Test
    public void testApplyCurrencyRules_throwsExceptionWhenProvidedInvalidHissDenomination()
                    throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(
                        buildCatCurrency(HISS.value(), HISS.value(), HISS.value(), HISS.value(), HISS.value()));

        exception.expect(ServiceException.class);
        exception.expectMessage("Invalid cat currency");

        rulesApplicator.applyCurrencyRules(conversionRequest);
    }

    @Test
    public void testApplyCurrencyRules_throwsExceptionWhenProvidedInvalidMeowDenomination()
                    throws Exception {

        ConversionRequest conversionRequest = buildConversionRequest(
                        buildCatCurrency(MEOW.value(), MEOW.value(), MEOW.value(), MEOW.value(), MEOW.value()));

        exception.expect(ServiceException.class);
        exception.expectMessage("Invalid cat currency");

        rulesApplicator.applyCurrencyRules(conversionRequest);
    }

    @Test
    public void testApplyCurrencyRules_throwsExceptionWhenProvidedInvalidCatCurrencyOrder() throws Exception {

        List<String> catCurrency = buildCatCurrency(PURR.value(), HISS.value(), PURR.value(), YOWL.value());
        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);

        exception.expect(ServiceException.class);
        exception.expectMessage("Invalid cat currency");

        rulesApplicator.applyCurrencyRules(conversionRequest);

    }
}
