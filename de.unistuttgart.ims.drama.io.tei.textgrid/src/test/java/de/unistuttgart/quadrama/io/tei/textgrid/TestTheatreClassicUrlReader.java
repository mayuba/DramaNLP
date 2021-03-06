package de.unistuttgart.quadrama.io.tei.textgrid;

import static org.junit.Assert.assertEquals;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.unistuttgart.ims.drama.api.Drama;

public class TestTheatreClassicUrlReader {
	static CollectionReaderDescription description;
	static JCas jcas;

	@BeforeClass
	public static void setUp() throws ResourceInitializationException {
		description = CollectionReaderFactory.createReaderDescription(TheatreClassicUrlReader.class,
				TheatreClassicUrlReader.PARAM_INPUT, "src/test/resources/tc/tc0623.tei.xml",
				TheatreClassicUrlReader.PARAM_LANGUAGE, "fr");
		AggregateBuilder b = new AggregateBuilder();
		if (TestGenerics.debug)
			b.add(AnalysisEngineFactory.createEngineDescription(XmiWriter.class, XmiWriter.PARAM_TARGET_LOCATION,
					"target/doc"));
		jcas = SimplePipeline.iteratePipeline(description, b.createAggregateDescription()).iterator().next();
	}

	@Test
	public void testGeneral() {
		assertEquals("tc0623", JCasUtil.selectSingle(jcas, Drama.class).getDocumentId());
	}
}
