package be.freels.maven.groovyc.plugin;

import org.apache.maven.artifact.DependencyResolutionRequiredException;

import java.io.File;
import java.util.List;

/**
 * @goal testCompileGroovy
 * @requiresDependencyResolution test
 * @phase test-compile
 */
public class GroovyTestCompileMojo extends AbstractGroovyMojo {
    /**
     * @parameter expression="src/test/groovy"
     * @required
     */
    private File testSrcDir;

    /**
     * @parameter expression="${project.build.testOutputDirectory}"
     * @required
     */
    private File testOutputDir;

    @Override
    protected String getExecutorName() {
        return "groovyTestCompile";
    }

    public File getSrcDir() {
        return testSrcDir;
    }

    public File getTargetDir() {
        return testOutputDir;
    }

    public List getClasspath() throws DependencyResolutionRequiredException {
        return project.getTestClasspathElements();
    }
}
