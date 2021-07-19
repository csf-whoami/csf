/**
 * 
 */
package com.csf.base.utilities;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author mba0051
 *
 */
public class MarshallerUtils {

	/**
	 * Mashaller object to XML string.
	 * 
	 * @author tuan
	 * @param <T>
	 * @param objIns
	 * @return
	 * @throws JAXBException
	 */
	public static <T> String marshallerToXML(T objIns) throws JAXBException {
		String result = null;

		// creating the JAXB context
		JAXBContext jContext = JAXBContext.newInstance(objIns.getClass());
		Marshaller marshallObj = jContext.createMarshaller();
		marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		StringWriter writer = new StringWriter();
		marshallObj.marshal(objIns, writer);
		result = writer.toString();
		return result;
	}

	/**
	 * Unmasharller XML content to Object.
	 * 
	 * @author tuan
	 * @param <T>
	 * @param xmlContent
	 * @param objIns
	 * @return
	 * @throws JAXBException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unMarshallerToObject(String xmlContent, Class<T> objIns) throws JAXBException {

		T result = null;
		JAXBContext jContext = JAXBContext.newInstance(objIns);
		Unmarshaller jaxbUnmarshaller = jContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlContent);
		result = (T) jaxbUnmarshaller.unmarshal(reader);

		return result;
	}
}
