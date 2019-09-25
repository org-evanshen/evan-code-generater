package org.evanframework.toolbox.ormcreator;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PropertySet;

@Deprecated
public class OrmGeneratorTask extends Task {

	private PropertySet propertyset;

	private Path classpath;

	@Override
	public void execute() {
		super.log(propertyset.toString());
		super.log(classpath.toString());
		//OrmCreator ormCreator = new OrmCreator();
		//ormCreator.create(conf);
	}

	public void setPropertyset(PropertySet propertyset) {
		super.log(propertyset.toString());
		this.propertyset = propertyset;
	}

	public void setClasspath(Path classpath) {
		super.log(classpath.toString());
		this.classpath = classpath;
	}
}
