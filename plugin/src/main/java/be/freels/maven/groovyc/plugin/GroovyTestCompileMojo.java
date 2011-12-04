package be.freels.maven.groovyc.plugin;

import java.io.File;

/**
 * @goal testCompileGroovy
 * @requiresDependencyResolution test
 * @phase test-compile
 */
public class GroovyTestCompileMojo extends AbstractGroovyMojo {
    /**
     * @parameter expression="${baseDir}/src/test/groovy"
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
}
