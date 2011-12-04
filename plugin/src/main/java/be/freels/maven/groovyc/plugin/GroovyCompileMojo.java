package be.freels.maven.groovyc.plugin;

import java.io.File;

/**
 * @goal compileGroovy
 * @requiresDependencyResolution compile
 * @phase compile
 */
public class GroovyCompileMojo extends AbstractGroovyMojo {
    /**
     * @parameter expression="${baseDir}/src/main/groovy"
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
}
