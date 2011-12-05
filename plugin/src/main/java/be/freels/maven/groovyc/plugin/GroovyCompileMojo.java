package be.freels.maven.groovyc.plugin;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

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


    protected File getSrcDir() {
        return mainSrcDir;
    }

    protected File getTargetDir() {
        return mainOutputDir;
    }

    protected List getClasspath() throws DependencyResolutionRequiredException {
        return project.getCompileClasspathElements();
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        project.addCompileSourceRoot(mainSrcDir.getPath());
        super.execute();
    }
}
