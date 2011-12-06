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
 * @goal compileGroovy
 * @threadSafe
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
