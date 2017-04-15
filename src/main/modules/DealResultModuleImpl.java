package main.modules;

import main.dao.DealResultDAO;
import main.dto.DealResultDto;
import main.entities.DealResult;
import main.utils.DataHash;

import java.util.List;
import java.util.stream.Collectors;

public class DealResultModuleImpl implements DealResultModule {

    DealResultDAO dealResultDAO;

    @Override
    public void create(DealResultDto dealResultDto) {
        dealResultDAO.create(transformDealResult(dealResultDto));
    }

    @Override
    public List<DealResultDto> getByDealId(String dealId) {
        List<DealResult> resultList = dealResultDAO.getByDealId(new DataHash().decode(dealId));
        return resultList.stream().map(dr -> transformDealResult(dr)).collect(Collectors.toList());
    }

    @Override
    public DealResultDto transformDealResult(DealResult dealResult) {
        DealResultDto dealResultDto = new DealResultDto();
        dealResultDto.setDeal(dealResult.getDealId());
        dealResultDto.setId(dealResult.getId());
        dealResultDto.setHashedId(new DataHash().encode(dealResult.getId()));
        dealResultDto.setPairNS(dealResult.getPairNS());
        dealResultDto.setPairEW(dealResult.getPairEW());
        dealResultDto.setDeclarer(dealResult.getDeclarer());
        dealResultDto.setDeclarerPosition(dealResult.getDeclarerPosition());
        dealResultDto.setContract(dealResult.getContract());
        dealResultDto.setLead(dealResult.getLead());
        dealResultDto.setResult(dealResult.getResult());
        dealResultDto.setPoints(dealResult.getPoints());

        return dealResultDto;
    }

    @Override
    public DealResult transformDealResult(DealResultDto dealResultDto) {
        DealResult dealResult = new DealResult();
        dealResult.setDealId(dealResultDto.getDeal());
        dealResult.setId(dealResultDto.getId());
        dealResult.setPairNS(dealResultDto.getPairNS());
        dealResult.setPairEW(dealResultDto.getPairEW());
        dealResult.setDeclarer(dealResultDto.getDeclarer());
        dealResult.setDeclarerPosition(dealResultDto.getDeclarerPosition());
        dealResult.setContract(dealResultDto.getContract());
        dealResult.setLead(dealResultDto.getLead());
        dealResult.setResult(dealResultDto.getResult());
        dealResult.setPoints(dealResultDto.getPoints());

        return dealResult;

    }

    public DealResultDAO getDealResultDAO() {
        return dealResultDAO;
    }

    public void setDealResultDAO(DealResultDAO dealResultDAO) {
        this.dealResultDAO = dealResultDAO;
    }
}