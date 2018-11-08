import com.station.rest.StationCrudApplication;
import com.station.rest.model.Station;
import com.station.rest.model.StationDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StationCrudApplication.class)
@WebAppConfiguration
@EntityScan(basePackages = {"com.station.rest"})
public class StationCrudApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private StationDataRepository stationDataRepository;

    @Test
    public void testSaveStation(){
        Station station = new Station();
        station.setCallSign("callSign");
        station.setName("Station5");
        station.setHdEnabled(true);
        station.setStationId(6);
        stationDataRepository.insert(station);
        assertNotNull(station.getStationId()); //not null after save
        Station fetchedStation = stationDataRepository.findByStationId(station.getStationId());
        assertNotNull(fetchedStation);
        assertEquals(station.getStationId(), fetchedStation.getStationId());
        assertEquals(station.getName(), fetchedStation.getName());
        //update description and save
        fetchedStation.setName("Station5");
        station.setStationId(station.getStationId()+1);
        stationDataRepository.insert(station);
        Station fetchedUpdatedStation = stationDataRepository.findByStationId(station.getStationId());
        assertEquals(fetchedStation.getName(), fetchedUpdatedStation.getName());
        Iterable<Station> stations = stationDataRepository.findAll();
        int count = 0;
        for(Station eachStation : stations){
            count++;
        }
        assertNotEquals(count, 50);
    }

    @Test
    public void findAllUsers() {
        List<Station> stations = stationDataRepository.findAll();
        assertNotNull(stations);
        assertTrue(!stations.isEmpty());
    }

    @Test
    public void testFindStationId() {
        Station station = stationDataRepository.findByStationId(1);
        assertNotNull(station);
    }

    @Test
    public void testFindStationName() {
        List<Station> stations = stationDataRepository.findByStationName("Station1");
        assertNotNull(stations);
        assertTrue(!stations.isEmpty());
    }


    @Test
    public void testRemoveStation() {
        int count = stationDataRepository.deleteById(1);
        assertEquals(count,1);
    }

    @Test
    public void testUpdateStation() {
        Station station = new Station();
        station.setCallSign("callSign");
        station.setName("Station5");
        station.setHdEnabled(true);
        station.setStationId(1);
        stationDataRepository.update(station);
        Station stationData = stationDataRepository.findByStationId(1);
        assertEquals(station.getName(),stationData.getName());
    }

    @Test
    public void testFindHdEnabledStations() {
        List<Station> stations = stationDataRepository.findByHdEnabledStations(true);
        assertNotNull(stations);
        assertTrue(!stations.isEmpty());
    }


}