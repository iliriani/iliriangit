package ecorerecommender;

import java.io.File;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class EclDemo {
	
	public static void main(String[] args) throws Exception {
		
		EclModule module = new EclModule();
		module.parse(new File("ecore.ecl"));
		
		EmfModel left = new EmfModel();
		left.setName("Left");
		left.setModelFile("socialnetwork.ecore");
		left.setMetamodelUri(EcorePackage.eINSTANCE.getNsURI());
		left.setReadOnLoad(true);
		left.setStoredOnDisposal(false);
		left.load();
		
		
		EmfModel right = new EmfModel();
		right.setName("Right");
		right.setModelFile("socialnetwork2.ecore");
		right.setMetamodelUri(EcorePackage.eINSTANCE.getNsURI());
		right.setReadOnLoad(true);
		right.setStoredOnDisposal(false);
		right.load();
		
		
		KnowledgeBase kb = new KnowledgeBase();
		
		module.getContext().getModelRepository().addModel(left);
		module.getContext().getModelRepository().addModel(right);
		
		module.execute();
		
		MatchTrace matchTrace = module.getContext().getMatchTrace();
		
		for (Match match : matchTrace.getMatches()) {
			if (match.isMatching()) {
				System.out.println(match.getLeft() + " <-> " + match.getRight());
			}
		}
		
		module.getContext().dispose();
		
	}
	
}
