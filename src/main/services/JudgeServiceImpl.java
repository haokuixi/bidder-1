package main.services;

import main.exceptions.XmlValidationException;
import main.modules.JudgeModule;

public class JudgeServiceImpl implements JudgeService {

    JudgeModule judgeModule;

    @Override
    public void validateXml(String xmlContent) throws XmlValidationException {
        judgeModule.validateXml(xmlContent);
    }

    public JudgeModule getJudgeModule() {
        return judgeModule;
    }

    public void setJudgeModule(JudgeModule judgeModule) {
        this.judgeModule = judgeModule;
    }
}

