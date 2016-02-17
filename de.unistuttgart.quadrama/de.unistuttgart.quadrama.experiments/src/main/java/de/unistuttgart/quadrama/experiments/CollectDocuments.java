package de.unistuttgart.quadrama.experiments;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.unistuttgart.ims.uimautil.SetDocumentId;
import de.unistuttgart.quadrama.io.gutenbergde.GutenbergDEReader;
import de.unistuttgart.quadrama.io.textgridtei.TextgridTEIReader;

public class CollectDocuments {

	public static void main(String[] args) throws UIMAException, IOException {

		System.err.println("Collecting Wieland ...");
		SimplePipeline.runPipeline(
				createReaderDescription(GutenbergDEReader.class,
						GutenbergDEReader.PARAM_INPUT_DIRECTORY,
						"src/main/resources/raw/Wieland/"),
				createEngineDescription(SetDocumentId.class,
						SetDocumentId.PARAM_DOCUMENT_ID, "Wieland"),
				createEngineDescription(XmiWriter.class,
						XmiWriter.PARAM_TARGET_LOCATION,
						"src/main/resources/romeo-and-juliet/de/"));

		System.err.println("Collecting Schlegel ...");
		SimplePipeline.runPipeline(
				createReaderDescription(TextgridTEIReader.class,
						TextgridTEIReader.PARAM_INPUT_DIRECTORY,
						"src/main/resources/raw/Schlegel/"),
				createEngineDescription(SetDocumentId.class,
						SetDocumentId.PARAM_DOCUMENT_ID, "Schlegel"),
				createEngineDescription(XmiWriter.class,
						XmiWriter.PARAM_TARGET_LOCATION,
										"src/main/resources/romeo-and-juliet/de/"));
	}

}