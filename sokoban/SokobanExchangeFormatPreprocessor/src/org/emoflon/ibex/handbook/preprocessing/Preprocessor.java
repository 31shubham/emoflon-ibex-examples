package org.emoflon.ibex.handbook.preprocessing;

import java.io.File;
import java.util.Optional;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.handbook.preprocessing.api.PreprocessingAPI;
import org.emoflon.ibex.handbook.preprocessing.api.PreprocessingDemoclesApp;
import org.emoflon.ibex.handbook.preprocessing.api.matches.InitMatch;

public class Preprocessor extends PreprocessingDemoclesApp {

	public Preprocessor(ResourceSet resourceSet) {
		File root = new File(Preprocessor.class.getResource(".").getFile());
		workspacePath = root.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile()
				.getParentFile().getParent() + File.separatorChar;
		this.resourceSet = resourceSet;
	}

	public void preprocess() {
		PreprocessingAPI api = initAPI();
		api.completeFirstRow().enableAutoApply();
		api.completeAllOtherRows().enableAutoApply();
		Optional<InitMatch> o = api.init().apply();
		api.updateMatches();
		System.out.println("Could init be applied? " + o.isPresent());
	}
}
