package net.sf.javaanpr.test;

import junitparams.JUnitParamsRunner;
import junitparams.FileParameters;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * Created by Flashed on 07-03-2017.
 */
@RunWith(JUnitParamsRunner.class)
public class RecognitionAllIT {

    @Test
    @FileParameters("src/test/resources/plates.csv")
    public void loadParamsFromCSVFile(String imagePath, String expectedLicensePlate) throws IOException, ParserConfigurationException, SAXException {

        imagePath = "snapshots/" + imagePath;
        CarSnapshot carSnap = new CarSnapshot(imagePath);
        assertThat("CarSnap is null", carSnap, notNullValue());
        assertThat("CarSnap.image is null", carSnap.getImage(), notNullValue());
        Intelligence intel = new Intelligence();
        assertThat("Intel is null", intel, notNullValue());
        String spz = intel.recognize(carSnap);
        assertThat("The licence plate is null - are you sure the image has the correct color space?", spz, notNullValue());
        assertThat("Expected Licenseplate did not match result", expectedLicensePlate, equalTo(spz));
        carSnap.close();
    }

}
