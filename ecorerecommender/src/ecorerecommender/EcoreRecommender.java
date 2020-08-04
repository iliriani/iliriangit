package ecorerecommender;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EcoreRecommender {
	
	public static void main(String[] args) throws Exception {
		new EcoreRecommender().getRecommendations(new File("recipes.ecore"), Collections.emptyList());
	}
	
	public List<String> getRecommendations(File devEcore, List<File> knoweldgeBase) throws Exception {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI(devEcore.getAbsolutePath()));
		resource.load(null);
		
		EPackage root = (EPackage) resource.getContents().get(0);
		
		
		for (EClassifier c : root.getEClassifiers()) {
			System.out.println(c.getName());
			
			for (EStructuralFeature f : ((EClass) c).getEStructuralFeatures()) {
				System.out.println("--" + f.getName() + ":" + f.getEType().getName());
			}
			
		}
		
		return Collections.emptyList();
		
	}
	
}
