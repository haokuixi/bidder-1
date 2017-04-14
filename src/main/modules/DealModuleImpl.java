package main.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dao.DealDAO;
import main.dto.DealDto;
import main.entities.Deal;
import main.model.deals.DealsUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class DealModuleImpl implements DealModule {

    private static Logger LOGGER = Logger.getLogger(DealModuleImpl.class);

    TournamentModule tournamentModule;
    DealsUtils dealsUtils;
    DealDAO dealDAO;

    @Override
    public void createDeal(DealDto d) {
        try {
            dealDAO.create(transformDeal(d));
        } catch (JsonProcessingException e) {
            LOGGER.debug(e.getMessage(), e);
        }
    }

    @Override
    public Deal transformDeal(DealDto dealDto) throws JsonProcessingException {
        dealsUtils = new DealsUtils();
        Deal deal = new Deal();
        deal.setId(dealDto.getId());
        deal.setTournament(tournamentModule.transformTournament(dealDto.getTournament()));
        deal.setCards(dealsUtils.dealToJson(dealDto.getDealModel()));

        return deal;
    }

    @Override
    public DealDto transformDeal(Deal deal) throws IOException {
        dealsUtils = new DealsUtils();
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setTournament(tournamentModule.transformTournament(deal.getTournament()));
        dealDto.setDealModel(dealsUtils.jsonToDeal(deal.getCards()));

        return dealDto;
    }

    public DealDAO getDealDAO() {
        return dealDAO;
    }

    public void setDealDAO(DealDAO dealDAO) {
        this.dealDAO = dealDAO;
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}
