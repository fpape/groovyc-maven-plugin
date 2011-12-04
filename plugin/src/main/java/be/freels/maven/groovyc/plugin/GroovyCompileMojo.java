package be.freels.maven.groovyc.plugin;

import org.apache.maven.artifact.DependencyResolutionRequiredException;

import java.io.File;
import java.util.List;

/**
 * @goal compileGroovy
 * @requiresDependencyResolution compile
 * @phase compile
 */
public class GroovyCompileMojo extends AbstractGroovyMojo {
    /**
     * @parameter expression="src/main/groovy"
     * @required
     */
    private File mainSrcDir;

    /**
     * @parameter expression="${project.build.outputDirectory}"
     * @required
     */
    private File mainOutputDir;

    @Override
    protected String getExecutorName() {
        return "groovyCompile";
    }

    public File getSrcDir() {
        return mainSrcDir;
    }

    public File getTargetDir() {
        return mainOutputDir;
    }

    public List getClasspath() throws DependencyResolutionRequiredException {
        return project.getCompileClasspathElements();
    }
}
