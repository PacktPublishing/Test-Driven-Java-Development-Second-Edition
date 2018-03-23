package com.packtpublishing.tddjava.ch02friendships.bdd.jbehave;

import com.packtpublishing.tddjava.ch02friendships.bdd.steps.WebSteps;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.AbstractStoryPathResolver;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Properties;

public class JBehaveTest extends JUnitStory {
    public JBehaveTest() {
        configuredEmbedder().embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true);
    }

    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryPathResolver(new StoryResolver())
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withViewResources(viewResources)
                        .withCodeLocation(
                                CodeLocations.codeLocationFromPath("build/reports/jbehave"))
                        .withFailureTrace(true)
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.HTML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new WebSteps());
    }

    private class StoryResolver extends AbstractStoryPathResolver {

        public StoryResolver() {
            super(".story");
        }

        @Override
        protected String resolveDirectory(
                Class<? extends Embeddable> embeddableClass) {
            return "bdd/jbehave/stories";
        }

        @Override
        protected String resolveName(Class<? extends Embeddable> embeddableClass) {
            return "wikipediaSearch";
        }
    }
}
