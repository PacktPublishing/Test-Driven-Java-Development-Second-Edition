package com.packtpublishing.tddjava.ch07bdd;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.*;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(getReporter())
                .useStoryLoader(new LoadFromURL());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(
                CodeLocations.codeLocationFromPath("").getFile(),
                Collections.singletonList("stories/**/*.story"),
                new ArrayList<String>(),
                "file:"
        );
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new Steps());
    }

    private StoryReporterBuilder getReporter() {
        return new StoryReporterBuilder()
                .withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName())
                .withDefaultFormats()
                .withFormats(Format.CONSOLE, Format.HTML);
    }

}
