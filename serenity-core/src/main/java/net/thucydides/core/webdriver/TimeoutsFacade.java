package net.thucydides.core.webdriver;

import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 30/01/15.
 */
public class TimeoutsFacade implements WebDriver.Timeouts {

    private final WebDriverFacade webDriverFacade;
    private final WebDriver.Timeouts timeouts;

    public TimeoutsFacade(WebDriverFacade webDriverFacade, WebDriver.Timeouts timeouts) {
        this.webDriverFacade = webDriverFacade;
        this.timeouts = timeouts;
    }

    @Override
    public WebDriver.Timeouts implicitlyWait(long timeoutValue, TimeUnit timeUnit) {
        webDriverFacade.implicitTimeout = Duration.of(timeoutValue, TIME_TO_TEMPORAL.get(timeUnit));
        return (timeouts != null) ? timeouts.implicitlyWait(timeoutValue,timeUnit) : timeouts;
    }

    @Override
    public WebDriver.Timeouts setScriptTimeout(long timeoutValue, TimeUnit timeUnit) {
        return (timeouts != null) ? timeouts.setScriptTimeout(timeoutValue, timeUnit) : timeouts;
    }

    @Override
    public WebDriver.Timeouts pageLoadTimeout(long timeoutValue, TimeUnit timeUnit) {
        return (timeouts != null) ? timeouts.pageLoadTimeout(timeoutValue,timeUnit) : timeouts;
    }

    private static final Map<TimeUnit, TemporalUnit> TIME_TO_TEMPORAL = new HashMap<>();
    static {
        TIME_TO_TEMPORAL.put(TimeUnit.MILLISECONDS, ChronoUnit.MILLIS);
        TIME_TO_TEMPORAL.put(TimeUnit.SECONDS, ChronoUnit.SECONDS);
        TIME_TO_TEMPORAL.put(TimeUnit.HOURS, ChronoUnit.HOURS);
        TIME_TO_TEMPORAL.put(TimeUnit.MINUTES, ChronoUnit.MINUTES);
        TIME_TO_TEMPORAL.put(TimeUnit.DAYS, ChronoUnit.DAYS);
        TIME_TO_TEMPORAL.put(TimeUnit.MICROSECONDS, ChronoUnit.MICROS);
        TIME_TO_TEMPORAL.put(TimeUnit.NANOSECONDS, ChronoUnit.NANOS);
    }
}
