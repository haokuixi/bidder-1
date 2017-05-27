package main.modules;

import main.exceptions.XmlValidationException;

public interface JudgeModule {

    void validateXml(String xmlContent) throws XmlValidationException;

}
