package main.modules;

import main.dao.MovementDAO;
import main.dto.MovementDto;
import main.dto.XmlContentDto;
import main.entities.Movement;
import main.model.movements.Tables;
import main.utils.DataHash;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MovementModuleImpl implements MovementModule {

    MovementDAO movementDAO;

    @Override
    public void create(MovementDto m) {
        movementDAO.create(transformMovement(m));
    }

    @Override
    public void create(XmlContentDto xml) {
        create(createDto(xml));
    }

    @Override
    public List<MovementDto> getByPairsNumber(int pairsNumber) {
        List<Movement> byPairsNumber = movementDAO.getByPairsNumber(pairsNumber);
        List<MovementDto> list = new ArrayList<>();

        for (Movement m : byPairsNumber) {
            list.add(transformMovement(m));
        }

        return list;
    }

    @Override
    public MovementDto getById(int id) {
        return transformMovement(movementDAO.getById(id));
    }

    @Override
    public MovementDto getByid(String hashedId) {
        return transformMovement(movementDAO.getById(new DataHash().decode(hashedId)));
    }

    @Override
    public MovementDto transformMovement(Movement m) {
        MovementDto movementDto = new MovementDto();
        movementDto.setHashedId(new DataHash().encode(m.getId()));
        movementDto.setPairs(m.getPairs());

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Tables.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(m.getXmlFile());
            movementDto.setMovementTables((Tables) unmarshaller.unmarshal(reader));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        movementDto.setBoards(calculateBoards(movementDto));
        movementDto.setRounds(movementDto.getMovementTables().getTable().get(0).getRounds().getRound().size());

        return movementDto;
    }

    @Override
    public MovementDto createDto(XmlContentDto xml) {
        MovementDto movementDto = new MovementDto();
        JAXBContext context = null;
        String movement = xml.getXmlContent().replace("\t", "").replace("\n", "").replace("\r", "");
        try {
            context = JAXBContext.newInstance(Tables.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(movement);
            movementDto.setMovementTables((Tables) unmarshaller.unmarshal(reader));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        movementDto.setBoards(calculateBoards(movementDto));
        movementDto.setRounds(movementDto.getMovementTables().getTable().get(0).getRounds().getRound().size());
        movementDto.setPairs(movementDto.getMovementTables().getTable().size()*2);
        return movementDto;
    }

    private int calculateBoards(MovementDto movementDto) {
        List<Tables.Table> tables = movementDto.getMovementTables().getTable();
        int boards = 0;

        if(!tables.isEmpty()) {
            for(int i=0; i<tables.size(); i++) {
                List<Tables.Table.Rounds.Round> rounds = tables.get(i).getRounds().getRound();
                for(int j=0; j<rounds.size(); j++) {
                    Byte to = rounds.get(j).getBoards().getTo();
                    if(to > boards) {
                        boards = to;
                    }
                }
            }
        }

        return boards;
    }

    @Override
    public Movement transformMovement(MovementDto m) {
        Movement movement = new Movement();

        if(m.getHashedId() != null) {
            movement.setId(new DataHash().decode(m.getHashedId()));
        }
        movement.setPairs(m.getPairs());

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Tables.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(m.getMovementTables(), sw);
            movement.setXmlFile(sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return movement;
    }


    public MovementDAO getMovementDAO() {
        return movementDAO;
    }

    public void setMovementDAO(MovementDAO movementDAO) {
        this.movementDAO = movementDAO;
    }
}
