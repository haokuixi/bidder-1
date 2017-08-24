package main.modules;

import main.dao.DealResultDAO;
import main.dto.DealResultDto;
import main.entities.DealResult;
import main.entities.Pair;
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
    public String resolveContractColor(String text) {
        switch (text.substring(1, 2)) {
            case "S":
                return "♠";
            case "H":
                return "♥";
            case "D":
                return "♦";
            case "C":
                return "♣";
            default:
                return "NT";
        }
    }

    @Override
    public DealResultDto transformDealResult(DealResult dealResult) {
        DealResultDto dealResultDto = new DealResultDto();
        dealResultDto.setDeal(dealResult.getDealId());
        dealResultDto.setHashedId(new DataHash().encode(dealResult.getId()));
        dealResultDto.setPairNS(dealResult.getPairNS());
        dealResultDto.setPairEW(dealResult.getPairEW());
        dealResultDto.setDeclarerPosition(dealResult.getDeclarerPosition());
        dealResultDto.setContract(dealResult.getContract());
        dealResultDto.setLead(dealResult.getLead());
        dealResultDto.setResult(dealResult.getResult());
        dealResultDto.setPoints(dealResult.getPoints());
        dealResultDto.setContractColor(resolveContractColor(dealResult.getContract()));
        dealResultDto.setRoundNumber(dealResult.getRoundNumber());

        return dealResultDto;
    }

    @Override
    public DealResult transformDealResult(DealResultDto dealResultDto) {
        DealResult dealResult = new DealResult();
        dealResult.setDealId(dealResultDto.getDeal());
        if(dealResultDto.getHashedId() != null) {
            dealResult.setId(new DataHash().decode(dealResultDto.getHashedId()));
        }
        dealResult.setPairNS(dealResultDto.getPairNS());
        dealResult.setPairEW(dealResultDto.getPairEW());
        dealResult.setDeclarerPosition(dealResultDto.getDeclarerPosition());
        dealResult.setContract(dealResultDto.getContract());
        dealResult.setLead(dealResultDto.getLead());
        dealResult.setResult(dealResultDto.getResult());
        dealResult.setPoints(dealResultDto.getPoints());
        dealResult.setRoundNumber(dealResultDto.getRoundNumber());

        return dealResult;

    }

    @Override
    public boolean didUserPlayedThisDeal(String login, List<DealResultDto> results) {
        for (DealResultDto dr : results) {
            Pair pairEW = dr.getPairEW();
            Pair pairNS = dr.getPairNS();

            if (pairEW.getPlayerOne().getLogin().equals(login) ||
                    pairEW.getPlayerTwo().getLogin().equals(login) ||
                    pairNS.getPlayerOne().getLogin().equals(login) ||
                    pairNS.getPlayerTwo().getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean didUserPlayedThisDeal(String login, String dealId) {
        return didUserPlayedThisDeal(login, getByDealId(dealId));
    }

    public DealResultDAO getDealResultDAO() {
        return dealResultDAO;
    }

    public void setDealResultDAO(DealResultDAO dealResultDAO) {
        this.dealResultDAO = dealResultDAO;
    }
}
