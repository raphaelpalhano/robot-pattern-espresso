package com.example.importanttodos.test;

import io.cucumber.android.runner.CucumberAndroidJUnitRunner;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = "features",                             // pasta androidTest/assets/features
        glue     = "com.example.importanttodos.test.steps" // seu pacote de steps + holders
)
public class CucumberTestInstrumentation extends CucumberAndroidJUnitRunner {
}
