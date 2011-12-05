package be.freels.maven.groovyc.executor

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 12/5/11
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
class GroovycConfiguration {
    File srcDir
    File targetDir
    String sourceCompatibility
    String targetCompatibility
    boolean listFiles
    boolean debug
    List classpath

    public static Builder builder() {
        return new Builder()
    }

    public static class Builder {
        def config = [:]

        Builder withSrcDir(File srcDir) {
            config.srcDir = srcDir
            return this
        }

        Builder withTargetDir(File targetDir) {
            config.targetDir = targetDir
            return this
        }

        Builder withSourceCompatibility(String sourceCompatibility) {
            config.sourceCompatibility = sourceCompatibility
            return this
        }

        Builder withTargetCompatibility(String targetCompatibility) {
            config.targetCompatibility = targetCompatibility
            return this
        }

        Builder withDebug(boolean debug) {
            config.debug = debug
            return this
        }

        Builder withListFiles(boolean listFiles) {
            config.listFiles = listFiles
            return this
        }

        Builder withClasspath(List classpath) {
            config.classpath = classpath
            return this
        }

        GroovycConfiguration build() {
            return config as GroovycConfiguration
        }
    }
}
