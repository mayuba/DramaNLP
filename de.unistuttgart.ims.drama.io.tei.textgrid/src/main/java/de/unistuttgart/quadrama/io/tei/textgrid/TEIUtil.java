package de.unistuttgart.quadrama.io.tei.textgrid;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import de.unistuttgart.ims.drama.api.CastFigure;

public class TEIUtil {
	public static CastFigure parsePersonElement(JCas jcas, Element personElement) {
		CastFigure figure = new CastFigure(jcas);
		if (personElement.hasAttr("xml:id"))
			figure.setXmlId(personElement.attr("xml:id"));
		if (personElement.hasAttr("sex"))
			figure.setGender(personElement.attr("sex"));
		if (personElement.hasAttr("age"))
			figure.setAge(personElement.attr("age"));

		// gather names
		List<String> nameList = new LinkedList<String>();
		Elements nameElements = personElement.select("persName");

		for (int j = 0; j < nameElements.size(); j++) {
			nameList.add(nameElements.get(j).text());
		}
		for (TextNode tn : personElement.textNodes()) {
			if (tn.text().length() > 0)
				nameList.add(tn.text());
		}
		figure.setNames(toStringArray(jcas, nameList));
		figure.addToIndexes();
		return figure;

	}

	static StringArray toStringArray(JCas jcas, Collection<String> coll) {
		StringArray arr = new StringArray(jcas, coll.size());
		Iterator<String> collIter = coll.iterator();
		int i = 0;
		while (collIter.hasNext()) {
			arr.set(i++, collIter.next());
		}
		return arr;
	}
}