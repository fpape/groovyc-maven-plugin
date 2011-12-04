package be.freels.maven.groovyc.executor

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 10/4/11
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
class GroovyCompilerExecutor {
    private GroovycMojo mojo
    private String executorName

    GroovyCompilerExecutor(GroovycMojo mojo, String executorName) {
        this.mojo = mojo
        this.executorName = executorName

        new AntBuilder(new AntProject()).sequential {
            taskdef name: "groovyc", classname: "org.codehaus.groovy.ant.Groovyc"
            groovyc srcdir: "$mojo.srcDir.absolutePath", destdir: "$mojo.targetDir.absolutePath", {
                javac source: mojo.sourceCompatibility, target: mojo.targetCompatibility, debug: mojo.debug ? 'on' : 'off'
            }
        }
    }
}
