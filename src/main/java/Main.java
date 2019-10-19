import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import output.OutputAsFile;

import java.io.*;

public class Main {
	static String[] strategyNameList = {
		"PM", "CM1"
	};
	static String config_file_path_ = "/src/main/resources/common.json";

	public static void main( String[] args ) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		int experiment_times_ = 0;

		try {
			jsonNode = mapper.readTree( new File( System.getProperty( "user.dir" ) + config_file_path_ ) );
			experiment_times_ = jsonNode.get( "environment" ).get( "experiment_times" ).asInt();
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		for ( String strategyName: strategyNameList ) {

			ExperimentManager em = new ExperimentManager( strategyName );
			for ( int i = 0; i < experiment_times_; i++ ) {
				em.doExperiment();
			}
			OutputAsFile of = new OutputAsFile();
			of.output( "results", strategyName, null, "csv" );
		}
	}
}
