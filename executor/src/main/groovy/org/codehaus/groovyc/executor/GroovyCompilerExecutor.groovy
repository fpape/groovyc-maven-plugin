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

package org.codehaus.groovyc.executor

import org.apache.maven.plugin.logging.Log
import org.apache.maven.plugin.logging.SystemStreamLog

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 10/4/11
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
class GroovyCompilerExecutor {
    private Log log = new SystemStreamLog()

    def doGroovyCompile(GroovycConfiguration groovycConfig) {
        File srcDir = groovycConfig.srcDir
        if (!srcDir.exists()) {
            log.info("srcDir: '$srcDir ' does not exist, skipping groovy compile")
            return
        }

        File targetDir = groovycConfig.targetDir
        if (!targetDir.exists()) {
            log.debug("targetDir: '$targetDir' does not exist -> creating targetDir")
            boolean mkdirSuccess = targetDir.mkdirs()
            if (!mkdirSuccess) {
                log.info("creating targetDir: '$targetDir' failed, skipping groovy compile")
                return
            }

            log.debug("targetDir created")
        }

        new AntBuilder(new AntProject()).sequential {
            taskdef name: "groovyc", classname: "org.codehaus.groovy.ant.Groovyc"
            groovyc srcdir: "$srcDir.absolutePath", destdir: "$targetDir.absolutePath", listfiles: "$groovycConfig.listFiles", {
                javac source: groovycConfig.sourceCompatibility, target: groovycConfig.targetCompatibility, debug: groovycConfig.debug ? 'on' : 'off'
                classpath {
                    groovycConfig.classpath.each {
                        pathelement location: "$it"
                    }
                }
            }
        }
    }
}
