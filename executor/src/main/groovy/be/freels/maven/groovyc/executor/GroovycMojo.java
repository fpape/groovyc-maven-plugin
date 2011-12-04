package be.freels.maven.groovyc.executor;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 10/4/11
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GroovycMojo {
    File getSrcDir();

    File getTargetDir();

    String getSourceCompatibility();

    String getTargetCompatibility();

    boolean isDebug();
}
