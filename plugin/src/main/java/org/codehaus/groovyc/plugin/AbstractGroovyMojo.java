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

import org.codehaus.groovyc.executor.GroovyCompilerExecutor;
import org.codehaus.groovyc.executor.GroovycConfiguration;
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
                    .withListFiles(listFiles)
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
