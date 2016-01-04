import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

public class Homework4GIS {

    /**
     * @param args
     * http://www.gdal.org/ogr2ogr.html
     * http://docs.geotools.org/latest/userguide/tutorial/quickstart/maven.html
     * 
     */
    public static void main(String[] args) {
        // display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }

        FileDataStore store;
        SimpleFeatureSource featureSource = null;
        try {
            store = FileDataStoreFinder.getDataStore(file);
            featureSource = store.getFeatureSource();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");
        System.out.println(featureSource.getSchema().getGeometryDescriptor().getType());
        if (featureSource != null) {
            Style style = SLD.createSimpleStyle(featureSource.getSchema(),Color.GREEN);
            Layer layer = new FeatureLayer(featureSource, style);
            map.addLayer(layer);

            // Now display the map
            JMapFrame.showMap(map);
        }
    }

}
