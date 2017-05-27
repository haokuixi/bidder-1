package main.services;

import main.exceptions.XmlValidationException;

public interface JudgeService {

    void validateXml(String xmlContent) throws XmlValidationException;
}
