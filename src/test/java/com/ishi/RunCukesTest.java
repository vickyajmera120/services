package com.ishi;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features  = "src/test/resources/com/ishi",
        glue = "com.ishi"
)
public class RunCukesTest {
	
}