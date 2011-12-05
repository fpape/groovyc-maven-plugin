package be.freels.maven.groovyc.plugin;

import be.freels.maven.groovyc.executor.GroovyCompilerExecutor;
import be.freels.maven.groovyc.executor.GroovycConfiguration;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 9/19/11
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractGroovyMojo extends AbstractMojo {
    /**
     * The maven project
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;


    /**
     * run in debug mode
     *
     * @parameter expression="false"
     * @required
     */
    private boolean debug;

    /**
     * Indicates whether the source files to be compiled will be listed; defaults to no.
     *
     * @parameter expression="false"
     * @required
     */
    private boolean listFiles;


    /**
     *
     *  @parameter expression="1.5"
     *  @required
     */
    private String sourceCompatibility;
    /**
     *
     *  @parameter expression="1.5"
     *  @required
     */
    private String targetCompatibility;

    protected MavenProject getProject() {
        return project;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getSourceCompatibility() {
        return sourceCompatibility;
    }

    public String getTargetCompatibility() {
        return targetCompatibility;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            GroovycConfiguration groovycConfiguration = GroovycConfiguration.builder()
                    .withSrcDir(getSrcDir())
                    .withTargetDir(getTargetDir())
                    .withSourceCompatibility(getSourceCompatibility())
                    .withTargetCompatibility(getTargetCompatibility())
                    .withDebug(isDebug())
                    .withClasspath(getClasspath())
                    .build();

            new GroovyCompilerExecutor()
                    .doGroovyCompile(groovycConfiguration);
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoExecutionException("Error compiling groovy sources", e);
        }
    }

    protected abstract File getSrcDir();

    protected abstract File getTargetDir();

    protected abstract List getClasspath() throws DependencyResolutionRequiredException;
}
