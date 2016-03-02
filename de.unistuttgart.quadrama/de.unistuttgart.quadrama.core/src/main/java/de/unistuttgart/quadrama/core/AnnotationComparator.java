package de.unistuttgart.quadrama.core;

import java.util.Comparator;

import org.apache.uima.jcas.tcas.Annotation;

/**
 * This comparator sorts non-overlapping annotations by their begin position.
 * 
 * @author Nils Reiter
 *
 */
public class AnnotationComparator implements Comparator<Annotation> {

	public int compare(Annotation o1, Annotation o2) {
		return Integer.compare(o1.getBegin(), o2.getBegin());
	}

}
