/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codehaus.groovyc.plugin;

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
