package main.modules;

import main.exceptions.XmlValidationException;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

public class JudgeModuleImpl implements JudgeModule {
    @Override
    public void validateXml(String xmlContent) throws XmlValidationException {
        StringReader reader = new StringReader(xmlContent);
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema;
        try {
            schema = factory.newSchema(getClass().getClassLoader().getResource("movement.xsd"));
            Validator val = schema.newValidator();
            val.validate(new StreamSource(reader));

        } catch (SAXException e) {
            throw XmlValidationException.xmlContentValidationException(e);
        } catch (IOException e) {
            throw XmlValidationException.inputFileIoException(e);
        }
    }
}
