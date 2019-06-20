
MAVEN:
	create executable jar with all dependency 
	 mvn clean compile assembly:single

GRADLE: customize jar clousure for creating executable jar with all dependency.
	jar {
	    manifest { 
	    	attributes "Main-Class": "$mainClassName"
	    }  
	    baseName = 'SimpleJdbcPrj'
	    from { configurations.compile.collect { it.isDirectory() ? it : zipTree(it) } }
	}

	gradle build



ANT:
	create a task for creating jar and copy other jars with it.

	<target name="jar" depends="build">
		<mkdir dir="${jar.dir}"/>
		<mkdir dir="${build.dir}/lib"/>
		<copy todir="${build.dir}/lib" flatten="true">
		    <path refid="libraries-path"/>
		</copy>

		<manifestclasspath property="manifest.classpath" jarfile="${jar.file}">
		    <classpath refid="libraries-path"/>
		</manifestclasspath>

		<jar destfile="${jar.file}" basedir="${build.dir}">
		    <manifest>
		        <attribute name="Main-Class" value="${main-class}"/>
		        <attribute name="Class-Path" value="${manifest.classpath}"/>
		    </manifest>
		</jar>  
    	</target>

