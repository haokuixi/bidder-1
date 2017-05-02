package main.modules;

import main.dao.MovementDAO;
import main.dto.MovementDto;
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

        return movementDto;
    }

    @Override
    public Movement transformMovement(MovementDto m) {
        Movement movement = new Movement();
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
