package be.freels.maven.groovyc.plugin;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.util.List;

/**
 * @goal testCompileGroovy
 * @threadSafe
 * @requiresDependencyResolution test
 * @phase test-compile
 */
public class GroovyTestCompileMojo extends AbstractGroovyMojo {
    /**
     * Set this to 'true' to bypass unit tests entirely.
     * Its use is NOT RECOMMENDED, but quite convenient on occasion.
     *
     * @parameter expression="${maven.test.skip}"
     */
    private boolean skip;
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

    protected File getSrcDir() {
        return testSrcDir;
    }

    protected File getTargetDir() {
        return testOutputDir;
    }

    protected List getClasspath() throws DependencyResolutionRequiredException {
        return project.getTestClasspathElements();
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        project.addTestCompileSourceRoot(testSrcDir.getPath());
        if (skip) {
            getLog().info("Not compiling groovy test sources");
        } else {
            super.execute();
        }
    }

}
